package org.anaxivis.core.svg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

import org.anaxivis.MainContent;
import org.apache.batik.swing.JSVGCanvas;

public class EyeChartGenerator {
    public static void generateLogmar(String characters, Dimension resolution, int dpi, float distance, boolean upper, MainContent maincontent) {

	// Clear old content, save maybe?
	maincontent.setVisible(false);
	maincontent.removeAll();
	//

	GridBagConstraints gbc = new GridBagConstraints();

	JPanel panel;
	JSVGCanvas canvas;
	int size;
	if (upper) {
	    size = 100; // Calculate first row size
	} else {
	    size = 50;
	}
	int rows = characters.length() / 5; // Number of rows to show
	int lettersPerRow = 5;
	float verticalPaddingPercentOfSize = 1; // How big the padding between
						// letters should be
	float horizontalPaddingPercentOfSize = 0.75f; // How big the padding
						      // between rows should be
	float letterSizeDecrease = 0.75f; // How much the letters should
					  // decrease

	if ((rows * 5) > characters.length()) {
	    System.out.println(characters.length());
	    // Not enough characters as argument.

	    int delta = (rows * 5) - characters.length();

	    // Fill end with more characters
	    characters = characters.concat(getStringWithLengthAndFilledWithCharacter(delta, 'E'));
	    System.out.println(characters.length());
	}
	for (int i = 0; i < rows; i++) {

	    panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (size * horizontalPaddingPercentOfSize), 0));

	    if (i > 0) {
		size = (int) (size * letterSizeDecrease);
		gbc.gridy = i;
	    }

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(0 + (i * lettersPerRow), 1 + (i * lettersPerRow)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(1 + (i * lettersPerRow), 2 + (i * lettersPerRow)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(2 + (i * lettersPerRow), 3 + (i * lettersPerRow)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(3 + (i * lettersPerRow), 4 + (i * lettersPerRow)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    panel.add(Box.createHorizontalStrut((int) (verticalPaddingPercentOfSize * size)));

	    canvas = SVGCharacter.getLatinCharacter(characters.substring(4 + (i * lettersPerRow), 5 + (i * lettersPerRow)), size);
	    canvas.setPreferredSize(new Dimension(size, size));
	    panel.add(canvas);

	    gbc.gridy = i;
	    maincontent.add(panel, gbc);

	    maincontent.setVisible(true);
	}
    }

    private static void generateSnellen(String characters, Dimension resolution, int dpi, float distance, MainContent mainContent) {
	List<JSVGCanvas> canvases = new ArrayList<JSVGCanvas>();
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
