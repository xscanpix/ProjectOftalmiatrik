package com.anaxivis.application;

import java.util.Random;

public class SVGChartGenerator {

    private static Random random = new Random();

    private static char[] goodChars = { 'A', 'B', 'C', 'D', 'E', 'F', 'H', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'X', 'Y', 'Z' };
    private static String[] values = { "0.50", "6/ 19", "0.40", "6/ 15", "0.30", "6/ 12", "0.20", "6/ 0.5", "0.10", "6 /7.6", "0.00", "6/ 6.0",
	    "-0.10", "6/ 4.8", "-0.20", "6/ 3.8", "-0.30", "6/ 3.0", "-0.40", "6/ 2.4" };

    private static String lastGenerated;

    public static String generateTestLOGMARChart(int width, int height, float dpi) {
	float pixelPerMM = dpi / 25.4f;

	StringBuilder builder = new StringBuilder();

	builder.append("<?xml version=\"1.0\" ?>");
	builder.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\" >");
	builder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" version=\"1.1\" width=\"" + width
		+ "\" height=\"" + (2 * height) + "\" >");
	builder.append("<defs><style>@font-face { font-family: \"Sloan\"; src: url(./Sloan.otf); }</style></defs>");

	float charWidth = 58.666666666f * pixelPerMM;
	float charSpacing = 44 * pixelPerMM;
	float totalWidth = 0;
	float offset = 0;
	float yoffset = -100;

	for (int i = 0; i < 20; i += 2) {
	    charWidth = charWidth * 0.75f;
	    charSpacing = charSpacing * 0.75f;
	    totalWidth = (charWidth * 5f) + (charSpacing * 4f);
	    offset = (width - totalWidth) / 2f;
	    yoffset = yoffset + charWidth + charSpacing;

	    builder.append("<text x=\"" + 50 + "\" y=\"" + (yoffset) + "\" font-size=\"" + 20 + "\" font-family=\"Arial\">" + values[i]
		    + "</text>");

	    builder.append("<text x=\"" + offset + "\" y=\"" + yoffset + "\" font-size=\"" + charWidth + "\" font-family=\"Sloan\">"
		    + goodChars[random.nextInt(goodChars.length)] + "</text>");
	    builder.append("<text x=\"" + (offset + charWidth + charSpacing) + "\" y=\"" + yoffset + "\" font-size=\"" + charWidth
		    + "\" font-family=\"Sloan\">" + goodChars[random.nextInt(goodChars.length)] + "</text>");
	    builder.append("<text x=\"" + (offset + (charWidth + charSpacing) * 2f) + "\" y=\"" + yoffset + "\" font-size=\"" + charWidth
		    + "\" font-family=\"Sloan\">" + goodChars[random.nextInt(goodChars.length)] + "</text>");
	    builder.append("<text x=\"" + (offset + (charWidth + charSpacing) * 3f) + "\" y=\"" + yoffset + "\" font-size=\"" + charWidth
		    + "\" font-family=\"Sloan\">" + goodChars[random.nextInt(goodChars.length)] + "</text>");
	    builder.append("<text x=\"" + (offset + (charWidth + charSpacing) * 4f) + "\" y=\"" + yoffset + "\" font-size=\"" + charWidth
		    + "\" font-family=\"Sloan\">" + goodChars[random.nextInt(goodChars.length)] + "</text>");

	    builder.append("<text x=\"" + (width - 75) + "\" y=\"" + (yoffset) + "\" font-size=\"" + 16 + "\" font-family=\"Arial\">"
		    + values[i] + "</text>");
	}

	builder.append("</svg>");

	lastGenerated = builder.toString();

	return builder.toString();
    }

    public static String getLastGenerated() {
	return lastGenerated;
    }
}
