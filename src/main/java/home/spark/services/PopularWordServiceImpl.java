package home.spark.services;

import home.spark.configuration.UserConfig;
import home.spark.utils.WordsUtil;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.List;

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
}
