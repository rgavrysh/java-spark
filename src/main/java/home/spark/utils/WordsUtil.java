package home.spark.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsUtil {

    public static List<String> trimWord(String word) {
        return Stream.of(word.split(" "))
                .map(w -> w.replaceAll("[^a-zA-Z]", ""))
                .collect(Collectors.toList());
    }
}
