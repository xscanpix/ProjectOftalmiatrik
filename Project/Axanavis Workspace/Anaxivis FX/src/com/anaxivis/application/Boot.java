package com.anaxivis.application;

import javafx.application.Application;

public class Boot {

    // static NetworkTest test = new NetworkTest();

    public static void main(String[] args) {
	new Thread() {
	    @Override
	    public void run() {
		Application.launch(AxanivisApplication.class);
	    }
	}.start();
	AxanivisApplication startUpTest = AxanivisApplication.waitForStartUpTest();

	// new Thread(test).start();
	// startUpTest.setNet(test);
	// startUpTest.startNet();
    }
}
