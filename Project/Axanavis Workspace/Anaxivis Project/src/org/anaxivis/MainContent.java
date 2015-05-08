package org.anaxivis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import org.anaxivis.core.svg.EyeChartGenerator;
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
    private List<JSVGCanvas> currentCanvases;
    private GridBagLayout layout;
    private GridBagConstraints gbc;

    public MainContent(Application parent) {
	super();

	this.parent = parent;
	currentCanvases = new ArrayList<JSVGCanvas>();
	gbc = new GridBagConstraints();
	layout = new GridBagLayout();

	init();
    }

    private void init() {
	setFocusable(false);
	setLayout(layout);
	setBackground(Color.WHITE);

	EyeChartGenerator.generateEyeChart(EyeChartGenerator.TYPE.LOGMAR, "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE",
		new Dimension(1920, 1080), 96, 6, this);
    }

    public void setCurrentSVGDocument(Document doc, int index) {
	if (currentCanvases.size() < index) {
	    JSVGCanvas c = currentCanvases.get(index);
	    if (c != null) {
		c.setDocument(doc);
	    }
	}
    }

    public void setCurrentSvgFile(File file) {
	logger.log(Level.INFO, "Setting current file to: " + file.getName());

	setCurrentSVGDocument(JSVGCanvasLoader.loadFile(file), 0);
    }
}
