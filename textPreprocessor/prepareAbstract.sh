#!/bin/bash
inputFile="$1"
outputFile="abstract_${1}"
python textReplacer.py ./prepareAbstract.conf < $inputFile > tmp0
python textReplacer.py ./htmlChemicalIndex.conf < tmp0 > $outputFile
rm tmp*
