package home.spark.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:user.properties")
public class AppConfig {
    @Bean
    public JavaSparkContext sc() {
        SparkConf conf = new SparkConf().setAppName("wiki analyst").setMaster("local[*]");
        return new JavaSparkContext(conf);
    }

    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(sc());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}