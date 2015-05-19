package org.anaxivis;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import org.anaxivis.network.Network;

/**
 * The main frame class
 * 
 * @author Svante Nilsson
 * @version 0.1
 * @since 2015-04-06
 */
@SuppressWarnings("serial")
public class Application implements IApplication {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;
    
    private static final String TITLE = "Axanivis";
    private static final int FULLSCREEN_TOGGLE = KeyEvent.VK_F1;
    private static final int MENU_TOGGLE = KeyEvent.VK_F2;
    private static final int DEBUG_MENU_TOGGLE = KeyEvent.VK_F3;
    private static final int MINIMUM_FRAME_WIDTH = 1200;
    private static final int MINIMUM_FRAME_HEIGHT = 800;
    private static final boolean START_IN_FULLSCREEN = false;
    private static final boolean START_POLLING_NETWORK = false;

    private static final int POLLING_DELAY = 0;
    private static final int POLLING_PERIOD = 500;
    private static final TimeUnit POLLING_UNIT = TimeUnit.MILLISECONDS;

    private boolean inFullscreen;
    private boolean isPollingThreadRunning;
    private volatile boolean isPolling;

    private ScheduledThreadPoolExecutor networkExecutor;

    private Network network;
    private JFrame frame;
    private MainContent mainContent;
    private MainFrameMenu mainMenuBar;

    public Application(Network network) {
	this.network = network;
	networkExecutor = new ScheduledThreadPoolExecutor(1);
	frame = new JFrame(TITLE);

	isPollingThreadRunning = false;

	init();
    }

    /**
     * Configures the window with some basic settings and sets up the component
     * and listeners.
     */
    private void init() {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(true);
	frame.setMinimumSize(new Dimension(MINIMUM_FRAME_WIDTH, MINIMUM_FRAME_HEIGHT));
	frame.setLocationRelativeTo(null);

	registerKeyStrokes();

	if (START_IN_FULLSCREEN) {
	    toggleFullscreen();
	    inFullscreen = true;
	} else {
	    frame.setSize(frame.getMinimumSize());
	    inFullscreen = false;
	}

	initComponents();

	frame.validate();
	frame.setVisible(true);
	frame.pack();

	if (START_POLLING_NETWORK) {
	    network.start();
	    startPolling();
	}
    }

    /**
     * Registers the key strokes to the window that is used to toggle a
     * multitude of things.
     */
    private void registerKeyStrokes() {
	InputMap inputMap = frame.getRootPane().getInputMap();
	ActionMap actionMap = frame.getRootPane().getActionMap();

	KeyStroke FULLSCREEN_TOGGLE_KEY_STROKE = KeyStroke.getKeyStroke(FULLSCREEN_TOGGLE, 0, true);
	inputMap.put(FULLSCREEN_TOGGLE_KEY_STROKE, FULLSCREEN_TOGGLE);
	actionMap.put(FULLSCREEN_TOGGLE, new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		toggleFullscreen();
	    }
	});

	KeyStroke MENU_TOGGLE_KEY_STROKE = KeyStroke.getKeyStroke(MENU_TOGGLE, 0, true);
	inputMap.put(MENU_TOGGLE_KEY_STROKE, MENU_TOGGLE);
	actionMap.put(MENU_TOGGLE, new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		toggleMenu();
	    }
	});

	KeyStroke DEBUG_MENU_TOGGLE_KEY_STROKE = KeyStroke.getKeyStroke(DEBUG_MENU_TOGGLE, 0, true);
	inputMap.put(DEBUG_MENU_TOGGLE_KEY_STROKE, DEBUG_MENU_TOGGLE);
	actionMap.put(DEBUG_MENU_TOGGLE, new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		// TODO: Create debug window
	    }
	});
    }

    /**
     * Initializes the components and adds them to the window.
     */
    private void initComponents() {
	mainMenuBar = new MainFrameMenu(this);
	mainContent = new MainContent(this);

	frame.setJMenuBar(mainMenuBar);
    }

    /**
     * Used to request closing the window.
     * 
     * @param message
     *            the message to show when the close is requested.
     */
    public void requestClose() {
	logger.log(Level.INFO, "Close requested");
	networkExecutor.shutdown();
	network.dispose();
	frame.dispose();
    }

    /**
     * Used to request setting the window to fullscreen.
     * 
     * @param message
     *            the message to show when fullscreen is requested.
     */
    public void toggleFullscreen() {
	logger.log(Level.INFO, "Fullscreen toggle requested");

	frame.setVisible(false);

	if (inFullscreen) {
	    frame.dispose();
	    frame.setUndecorated(false);
	    frame.setLocation(0, 0);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    mainMenuBar.setVisible(true);
	    mainMenuBar.validate();
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    inFullscreen = false;
	} else {
	    frame.dispose();
	    mainMenuBar.setVisible(false);
	    mainMenuBar.validate();
	    frame.setUndecorated(true);
	    frame.setLocation(0, 0);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.pack();
	    inFullscreen = true;
	}

	frame.setVisible(true);
	frame.repaint();
    }

    /**
     * Toggles the menu's visibility
     */
    public void toggleMenu() {
	if (frame.getJMenuBar() != null) {
	    if (frame.getJMenuBar().isVisible()) {
		frame.getJMenuBar().setVisible(false);
	    } else {
		frame.getJMenuBar().setVisible(true);
	    }
	}
    }

    public void startPolling() {
	isPolling = true;

	if (!isPollingThreadRunning) {
	    networkExecutor.scheduleAtFixedRate(new Runnable() {
		@Override
		public void run() {
		    if (isPolling) {
			System.out.println("Application polled from network: " + network.poll());
		    }
		}
	    }, POLLING_DELAY, POLLING_PERIOD, POLLING_UNIT);
	    isPollingThreadRunning = true;
	}
    }

    public void stopPolling() {
	isPolling = false;
    }

    public MainContent getMainContent() {
	return mainContent;
    }

    public JFrame getFrame() {
	return frame;
    }

    public Network getNetwork() {
	return network;
    }
}
