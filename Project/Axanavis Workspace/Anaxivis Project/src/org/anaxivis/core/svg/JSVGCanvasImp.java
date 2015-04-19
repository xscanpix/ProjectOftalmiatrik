package org.anaxivis.core.svg;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

@SuppressWarnings("serial")
public class JSVGCanvasImp extends JSVGCanvas {
    public JSVGCanvasImp() {

	@SuppressWarnings("unused")
	TestLogMAR test = new TestLogMAR(1920, 1080, 96, "Sloan2.svg");

	Document doc = null;
	this.setFocusable(false);

	try {
	    String parser = XMLResourceDescriptor.getXMLParserClassName();
	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);

	    doc = f.createDocument("Sloan2.svg");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	this.setDoubleBuffered(true);
	this.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
	this.setDocument(doc);
    }
}
