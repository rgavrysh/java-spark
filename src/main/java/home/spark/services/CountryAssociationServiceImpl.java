package home.spark.services;

import home.spark.utils.WordDataFrameCreator;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CountryAssociationServiceImpl implements CountryAssociationService {

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private PopularWordService popularWordService;

    @Autowired
    private WordDataFrameCreator wordDataFrameCreator;

    @Override
    public List<String> topAssociations(String country, int x) {
        JavaRDD<String> rdd = sc.textFile("data/country/" + country + ".txt");
        return popularWordService.topXWords(rdd, x);
    }

    @Override
    public Collection<String> commonWords(String country1, String country2, int numOfWords) {
        return popularWordService.topCommonWords(wordDataFrameCreator.create("data/country/" + country1 + ".txt"),
                wordDataFrameCreator.create("data/country/" + country2 + ".txt"), numOfWords);
    }
}
