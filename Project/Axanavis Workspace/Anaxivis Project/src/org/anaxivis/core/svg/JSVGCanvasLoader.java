package org.anaxivis.core.svg;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

public class JSVGCanvasLoader {
    public static Document loadFile(File file) {

	Document doc = null;

	try {
	    String parser = XMLResourceDescriptor.getXMLParserClassName();
	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);

	    doc = f.createDocument("", new FileReader(file));
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

	return doc;
    }

    public static Document loadFile(String fileName) {

	File file = new File(fileName);

	return loadFile(file);
    }

    public static Document loadString(String string) {
	JSVGCanvas canvas = new JSVGCanvas();

	Document doc = null;

	try {
	    String parser = XMLResourceDescriptor.getXMLParserClassName();
	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);

	    doc = f.createDocument("", new StringReader(string));
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

	return doc;
    }
}
