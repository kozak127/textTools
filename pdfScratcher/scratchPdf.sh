#!/bin/bash
inputXml="$1"
inputPdf="$2"

groovy -cp pdfbox-app-1.8.3.jar main.groovy $inputXml $inputPdf
