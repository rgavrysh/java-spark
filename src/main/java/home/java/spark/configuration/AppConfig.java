package home.java.spark.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public JavaSparkContext sc() {
        SparkConf conf = new SparkConf().setAppName("wiki analyst").setMaster("local[*]");
        return new JavaSparkContext(conf);
    }
}
