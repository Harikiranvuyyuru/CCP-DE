#!/usr/bin/env python

from pyspark import SparkConf, SparkContext
"""
	Usage: ./spark-submit SimpleApp.py
"""
conf = SparkConf().setMaster("local[2]").setAppName("Simple App")
sc = SparkContext(conf=conf)

data = sc.parallelize([1, 2, 3, 4])

total = data.reduce(lambda x, y: x + y)

print total
