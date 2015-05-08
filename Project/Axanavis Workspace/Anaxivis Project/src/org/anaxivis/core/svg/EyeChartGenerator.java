package org.anaxivis.core.svg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

import org.anaxivis.MainContent;
import org.apache.batik.swing.JSVGCanvas;

public class EyeChartGenerator {
    public static enum TYPE {
	LOGMAR, SNELLEN
    };

    public static void generateEyeChart(TYPE type, String characters, Dimension resolution, int dpi, float distance, MainContent mainContent) {
	switch (type) {
	case LOGMAR:
	    generateLogmar(characters, resolution, dpi, distance, mainContent);
	case SNELLEN:
	    generateSnellen(characters, resolution, dpi, distance, mainContent);
	default:
	    break;
	}
    }

    private static void generateLogmar(String characters, Dimension resolution, int dpi, float distance, MainContent mainContent) {
	GridBagConstraints gbc = new GridBagConstraints();

	JPanel panel;
	JSVGCanvas canvas;
	int size = 100; // Calculate first row size
	int rows = 7; // Number of rows to show
	float verticalPaddingPercentOfSize = 1; // How big the padding between
						// letters should be
	float horizontalPaddingPercentOfSize = 0.75f; // How big the padding
						      // between rows should be
	float letterSizeDecrease = 0.75f; // How much the letters should
					  // decrease

	for (int i = 0; i < rows; i++) {

	    panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (size * horizontalPaddingPercentOfSize), 0));
	    System.out.println(panel.getBorder());

	    if (i > 0) {
		size = (int) (size * letterSizeDecrease);
		gbc.gridy = i;
	    }

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(0 + (i * rows), 1 + (i * rows)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(1 + (i * rows), 2 + (i * rows)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(2 + (i * rows), 3 + (i * rows)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(3 + (i * rows), 4 + (i * rows)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(4 + (i * rows), 5 + (i * rows)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    gbc.gridy = i;
	    mainContent.add(panel, gbc);
	}
    }

    private static void generateSnellen(String characters, Dimension resolution, int dpi, float distance, MainContent mainContent) {
	List<JSVGCanvas> canvases = new ArrayList<JSVGCanvas>();
    }
}
