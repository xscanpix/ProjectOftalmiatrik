package com.anaxivis.application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class ApplicationMenu {
    private static final String FILE = "Arkiv";
    private static final String OPEN_FILE = "�ppna fil";
    private static final String SAVE_FILE = "Spara fil";
    private static final String FULLSCREEN = "Fullsk�rm";
    private static final String EXIT = "St�ng";
    private static final String CHARTS = "Charts";
    private static final String LOGMAR_R = "LogMAR_R";
    private static final String LOGMAR_1 = "LogMAR_1";
    private static final String LOGMAR_2 = "LogMAR_2";
    private static final String HELP = "Hj�lp";
    private static final String ABOUT = "Om programmet";

    private MenuBar view;
    private AxanivisApplication parent;

    public ApplicationMenu(AxanivisApplication parent) {
	this.parent = parent;

	view = new MenuBar();

	view.getMenus().add(createFileMenu());
	view.getMenus().add(createChartsMenu());
    }

    private Menu createFileMenu() {
	Menu menu = new Menu(FILE);

	MenuItem openFile = new MenuItem(OPEN_FILE);
	openFile.addEventHandler(ActionEvent.ACTION, e -> {
	    FileChooser fc = new FileChooser();
	    fc.setTitle(OPEN_FILE);
	    File file = fc.showOpenDialog(parent.getMainStage());
	    if (file != null)
		parent.getMainContent().loadSVGFile(file);
	});
	MenuItem saveFile = new MenuItem(SAVE_FILE);
	saveFile.addEventHandler(ActionEvent.ACTION, e -> {
	    // TODO: Add saving the last generated chart
	    });
	MenuItem fullscreen = new MenuItem(FULLSCREEN);
	fullscreen.addEventHandler(ActionEvent.ACTION, e -> {
	    parent.getMainStage().setFullScreen(!parent.getMainStage().isFullScreen());
	});
	MenuItem exit = new MenuItem(EXIT);
	exit.addEventHandler(ActionEvent.ACTION, e -> {
	    parent.close();
	});

	menu.getItems().addAll(openFile, saveFile, fullscreen, exit);

	return menu;
    }

    private Menu createChartsMenu() {
	Menu menu = new Menu(CHARTS);

	MenuItem LogMAR_R = new MenuItem(LOGMAR_R);
	LogMAR_R.addEventHandler(ActionEvent.ACTION, e -> {
	    parent.getMainContent()
		    .loadImage(SVGImageGenerator.generateImage(SVGImageGenerator.LOGMAR_R, 1920, 1080, 96), SVGImageGenerator.LOGMAR_R);
	});
	MenuItem LogMAR_1 = new MenuItem(LOGMAR_1);
	LogMAR_1.addEventHandler(ActionEvent.ACTION, e -> {
	    //parent.getMainContent().loadSVGFile("Sloan2.svg");
	});
	MenuItem LogMAR_2 = new MenuItem(LOGMAR_2);
	LogMAR_2.addEventHandler(ActionEvent.ACTION, e -> {
	    //parent.getMainContent().loadSVGFile("Sloan2.svg");
	});

	menu.getItems().addAll(LogMAR_R, LogMAR_1, LogMAR_2);

	return menu;
    }

    public MenuBar getView() {
	return view;
    }
}
