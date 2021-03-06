package com.anaxivis.application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

public class SVGImageLoader {
    private static final Logger logger = Logger.getLogger(SVGImageLoader.class.getName());

    public static Image loadFromFile(File file) {
	final BufferedImage buffer[] = new BufferedImage[1];

	try {
	    TranscoderInput in = new TranscoderInput(new FileInputStream(file));

	    ImageTranscoder transcoder = new ImageTranscoder() {
		@Override
		public void writeImage(BufferedImage image, TranscoderOutput out) throws TranscoderException {
		    buffer[0] = image;
		}

		@Override
		public BufferedImage createImage(int w, int h) {
		    return new BufferedImage(w, 2 * h, BufferedImage.TYPE_INT_ARGB);
		}
	    };
	    transcoder.transcode(in, null);

	} catch (FileNotFoundException | TranscoderException e) {
	    logger.log(Level.WARNING, "Problem transcoding the SVG file: " + file.getName());
	    e.printStackTrace();
	    return null;
	}

	return SwingFXUtils.toFXImage(buffer[0], null);
    }

    public static Image loadFromFile(Path p) {

	final BufferedImage buffer[] = new BufferedImage[1];

	try {
	    TranscoderInput in = new TranscoderInput(new FileInputStream(p.toString()));

	    ImageTranscoder transcoder = new ImageTranscoder() {
		@Override
		public void writeImage(BufferedImage image, TranscoderOutput out) throws TranscoderException {
		    buffer[0] = image;
		}

		@Override
		public BufferedImage createImage(int w, int h) {
		    return new BufferedImage(w, 2 * h, BufferedImage.TYPE_INT_ARGB);
		}
	    };
	    transcoder.transcode(in, null);
	} catch (FileNotFoundException | TranscoderException e) {
	    logger.log(Level.WARNING, "Problem transcoding the SVG file: " + p.toString());
	    e.printStackTrace();
	    return null;
	}

	return SwingFXUtils.toFXImage(buffer[0], null);
    }

    public static Image loadFromString(String s) {

	final BufferedImage buffer[] = new BufferedImage[1];

	try {
	    TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(s.getBytes("UTF-8")));

	    ImageTranscoder transcoder = new ImageTranscoder() {
		@Override
		public void writeImage(BufferedImage image, TranscoderOutput out) throws TranscoderException {
		    buffer[0] = image;
		}

		@Override
		public BufferedImage createImage(int w, int h) {
		    return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		}
	    };
	    transcoder.transcode(in, null);
	} catch (TranscoderException | UnsupportedEncodingException e) {
	    logger.log(Level.WARNING, "Problem transcoding the SVG file");
	    e.printStackTrace();
	    return null;
	}

	return SwingFXUtils.toFXImage(buffer[0], null);
    }
}
