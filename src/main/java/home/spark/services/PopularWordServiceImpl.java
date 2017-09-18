package home.spark.services;

import home.spark.configuration.UserConfig;
import home.spark.utils.WordsUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.*;

@Service
public class PopularWordServiceImpl implements PopularWordService {
    @Autowired
    private UserConfig userConfig;

    @Override
    public List<String> topXWords(JavaRDD<String> lines, int x) {
        return lines.map(String::toLowerCase)
                .flatMap(WordsUtil::trimWord)
                .filter(word -> !userConfig.garbage.contains(word))
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> (a + b))
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .map(Tuple2::_2)
                .take(x);
    }

    @Override
    public Set<String> topCommonWords(DataFrame country1, DataFrame country2, int numOfWords) {
        DataFrame sorted = country1.withColumn("words", lower(column("words")))
                .filter(not(column("words").isin(userConfig.garbage.toArray())))
                .groupBy(column("words")).agg(count("words").as("country1"))
                .sort(column("country1").desc());
        sorted.show(numOfWords);

        DataFrame sorted2 = country2.withColumn("words", lower(column("words")))
                .filter(not(column("words").isin(userConfig.garbage.toArray())))
                .groupBy(column("words")).agg(count("words").as("country2"))
                .sort(column("country2").desc());
        sorted2.show(numOfWords);

        DataFrame dataFrame = sorted.join(sorted2, "words").sort(desc("country1"), desc("country2"));
        dataFrame.show(numOfWords);
        Row[] rows = dataFrame.take(numOfWords);
        return Arrays.asList(dataFrame.take(numOfWords))
                .stream().map(row -> row.getString(0)).collect(Collectors.toSet());
    }
}
