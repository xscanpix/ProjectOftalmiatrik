package org.anaxivis.core.svg;

import java.awt.Dimension;

public class EyeChartGenerator {
    public static enum TYPE {
	LOGMAR_R_T
    };

    public static String generateEyeChart(TYPE type, String characters, Dimension resolution) {	
	if (type == TYPE.LOGMAR_R_T) {
	    return LogMAREyeChart.getTestSVGString();
	}

	return "";
    }
}
