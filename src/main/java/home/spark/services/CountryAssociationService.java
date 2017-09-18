package home.spark.services;

import java.util.Collection;
import java.util.List;

public interface CountryAssociationService {
    List<String> topAssociations(String country, int x);

    Collection<String> commonWords(String country1, String country2, int numOfWords);
}
