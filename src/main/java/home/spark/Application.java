package home.spark;

import home.spark.services.CountryAssociationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        CountryAssociationService associationService = context.getBean(CountryAssociationService.class);
        List<String> top5 = associationService.topAssociations("japan", 10);
        System.out.println(top5);
    }
}
