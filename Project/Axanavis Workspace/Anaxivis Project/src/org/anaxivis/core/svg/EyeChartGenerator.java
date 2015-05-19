package org.anaxivis.core.svg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.anaxivis.Application;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EyeChartGenerator {

    public static final double radiansPerArcmin = Math.PI / 10800;

    public static JPanel generateLogmar(String characters, Dimension resolution, int dpi, float distance, boolean upper) {
	JPanel panel = new JPanel();
	panel.setLayout(new GridBagLayout());
	panel.setPreferredSize(new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT));
	panel.setBackground(Color.WHITE);

	int[] optotypeSizes = getOptotypeSizes(distance * 100);
	System.out.println("Sizes: " + optotypeSizes.length);

	GridBagConstraints gbc = new GridBagConstraints();

	int offset;

	if (upper) {
	    offset = 0;
	} else {
	    offset = 5;
	}

	int rows = characters.length() / 5;
	System.out.println("Rows: " + rows);
	int lettersPerRow = 5;

	if ((rows * 5) > characters.length()) {
	    int delta = (rows * 5) - characters.length();
	    characters = characters.concat(getStringWithLengthAndFilledWithCharacter(delta, 'E'));
	}

	for (int index = 0; index < rows; index++) {

	    gbc.gridy = index;

	    if (index < rows - 1) {
		panel.add(
			createLatinRow(characters.substring(index * 5, index * 5 + 5), optotypeSizes[index + offset], optotypeSizes[index + offset
				+ 1]), gbc);
	    } else {
		panel.add(createLatinRow(characters.substring(index * 5, index * 5 + 5), optotypeSizes[index + offset], 0), gbc);
	    }
	}

	return panel;
    }

    public static JPanel generateTumbling(int[] rotations, Dimension resolution, int dpi, float distance, boolean upper) {
	JPanel panel = new JPanel();
	panel.setLayout(new GridBagLayout());
	panel.setPreferredSize(new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT));
	panel.setBackground(Color.WHITE);

	int[] optotypeSizes = getOptotypeSizes(distance * 100);
	System.out.println("Sizes: " + optotypeSizes.length);

	GridBagConstraints gbc = new GridBagConstraints();

	int offset;

	if (upper) {
	    offset = 0;
	} else {
	    offset = 5;
	}

	int rows = rotations.length / 5;
	System.out.println("Rows: " + rows);
	int lettersPerRow = 5;

	for (int index = 0; index < rows; index++) {

	    gbc.gridy = index;

	    if (index < rows - 1) {
		panel.add(createTumblingRow(rotations, index, optotypeSizes[index + offset], optotypeSizes[index + offset + 1]), gbc);
	    } else {
		panel.add(createTumblingRow(rotations, index, optotypeSizes[index + offset], 0), gbc);
	    }
	}

	return panel;
    }

    private static JPanel createLatinRow(String characters, int size, int nextSize) {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	panel.setBackground(Color.WHITE);

	panel.setBorder(BorderFactory.createEmptyBorder(0, 0, nextSize, 0));

	for (int i = 0; i < characters.length(); i++) {
	    JSVGCanvas canvas = SVGCharacter.getLatinCharacter(characters.substring(i, i + 1), size, 0);
	    canvas.setPreferredSize(new Dimension(size, size));

	    panel.add(canvas);

	    if (i < characters.length() - 1) {
		panel.add(Box.createHorizontalStrut(size));
	    }
	}

	return panel;
    }

    private static JPanel createTumblingRow(int[] rotations, int index, int size, int nextSize) {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	panel.setBackground(Color.WHITE);
	panel.setFocusable(false);

	panel.setBorder(BorderFactory.createEmptyBorder(0, 0, nextSize, 0));

	for (int i = 0; i < 5; i++) {
	    JSVGCanvas canvas = SVGCharacter.getLatinCharacter("E", size, rotations[index]);
	    canvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
	    canvas.setDoubleBuffered(true);
	    canvas.setPreferredSize(new Dimension(size, size));
	    canvas.setFocusable(false);
	    panel.add(canvas);

	    if (i < 5 - 1) {
		panel.add(Box.createHorizontalStrut(size));
	    }
	}

	return panel;
    }

    private static int[] getOptotypeSizes(double distanceFromScreen) {
	int result[] = new int[14];

	for (int i = 0; i < 14; i++) {
	    double logMar = (10.0 - (double) i) / 10;
	    double v = Math.pow(10, logMar) * radiansPerArcmin;
	    double h = 10 * distanceFromScreen * Math.tan(v / 2);
	    result[i] = (int) (h * 37.795276);
	}

	return result;
    }

    private static String getStringWithLengthAndFilledWithCharacter(int length, char charToFill) {
	if (length > 0) {
	    char[] array = new char[length];
	    Arrays.fill(array, charToFill);
	    return new String(array);
	}
	return "";
    }
}
