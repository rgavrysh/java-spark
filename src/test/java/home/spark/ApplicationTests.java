package home.spark;

import home.spark.services.PopularWordService;
import home.spark.utils.WordDataFrameCreator;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private JavaSparkContext sc;
    @Autowired
    private SQLContext sqlContext;
    @Autowired
    private PopularWordService popularWordService;

    @Test
    public void contextLoads() {
        JavaRDD<String> rdd = sc.parallelize(Arrays.asList("java", "java", "java", "scala", "groovy", "groovy"));
        List<String> topWords = popularWordService.topXWords(rdd, 1);
        Assert.assertEquals("java", topWords.get(0));
    }

    @Test
    public void commonWords() {
        JavaRDD<Row> rdd1 = sc.parallelize(Arrays.asList("java", "java", "spark", "spring")).map(RowFactory::create);
        JavaRDD<Row> rdd2 = sc.parallelize(Arrays.asList("java", "javascript", "spark", "spring", "python")).map(RowFactory::create);
        DataFrame dataFrame1 = sqlContext.createDataFrame(rdd1, WordDataFrameCreator.dataFrameStructure());
        DataFrame dataFrame2 = sqlContext.createDataFrame(rdd2, WordDataFrameCreator.dataFrameStructure());
        Set<String> strings = popularWordService.topCommonWords(dataFrame1, dataFrame2, 3);
        Assert.assertTrue(strings.containsAll(Arrays.asList("java", "spark", "spring")));
        Assert.assertFalse(strings.contains("python"));
    }
}
