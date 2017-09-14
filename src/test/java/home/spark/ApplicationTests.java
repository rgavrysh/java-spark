package home.spark;

import home.spark.services.PopularWordService;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private JavaSparkContext sc;
    @Autowired
    private PopularWordService popularWordService;

    @Test
    public void contextLoads() {
        JavaRDD<String> rdd = sc.parallelize(Arrays.asList("java", "java", "java", "scala", "groovy", "groovy"));
        List<String> topWords = popularWordService.topXWords(rdd, 1);
        Assert.assertEquals("java", topWords.get(0));
    }
}
