package org.anaxivis.core.svg;

import javax.swing.JPanel;

public class EyeChart {
    private JPanel contentPanel;
    private int distanceFromScreen;

    public EyeChart() {
	this.contentPanel = new JPanel();
    }

    public JPanel getPanel() {
	return contentPanel;
    }

    public void updatePanel(JPanel panel, int distanceFromScreen) {
	contentPanel = panel;
	this.distanceFromScreen = distanceFromScreen;
    }
}
