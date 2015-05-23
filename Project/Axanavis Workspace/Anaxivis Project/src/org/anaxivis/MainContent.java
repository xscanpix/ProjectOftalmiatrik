package org.anaxivis;

import java.util.logging.Logger;

import org.anaxivis.core.svg.EyeChart;

public class MainContent {

    private static final Logger logger = Logger.getLogger(MainContent.class.getName());

    private Application parent;
    private EyeChart eyeChart;

    public MainContent(Application parent) {
	this.parent = parent;
    }

    public void updateEyeChart(int type, float distance, String characters, int[] rotations) {
	if (eyeChart == null) {
	    eyeChart = new EyeChart(type, distance, characters, rotations);
	} else {
	    eyeChart.updatePanel(type, distance, characters, rotations);
	}
	parent.getFrame().setVisible(false);
	parent.getFrame().getContentPane().removeAll();
	parent.getFrame().getContentPane().add(eyeChart.getPanel());
	parent.getFrame().setVisible(true);
    }

    public void updateEyeChart(int distance) {
	updateEyeChart(eyeChart.getType(), eyeChart.getDistanceFromScreen() + distance, eyeChart.getCharacters(), eyeChart.getRotations());
    }

    public EyeChart getEyeChart() {
	return eyeChart;
    }
}
