package home.java.spark.services;

import org.apache.spark.api.java.JavaRDD;

import java.util.List;

public interface PopularWordService {

    List<String> topXWords(JavaRDD<String> lines, int x);
}
