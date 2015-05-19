package org.anaxivis.core.svg;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.apache.batik.swing.JSVGCanvas;

public class RotatedJSVGCanvas extends JSVGCanvas {
    private double rotation;
    private double offsetX;
    private double offsetY;

    public RotatedJSVGCanvas(double rotation, int width, int height) {
	super();

	double temp = rotation % 360;

	this.rotation = 0;
	this.offsetX = 0;
	this.offsetY = 0;

	if (temp == 90) {
	    this.offsetX = width;
	    this.rotation = temp;
	} else if (temp == 180) {
	    this.offsetY = height;
	    this.rotation = temp;
	} else if (temp == 270) {
	    this.offsetX = width;
	    this.offsetY = height;
	    this.rotation = temp;
	}
    }

    @Override
    public void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.translate(offsetX, offsetY);
	g2d.rotate(Math.toRadians(rotation));

	super.paint(g2d);
    }
}
