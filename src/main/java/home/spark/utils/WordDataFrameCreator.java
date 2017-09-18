package home.spark.utils;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordDataFrameCreator {
    @Autowired
    private JavaSparkContext sc;
    @Autowired
    private SQLContext sqlContext;

    public DataFrame create(String pathToFile) {
        JavaRDD<Row> rowJavaRDD = sc.textFile(pathToFile)
                .map(String::toLowerCase)
                .flatMap(WordsUtil::trimWord)
                .map(RowFactory::create);
        return sqlContext.createDataFrame(rowJavaRDD, DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("words", DataTypes.StringType, true)
        }));
    }
}
