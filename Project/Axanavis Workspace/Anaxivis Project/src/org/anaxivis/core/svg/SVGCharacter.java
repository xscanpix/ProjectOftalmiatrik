package org.anaxivis.core.svg;

import java.awt.geom.AffineTransform;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SVGCharacter {

    private static final String supportedLatinCharacters = "CDHKNORSVXZ";
    private static final String PATH_TO_RESOURCES = "./res/";
    private static final String SLOAN_C = "sloan_c.svg";
    private static final String SLOAN_D = "sloan_d.svg";
    private static final String SLOAN_H = "sloan_h.svg";
    private static final String SLOAN_K = "sloan_k.svg";
    private static final String SLOAN_N = "sloan_n.svg";
    private static final String SLOAN_O = "sloan_o.svg";
    private static final String SLOAN_R = "sloan_r.svg";
    private static final String SLOAN_S = "sloan_s.svg";
    private static final String SLOAN_V = "sloan_v.svg";
    private static final String SLOAN_X = "sloan_x.svg";
    private static final String SLOAN_Z = "sloan_z.svg";

    public static JSVGCanvas getLatinCharacter(String letter, int sizePixel) {
	letter = letter.toUpperCase();
	if (supportedLatinCharacters.contains(letter)) {
	    String path = "";
	    switch (letter) {
	    case "C":
		path = PATH_TO_RESOURCES + SLOAN_C;
		break;
	    case "D":
		path = PATH_TO_RESOURCES + SLOAN_D;
		break;
	    case "H":
		path = PATH_TO_RESOURCES + SLOAN_H;
		break;
	    case "K":
		path = PATH_TO_RESOURCES + SLOAN_K;
		break;
	    case "N":
		path = PATH_TO_RESOURCES + SLOAN_N;
		break;
	    case "O":
		path = PATH_TO_RESOURCES + SLOAN_O;
		break;
	    case "R":
		path = PATH_TO_RESOURCES + SLOAN_R;
		break;
	    case "S":
		path = PATH_TO_RESOURCES + SLOAN_S;
		break;
	    case "V":
		path = PATH_TO_RESOURCES + SLOAN_V;
		break;
	    case "X":
		path = PATH_TO_RESOURCES + SLOAN_X;
		break;
	    case "Z":
		path = PATH_TO_RESOURCES + SLOAN_Z;
		break;
	    }

	    Document doc = JSVGCanvasLoader.loadFile(path);

	    if (doc != null) {
		Element root = doc.getDocumentElement();
		System.out.println(root.getAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE) + ", " + root.getAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE));
		root.setAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, Integer.toString(sizePixel));
		root.setAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, Integer.toString(sizePixel));
		System.out.println(root.getAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE) + ", " + root.getAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE));
		root.setAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE, "0 0 2000 2000");
		System.out.println(root.getAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE));

		JSVGCanvas canvas = new JSVGCanvas();
		canvas.setDoubleBuffered(true);
		canvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
		canvas.setDocument(doc);

		return canvas;
	    }

	    return null;
	} else {
	    // TODOD: Return some defaults
	    return null;
	}
    }
}
