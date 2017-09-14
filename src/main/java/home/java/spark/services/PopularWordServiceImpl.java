package home.java.spark.services;

import home.java.spark.utils.WordsUtil;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.List;

@Service
public class PopularWordServiceImpl implements PopularWordService {

    @Override
    public List<String> topXWords(JavaRDD<String> lines, int x) {
        return lines.map(String::toLowerCase)
                .flatMap(WordsUtil::trimWord)
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> (a + b))
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .map(Tuple2::_2)
                .take(x);
    }
}
