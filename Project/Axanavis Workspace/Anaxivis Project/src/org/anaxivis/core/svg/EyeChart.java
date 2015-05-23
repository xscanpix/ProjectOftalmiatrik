package org.anaxivis.core.svg;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.anaxivis.Application;

public class EyeChart {

    public static final int LOGMAR_R_U = 1;
    public static final int LOGMAR_R_B = 2;
    public static final int LOGMAR_1_U = 3;
    public static final int LOGMAR_1_B = 4;
    public static final int LOGMAR_2_U = 5;
    public static final int LOGMAR_2_B = 6;
    public static final int TUMBLING_U = 7;
    public static final int TUMBLING_B = 8;

    private JPanel contentPanel;
    private int type;
    private float distanceFromScreen;
    private String characters;
    private int[] rotations;

    public EyeChart(int type, float distance, String characters, int[] rotations) {
	updatePanel(type, distance, characters, rotations);
    }

    public JPanel getPanel() {
	return contentPanel;
    }

    public JPanel updatePanel(int type, float distance, String characters, int[] rotations) {
	this.type = type;
	this.distanceFromScreen = distance;
	this.characters = characters;
	this.rotations = rotations;

	switch (type) {
	case 1:
	    contentPanel = EyeChartGenerator.generateLogmar(characters, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, true);
	    break;
	case 2:
	    contentPanel = EyeChartGenerator.generateLogmar(characters, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, false);
	    break;
	case 3:
	    contentPanel = EyeChartGenerator.generateLogmar(characters, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, true);
	    break;
	case 4:
	    contentPanel = EyeChartGenerator.generateLogmar(characters, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, false);
	    break;
	case 5:
	    contentPanel = EyeChartGenerator.generateLogmar(characters, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, true);
	    break;
	case 6:
	    contentPanel = EyeChartGenerator.generateLogmar(characters, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, false);
	    break;
	case 7:
	    contentPanel = EyeChartGenerator.generateTumbling(rotations, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, true);
	    break;
	case 8:
	    contentPanel = EyeChartGenerator.generateTumbling(rotations, new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT), 96,
		    distance, false);
	    break;
	default:
	    contentPanel = EyeChartGenerator.generateLogmar("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe", new Dimension(Application.SCREEN_WIDTH,
		    Application.SCREEN_HEIGHT), 96, distance, false);
	    break;
	}
	
	return contentPanel;
    }

    public JPanel updateDistance(float distance) {
	distanceFromScreen = distanceFromScreen + distance;
	return updatePanel(type, distanceFromScreen, characters, rotations);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getDistanceFromScreen() {
        return distanceFromScreen;
    }

    public void setDistanceFromScreen(float distanceFromScreen) {
        this.distanceFromScreen = distanceFromScreen;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public int[] getRotations() {
        return rotations;
    }

    public void setRotations(int[] rotations) {
        this.rotations = rotations;
    }
}
