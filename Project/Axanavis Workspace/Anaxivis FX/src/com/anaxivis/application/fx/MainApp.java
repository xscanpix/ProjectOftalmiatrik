package com.anaxivis.application.fx;

import java.io.File;
import java.io.IOException;

import com.anaxivis.application.SVGChartGenerator;
import com.anaxivis.application.SVGImageLoader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final String WINDOW_TITLE = "Axanivis";
    private static final int MINIMUM_WIDTH = 800;
    private static final int MINIMUM_HEIGHT = 600;

    private static final boolean START_IN_FULLSCREEN = false;

    private static Stage window;
    private BorderPane rootLayout;

    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem saveFile;
    @FXML
    private MenuItem fullscreen;
    @FXML
    private MenuItem close;
    @FXML
    private AnchorPane mainContent;
    @FXML
    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
	window = primaryStage;
	window.setTitle(WINDOW_TITLE);
	window.setMinWidth(MINIMUM_WIDTH);
	window.setMinHeight(MINIMUM_HEIGHT);
	window.setFullScreenExitHint("");
	window.setOnCloseRequest(e -> {
	    e.consume();
	    close();
	});

	if (START_IN_FULLSCREEN) {
	    fullscreen();
	}

	initRootLayout();
    }

    private void initRootLayout() {
	try {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
	    rootLayout = (BorderPane) loader.load();

	    Scene scene = new Scene(rootLayout);
	    window.setScene(scene);
	    window.show();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void close() {
	Platform.exit();
    }

    @FXML
    private void openFileChooser() {
	FileChooser chooser = new FileChooser();
	chooser.setSelectedExtensionFilter(new ExtensionFilter("SVG", "svg"));

	//File file = chooser.showOpenDialog(window);
	//if (file != null) {
	    // Image image = SVGImageLoader.loadFromFile(file.toPath());
	    Image image = SVGImageLoader.loadFromString(SVGChartGenerator.generateTestLOGMARChart(1920, 1080, 96)); 
	    if (image != null) {
		imageView.fitWidthProperty().set(image.getWidth());
		imageView.fitHeightProperty().set(image.getHeight());
		imageView.viewportProperty().setValue(new Rectangle2D(0, 0, 1920, 1080));

		imageView.setImage(image);
	    }
	    
	//}
    }

    @FXML
    private void fullscreen() {
	window.setFullScreen(!window.isFullScreen());
    }

    public Stage getPrimaryStage() {
	return window;
    }

    public static void main(String[] args) {
	launch(args);
    }
}
