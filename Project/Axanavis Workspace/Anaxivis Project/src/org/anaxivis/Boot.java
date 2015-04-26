package org.anaxivis;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.anaxivis.network.Network;

/**
 * 
 * Class containing main method, used to run the application
 * 
 * @author Svante
 *
 */
public class Boot {

    private static final Logger logger = Logger.getLogger(Boot.class.getName());

    private static IApplication application;
    private static Network network;

    public static void main(String[] args) {
	network = new Network();
	
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		// TODO: Fix annoying Swing bug :@.
		logger.log(Level.INFO, "Running application...");
		application = new Application(network);
	    }
	});

	network.start();
    }
}
