package org.anaxivis;

import java.util.logging.Logger;

import javax.swing.JPanel;

import org.anaxivis.core.svg.EyeChart;

public class MainContent {

    private static final Logger logger = Logger.getLogger(MainContent.class.getName());

    private Application parent;
    private EyeChart eyeChart;

    public MainContent(Application parent) {
	this.parent = parent;
	eyeChart = new EyeChart();
    }

    public void updateEyeChart(JPanel panel, int distanceFromScreen) {
	eyeChart.updatePanel(panel, distanceFromScreen);
	parent.getFrame().setVisible(false);
	parent.getFrame().getContentPane().removeAll();
	parent.getFrame().getContentPane().add(eyeChart.getPanel());
	parent.getFrame().setVisible(true);
    }

    public EyeChart getEyeChart() {
	return eyeChart;
    }
}
