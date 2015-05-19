package org.anaxivis.core.svg;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SVGCharacter {

    private static final String supportedLatinCharacters = "CDEHKNORSVXZ";
    private static final String PATH_TO_RESOURCES = "./res/";
    private static final String SLOAN_C = "sloan_c2.svg";
    private static final String SLOAN_D = "sloan_d2.svg";
    private static final String SLOAN_H = "sloan_h2.svg";
    private static final String SLOAN_K = "sloan_k2.svg";
    private static final String SLOAN_N = "sloan_n2.svg";
    private static final String SLOAN_O = "sloan_o2.svg";
    private static final String SLOAN_R = "sloan_r2.svg";
    private static final String SLOAN_S = "sloan_s2.svg";
    private static final String SLOAN_V = "sloan_v2.svg";
    private static final String SLOAN_X = "sloan_x2.svg";
    private static final String SLOAN_Z = "sloan_z2.svg";
    private static final String SLOAN_E = "sloan_e2.svg";

    public static JSVGCanvas getLatinCharacter(String letter, int sizePixel, double rotation) {

	String path = "";
	switch (letter) {
	case "C":
	    path = PATH_TO_RESOURCES + SLOAN_C;
	    break;
	case "D":
	    path = PATH_TO_RESOURCES + SLOAN_D;
	    break;
	case "E":
	    path = PATH_TO_RESOURCES + SLOAN_E;
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
	default:
	    path = PATH_TO_RESOURCES + SLOAN_E;
	    break;
	}

	Document doc = JSVGCanvasLoader.loadFile(path);

	if (doc != null) {
	    Element root = doc.getDocumentElement();

	    NodeList nodelist = root.getElementsByTagName(SVGConstants.SVG_TEXT_TAG);
	    if (nodelist.getLength() > 0) {
		Node node = nodelist.item(0);

		// Set font-size and offset y
		node.getAttributes().getNamedItemNS(null, SVGConstants.SVG_Y_ATTRIBUTE).setNodeValue(Integer.toString(sizePixel));
		node.getAttributes().getNamedItemNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE).setNodeValue(Integer.toString(sizePixel));
	    }

	    root.setAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, Integer.toString(sizePixel));
	    root.setAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, Integer.toString(sizePixel));
	    root.setAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE, "0 0 " + sizePixel + " " + sizePixel);

	    JSVGCanvas canvas = null;
	    if (rotation == 0) {
		canvas = new JSVGCanvas();
	    } else {
		canvas = new RotatedJSVGCanvas(rotation, sizePixel, sizePixel);
	    }
	    canvas.setDoubleBuffered(true);
	    canvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
	    canvas.setDocument(doc);

	    return canvas;
	}

	// File problem
	return null;
    }
}
