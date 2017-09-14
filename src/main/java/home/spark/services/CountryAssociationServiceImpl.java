package home.spark.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryAssociationServiceImpl implements CountryAssociationService {

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private PopularWordService popularWordService;

    @Override
    public List<String> topAssociations(String country, int x) {
        JavaRDD<String> rdd = sc.textFile("data/country/" + country + ".txt");
        return popularWordService.topXWords(rdd, x);
    }
}
