package home.spark.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.DataFrame;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface PopularWordService extends Serializable {

    List<String> topXWords(JavaRDD<String> lines, int x);

    Set<String> topCommonWords(DataFrame country1, DataFrame country2, int numOfWords);
}
