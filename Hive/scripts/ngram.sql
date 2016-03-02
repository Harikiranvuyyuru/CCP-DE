--Usage: hive -f ngram.sql --hivevar text=paragraph --hivevar ngram=2 --hivevar top=10 --hivevar table=text

-- CREATE TABLE text (paragraph string)
--	ROW FORMAT DELIMITED FIELDS TERMINATED BY '\n'
--	STORED AS TEXTFILE;

--	------------------------------------------------
--	|	paragraph				|
--	|-----------------------------------------------|
--	|In the fields of computational linguistics and |
--	|probability, an n-gram is a contiguous sequence|
--	|of n items from a given sequence of text or 	|
--	|speech. The items can be phonemes, syllables, 	|
--	|letters, words or base pairs according to the 	|
--	|application. The n-grams typically are 	|
--	|collected from a text or speech corpus. When 	|
--	|the items are words, n-grams may also be called|
--	|shingles.					|
--	|-----------------------------------------------|
--	|An n-gram of size 1 is referred to as a 	|
--	|"unigram"; size 2 is a "bigram" (or, less 	|
--	|commonly, a "digram"); size 3 is a "trigram".	|
--	|Larger sizes are sometimes referred to by the 	|
--	|value of n, e.g., "four-gram", "five-gram", 	|
--	|and so on.					|
--	-------------------------------------------------

SELECT ngram(sentences(lower(${hivevar:text})), ${hivevar:ngram}, ${hivevar:top})
FROM ${hivevar:table};

-- [{"ngram":["is","a"],"estfrequency":3.0},{"ngram":["an","n-gram"],"estfrequency":2.0},
--	{"ngram":["from","a"],"estfrequency":2.0},{"ngram":["of","n"],"estfrequency":2.0},
--	{"ngram":["or","speech"],"estfrequency":2.0},{"ngram":["referred","to"],"estfrequency":2.0},
--	{"ngram":["sequence","of"],"estfrequency":2.0},{"ngram":["text","or"],"estfrequency":2.0},
--	{"ngram":["the","items"],"estfrequency":2.0},{"ngram":["the","application"],"estfrequency":1.0}]
