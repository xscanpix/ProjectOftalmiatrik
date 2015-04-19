package org.anaxivis.network;

import javax.swing.SwingWorker;

import org.anaxivis.IAxanivis;

public class TestWorker extends SwingWorker<String, Object> {

    private IAxanivis application;

    public TestWorker(IAxanivis application) {
	this.application = application;
    }

    @Override
    protected String doInBackground() throws Exception {
	
	return "Doing in background!";
    }
}
