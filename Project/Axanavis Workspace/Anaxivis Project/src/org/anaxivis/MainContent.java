package org.anaxivis;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import org.anaxivis.core.svg.JSVGCanvasLoader;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.Document;

/**
 * The main content class
 * 
 * @author Svante Nilsson
 * @version 0.1
 * @since 2015-04-06
 */
@SuppressWarnings("serial")
public class MainContent extends JPanel {

    private static final Logger logger = Logger.getLogger(MainContent.class.getName());

    private Application parent;
    private JSVGCanvas currentCanvas;

    public MainContent(Application parent) {
	super();

	this.parent = parent;

	init();
    }

    private void init() {
	setFocusable(false);

	currentCanvas = new JSVGCanvas();
	currentCanvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
	currentCanvas.setDoubleBuffered(true);
	currentCanvas.setFocusable(false);
	add(currentCanvas);
    }

    public void setCurrentSVGDocument(Document doc) {
	if (currentCanvas != null) {
	    currentCanvas.setDocument(doc);
	}
    }

    public void setCurrentSvgFile(File file) {
	logger.log(Level.INFO, "Setting current file to: " + file.getName());

	setCurrentSVGDocument(JSVGCanvasLoader.loadFile(file));
    }
}
