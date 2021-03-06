package com.anaxivis.application.fx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class EyeChart {
    private final ObjectProperty<Image> image;
    private final IntegerProperty chartType;

    public EyeChart() {
	this(null, 0);
    }

    public EyeChart(Image image, int type) {
	this.image = new SimpleObjectProperty<Image>(image);
	this.chartType = new SimpleIntegerProperty(type);
    }

    public Image getImage() {
	return image.get();
    }

    public int getType() {
	return chartType.get();
    }

    public void setImage(Image value) {
	image.set(value);
    }

    public void setType(int value) {
	chartType.set(value);
    }
}
