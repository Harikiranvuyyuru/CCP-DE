#!/usr/bin/env python

from dateutil import parser
import sys
"""
	Usage: python DateFormat.py <date string>
"""
def main(s):
    try:
        #parse input date string
        dt = parser.parse(s)
	# %D is short for %m/%d/%y
        return dt.strftime("%D")
    except ValueError:
        return s

if __name__ == "__main__":
    _formatted = main(sys.argv[1])
    print _formatted
