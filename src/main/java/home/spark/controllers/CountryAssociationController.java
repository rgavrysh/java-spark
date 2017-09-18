package home.spark.controllers;

import home.spark.services.CountryAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Set;

@RestController
public class CountryAssociationController {
    @Autowired
    private CountryAssociationService associationService;

    @GetMapping("/country/{name}/{numOfWords}")
    public List<String> getAssociatedWords(@PathVariable("name") String name, @PathVariable("numOfWords") int numOfWords) {
        return associationService.topAssociations(name, numOfWords);
    }

    @GetMapping("/common-words")
    public Set<String> getCommonWordsForCountries(@PathParam("country1") String country1,
                                                  @PathParam("country2") String country2,
                                                  @PathParam("numOfWords") int numOfWords) {
        return (Set<String>) associationService.commonWords(country1, country2, numOfWords);
    }
}
