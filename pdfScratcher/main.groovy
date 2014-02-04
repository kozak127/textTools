import java.awt.geom.Rectangle2D
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;
import java.io.*;

def createAreaList(Properties prop) {
    def areaList = []
    def number = prop.getProperty("area.number").toInteger()
    for(int i=0; i < number; i++) {
        areaList.add(createArea(prop, i))
    }
    return areaList
}

def createArea(Properties prop, Integer number) {
    String queryBase = "area."
    queryBase += number
    def x0 = prop.getProperty(queryBase + ".x0").toInteger()
    def x1 = prop.getProperty(queryBase + ".x1").toInteger()
    def y0 = prop.getProperty(queryBase + ".y0").toInteger()
    def y1 = prop.getProperty(queryBase + ".y1").toInteger()

    Rectangle2D rectangle = new Rectangle2D.Float (x0, y0, x1 - x0, y1 - y0)
    //Rectangle2D rectangle = new Rectangle2D.Float (40, 40, 280 - 40, 825 - 40)
    return rectangle
}

def createStripper(List list) {
    PDFTextStripperByArea stripper = new PDFTextStripperByArea()
    list.eachWithIndex{
        region, i ->
        stripper.addRegion(i.toString(), region)
    }
    /*for(int i=0; i< list.size(); i++) {
        stripper.addRegion(i.toString(), list[i])
    }*/
    return stripper
}

def processAndPrintText(PDFTextStripperByArea stripper, List pages, Properties prop){
    def number = prop.getProperty("area.number").toInteger()
    pages.each {
        stripper.extractRegions(it)
        for (int i = 0; i < number; i++){
            print stripper.getTextForRegion(i.toString())
        }   
        print "----------------------------------------------------\n"   
    }
}

try {
    // load properties
    def prop = new Properties()
    def xmlFile = new FileInputStream(args[0])
    prop.loadFromXML(xmlFile)

    // load input file
    File input = new File(args[1])
    def pddoc = PDDocument.load(input)

    // prepare stripper
    def areaList = createAreaList(prop)

    def stripper = createStripper(areaList)

    //get pages
    def pages = pddoc.getDocumentCatalog().getAllPages()

    // extract and print 
	processAndPrintText(stripper, pages, prop)

    // close document
    if (pddoc != null) {
        pddoc.close()
    }
} catch (Exception e){
    e.printStackTrace()
}
