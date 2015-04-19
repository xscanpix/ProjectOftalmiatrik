package org.anaxivis;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.anaxivis.network.TestWorker;

/**
 * 
 * Class containing main method, used to run the application
 * 
 * @author Svante
 *
 */
public class Boot {

    private static final Logger logger = Logger.getLogger(Boot.class.getName());

    private static IAxanivis application;

    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		// TODO: Fix annoying Swing bug :@.
		logger.log(Level.INFO, "Running application...");
		application = new Axanivis();

		(new TestWorker(application)).execute();
	    }
	});

    }
}
