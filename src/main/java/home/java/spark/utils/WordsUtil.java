package home.java.spark.utils;

import java.util.Collections;
import java.util.List;

public class WordsUtil {

    public static List<String> trimWord(String word) {
        return Collections.singletonList(word.replaceAll("[^a-zA-Z]", ""));
    }
}
