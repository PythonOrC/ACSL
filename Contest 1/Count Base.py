#!/bin/python3

import math
import os
import random
import re
import sys



#
# Complete the 'findModeCount' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER num
#  2. INTEGER base
#  3. STRING start
#

def findModeCount(num, base, start):
    digits = ["0","1","2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"]
    digits = digits[:base]
    counts = dict(zip(digits, [0]*len(digits)))
    i = start
    cnt = 1

    while (cnt <= num):
        print(i)
        counts = addCounts(counts, i)
        cnt += 1
        i = "".join(increment(digits, list(i)))
    return max(counts.values())
    
#recursion that increments the number
def increment(digits, i):
    # base case
    if len(i) == 1:
        if i[0] == digits[-1]:
            i[0] = digits[0]
            i.insert(0, digits[1])
        else:
            i[0] = digits[digits.index(i[0])+1]
        return i
    # recursive case
    if i[-1] == digits[-1]:
        i[-1] = digits[0]
        i = increment(digits, i[:-1]) + i[-1:]
    else:
        i[-1] = digits[digits.index(i[-1])+1]
    return i
    
def addCounts(counts, i):
    for digit in i:
        counts[digit] += 1
    return counts
    
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    num = int(input().strip())

    base = int(input().strip())

    start = input()

    result = findModeCount(num, base, start)

    fptr.write(str(result) + '\n')

    fptr.close()
