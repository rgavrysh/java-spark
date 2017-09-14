package home.spark.services;

import java.util.List;

public interface CountryAssociationService {
    List<String> topAssociations(String country, int x);
}
