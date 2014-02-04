import sys
import datetime

configFile = open(sys.argv[1], 'r')
symbolList = []

for line in configFile:
    symbol = line.split(';')
    symbol[1] = symbol[1][:-1] #remove trailing \n
    symbol[0] = symbol[0].replace('\\n', '\n'); #remove escaping \\n
    symbol[1] = symbol[1].replace('\\n', '\n'); #remove escaping \\n
    symbolList.append(symbol)

XMONTH = datetime.datetime.now().strftime("%m")
XYEAR = datetime.datetime.now().strftime("%Y")
XNUMBER = 0

for line in sys.stdin:
    for symbol in symbolList:
        line = line.replace(symbol[0], symbol[1])
    
    line = line.replace("XMONTH", XMONTH)
    line = line.replace("XYEAR", XYEAR)
    if (line.find("XNUMBER") != -1):
        line = line.replace("XNUMBER", str(XNUMBER))
        XNUMBER = XNUMBER + 1
    
    sys.stdout.write(line)

