#!/bin/bash
inputFile="$1"
outputFile="abstractInternational_${1}"
python textReplacer.py ./prepareAbstractInternational.conf < $inputFile > tmp0
python textReplacer.py ./htmlChemicalIndex.conf < tmp0 > $outputFile
rm tmp*
