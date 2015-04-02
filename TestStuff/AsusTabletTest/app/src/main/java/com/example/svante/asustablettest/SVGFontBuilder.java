package com.example.svante.asustablettest;

import android.util.Log;

public class SVGFontBuilder {

    public static String createSVGTextBox(String characters, int fontSize, int xOffset, int yOffset, String fillColor, String fontFamily) {
        StringBuilder builder = new StringBuilder();

        builder.append("<text type=\"textBox\" transform=\"matrix(1 0 0 1 " + xOffset + " " + yOffset + ")\" ");

        builder.append("width=\"" + fontSize + "\" ");
        builder.append("height=\"" + fontSize + "\" ");
        builder.append("stroke-miterlimit=\"4\" stroke=\"none\" fill=\"" + fillColor
                        + "\" font-family=\"" + fontFamily
                        + "\" font-size=\"" + fontSize
                        + "\" text-align=\"center\" text-anchor=\"start\">");

        builder.append("<tspan x=\"0\" y=\"" + fontSize + "\">");
        builder.append("<![CDATA[" + characters + "]]>");
        builder.append("</tspan></text>");

        return builder.toString();
    }

    public static String createSVGFont(int width, int height, String s) {

        StringBuilder builder = new StringBuilder();

        builder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" x=\"0px\" y=\"0px\" width=\"" + width + "px\" height=\"" + height + "px\" viewBox=\"0 0 "
                + width + " " + height
                + "\" enable-background=\"new 0 0 "
                + width + " " + height
                + "\" xml:space=\"preserve\">");

        builder.append(createSVGTextBox(s, 512, (width / 2) - 256, 0, "#000000","Arial"));

        builder.append("</svg>");

        return builder.toString();
    }
}
