package com.anaxivis.application;

import java.util.concurrent.CountDownLatch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AxanivisApplication extends Application {
    private static final String WINDOW_TITLE = "Axanivis";
    private static final int MINIMUM_WIDTH = 800;
    private static final int MINIMUM_HEIGHT = 600;
    private static final boolean START_IN_FULLSCREEN = false;

    public static final CountDownLatch latch = new CountDownLatch(1);
    private static AxanivisApplication app;

    private Stage window;
    private ApplicationMenu menuBar;
    private MainContent mainContent;

    //////////////////////////////////////////
    //private NetworkTest net;
    //private ScheduleTest st;
	

    // private class ScheduleTest extends ScheduledService<String> {
    //
    // private NetworkTest net;
    //
    // public ScheduleTest(NetworkTest test) {
    // net = test;
    // }
    //
    // @Override
    // protected Task<String> createTask() {
    //
    // return new Task<String>() {
    // @Override
    // protected String call() throws Exception {
    // return net.queue.poll();
    // }
    // };
    // }
    // }
    ////////////////////////////////////////
    
    public AxanivisApplication() {
	setStartUpTest(this);
    }

    public static AxanivisApplication waitForStartUpTest() {
	try {
	    latch.await();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return app;
    }

    public static void setStartUpTest(AxanivisApplication application) {
	app = application;
	latch.countDown();
    }

    public void start(Stage primaryStage) {
	window = primaryStage;

	
	// st.setPeriod(Duration.millis(10));
	// st.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	// @Override
	// public void handle(WorkerStateEvent event) {
	// System.out.println("App polled from network: " +
	// event.getSource().getValue());
	// }
	// });
	
	// Window settings
	window.setTitle(WINDOW_TITLE);
	window.setMinWidth(MINIMUM_WIDTH);
	window.setMinHeight(MINIMUM_HEIGHT);
	window.setFullScreenExitHint("");
	window.fullScreenProperty().addListener(e -> toggleMenu());
	window.setOnCloseRequest(e -> {
	    e.consume();
	    close();
	});

	mainContent = new MainContent();
	menuBar = new ApplicationMenu(this);

	BorderPane root = new BorderPane();

	root.setTop(menuBar.getView());
	root.setCenter(mainContent.getView());

	// Set scene and show window
	Scene mainScene = new Scene(root);
	window.setScene(mainScene);
	if (START_IN_FULLSCREEN) {
	    window.setFullScreen(START_IN_FULLSCREEN);
	} else {
	    window.setMaximized(true);
	}
	window.show();

    }

    public void toggleMenu() {
	if (menuBar.getView().isVisible() && window.isFullScreen()) {
	    menuBar.getView().setVisible(false);
	} else {
	    menuBar.getView().setVisible(true);
	}
    }

    public MainContent getMainContent() {
	return mainContent;
    }

    public Stage getMainStage() {
	return window;
    }

    public void close() {
	window.close();
    }

    // public void setNet(NetworkTest net) {
    // this.net = net;
    // st = new ScheduleTest(net);
    // }
    //
    // public void startNet() {
    // st.start();
    // }
}
