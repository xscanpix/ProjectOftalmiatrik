package com.anaxivis.application;

import java.io.File;
import java.nio.file.Path;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainContent {

    private StackPane view;
    private ImageView iView;
    private int type;

    public MainContent() {
	view = new StackPane();
	iView = new ImageView();

	view.getChildren().add(iView);
    }

    public void loadSVGFile(Path p) {
	Image image = SVGImageLoader.loadFromFile(p);
	iView.setImage(image);
	type = SVGImageGenerator.UNKNOWN;
    }

    public void loadSVGFile(File file) {
	Image image = SVGImageLoader.loadFromFile(file);
	iView.setImage(image);
	type = SVGImageGenerator.UNKNOWN;
    }

    public void loadImage(Image newImage, int newType) {
	System.out.println(newImage.getHeight());
	iView.setImage(newImage);
	if (newType == SVGImageGenerator.LOGMAR_1 || newType == SVGImageGenerator.LOGMAR_2 || newType == SVGImageGenerator.LOGMAR_R) {
	    iView.setViewport(new Rectangle2D(0, 0, newImage.getWidth(), newImage.getHeight()));
	}
	type = newType;
    }

    public ImageView getIView() {
	return iView;
    }

    public StackPane getView() {
	return view;
    }
}
