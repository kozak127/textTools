#!/bin/bash
inputFile="$1"
outputFile="int_${1}"
python textReplacer.py ./prepareInt.conf < $inputFile > tmp0
python textReplacer.py ./htmlChemicalIndex.conf < tmp0 > $outputFile
rm tmp*
