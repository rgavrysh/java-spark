# java-spark
This is a learning-purpose project. Major aim is get to know how to configure and boost Spark Context, and get familiar with its API.
# word-count
"Hello World!"-like example for parallel-computation frameworks - count words.

Text file as an input source -> RDD -> filter/aggregation -> topWords -> List<String>

Use: 'localhost:8081/country/{name}/{topWords}' endpoint -> (JavaRDD realization) to get topWords from wikitext about country. ('ukraine', 'usa', 'japan' available in 'data/country' folder)
'localhost:8081/common-words?countr1=&country2=&numOfWords=' -> (DataFrame realization) to get most common words between two wikitext sources.