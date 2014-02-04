#!/bin/bash
inputFile="$1"
outputFile="${1}_preparedAbstractInternational"
python textReplacer.py ./prepareAbstractInternational.conf < $inputFile > tmp0
python textReplacer.py ./htmlChemicalIndex.conf < tmp0 > $outputFile
rm tmp*
