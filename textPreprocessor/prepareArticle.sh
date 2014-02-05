#!/bin/bash
inputFile="$1"
outputFile="article_${1}"
python textReplacer.py ./prepareArticle.conf < $inputFile > tmp0
python textReplacer.py ./htmlChemicalIndex.conf < tmp0 > $outputFile
rm tmp*
