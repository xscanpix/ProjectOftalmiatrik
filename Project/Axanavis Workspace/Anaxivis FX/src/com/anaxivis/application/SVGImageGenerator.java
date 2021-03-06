package com.anaxivis.application;

import javafx.scene.image.Image;

public class SVGImageGenerator {

    public static final int UNKNOWN = -1;
    public static final int LOGMAR_R = 1;
    public static final int LOGMAR_1 = 2;
    public static final int LOGMAR_2 = 3;

    private static Image lastImage;

    public static Image generateImage(int type, int width, int height, int dpi) {

	switch (type) {
	case LOGMAR_R:
	    lastImage = SVGImageLoader.loadFromString(SVGChartGenerator.generateTestLOGMARChart(width, height, dpi));
	    break;
	default:
	    // TODO: Return some default image
	    break;
	}

	return lastImage;
    }
}
