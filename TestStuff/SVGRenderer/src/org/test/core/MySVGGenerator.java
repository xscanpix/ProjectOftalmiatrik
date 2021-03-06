package org.test.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MySVGGenerator {
	private int width;
	private int height;
	private float dpi;
	private String fileName;
	private Random random = new Random();

	private char[] goodChars = { 'A', 'B', 'C', 'D', 'E', 'F', 'H', 'K', 'L',
			'M', 'N', 'O', 'P', 'R', 'S', 'T', 'X', 'Y', 'Z' };

	public MySVGGenerator(int width, int height, float dpi, String fileName) {
		this.width = width;
		this.height = height;
		this.dpi = dpi;
		this.fileName = fileName;

		initDocument();
	}

	public void initDocument() {
		float pixelPerMM = dpi / 25.4f;

		StringBuilder builder = new StringBuilder();

		builder.append("<?xml version=\"1.0\" ?>");
		builder.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\" >");
		builder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" version=\"1.1\" width=\""
				+ width + "\" height=\"" + height + "\" >");
		builder.append("<defs><style>@font-face { font-family: \"Sloan\"; src: url(./Sloan.otf); }</style></defs>");

		float charWidth = 58.666666666f * pixelPerMM;
		float charSpacing = 44 * pixelPerMM;
		float totalWidth = 0;
		float offset = 0;
		float yoffset = 0;

		for (int i = 0; i < 20; ++i) {
			charWidth = charWidth * 0.75f;
			charSpacing = charSpacing * 0.75f;
			totalWidth = (charWidth * 5f) + (charSpacing * 4f);
			offset = (width - totalWidth) / 2f;
			yoffset = yoffset + charWidth + charSpacing;

			builder.append("<text x=\"" + offset + "\" y=\"" + yoffset
					+ "\" font-size=\"" + charWidth
					+ "\" font-family=\"Sloan\">"
					+ goodChars[random.nextInt(goodChars.length)] + "</text>");
			builder.append("<text x=\"" + (offset + charWidth + charSpacing)
					+ "\" y=\"" + yoffset + "\" font-size=\"" + charWidth
					+ "\" font-family=\"Sloan\">"
					+ goodChars[random.nextInt(goodChars.length)] + "</text>");
			builder.append("<text x=\""
					+ (offset + (charWidth + charSpacing) * 2f) + "\" y=\""
					+ yoffset + "\" font-size=\"" + charWidth
					+ "\" font-family=\"Sloan\">"
					+ goodChars[random.nextInt(goodChars.length)] + "</text>");
			builder.append("<text x=\""
					+ (offset + (charWidth + charSpacing) * 3f) + "\" y=\""
					+ yoffset + "\" font-size=\"" + charWidth
					+ "\" font-family=\"Sloan\">"
					+ goodChars[random.nextInt(goodChars.length)] + "</text>");
			builder.append("<text x=\""
					+ (offset + (charWidth + charSpacing) * 4f) + "\" y=\""
					+ yoffset + "\" font-size=\"" + charWidth
					+ "\" font-family=\"Sloan\">"
					+ goodChars[random.nextInt(goodChars.length)] + "</text>");
		}

		builder.append("</svg>");

		FileWriter out;
		try {
			out = new FileWriter(new File(fileName));
			out.write(builder.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
