package home.spark.services;

import org.apache.spark.api.java.JavaRDD;

import java.io.Serializable;
import java.util.List;

public interface PopularWordService extends Serializable {

    List<String> topXWords(JavaRDD<String> lines, int x);
}
