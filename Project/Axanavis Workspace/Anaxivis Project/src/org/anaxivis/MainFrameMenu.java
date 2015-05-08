package org.anaxivis;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.anaxivis.core.svg.EyeChartGenerator;

/**
 * The main frame menu bar class
 * 
 * @author Svante Nilsson
 * @version 0.1
 * @since 2015-04-06
 */
@SuppressWarnings("serial")
public class MainFrameMenu extends JMenuBar {
    private static final Logger logger = Logger.getLogger(MainFrameMenu.class.getName());

    // String constants
    private static final String FILE = "Arkiv";
    private static final String FULLSCREEN = "Fullskärm";
    private static final String OPEN_FILE = "Öppna fil";
    private static final String EXIT = "Stäng";
    private static final String CHARTS = "Charts";
    private static final String LOGMAR_RANDOM = "Random LogMAR";
    private static final String LOGMAR_1_U = "Övre LogMAR 1";
    private static final String LOGMAR_1_B = "Undre LogMAR 1";
    private static final String LOGMAR_2_U = "Övre LogMAR 2";
    private static final String LOGMAR_2_B = "Undre LogMAR 2";
    private static final String HELP = "Hjälp";
    private static final String BUG_FIX = "Fix bugs";
    private static final String ABOUT = "Om programmet";
    private static final String NETWORK = "Nätverk";
    private static final String START_NETWORK = "Starta nätverk";
    private static final String STOP_NETWORK = "Stoppa nätverk";
    private static final String START_POLLING = "Lyssna";
    private static final String STOP_POLLING = "Sluta lyssna";

    private Application parent;

    public MainFrameMenu(Application parent) {
	super();

	this.parent = parent;

	init();
    }

    private void init() {
	add(createFileMenu());
	add(createChartMenu());
	add(createHelpMenu());
	add(createNetworkMenu());
    }

    private JMenu createFileMenu() {
	JMenu fileMenu = new JMenu(FILE);
	JMenuItem exitMenuItem = new JMenuItem(EXIT);
	exitMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		parent.requestClose();
	    }
	});
	JMenuItem fullscreenMenuItem = new JMenuItem(FULLSCREEN);
	fullscreenMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		parent.toggleFullscreen();
	    }
	});

	JMenuItem openFileMenuItem = new JMenuItem(OPEN_FILE);
	openFileMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SVG Files", "svg");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    logger.log(Level.INFO, "User selected file: " + chooser.getSelectedFile().getName());
		    parent.getMainContent().setCurrentSvgFile(chooser.getSelectedFile());
		} else {
		    logger.log(Level.INFO, "User canceled selecting a file");
		}
	    }
	});

	fileMenu.add(openFileMenuItem);
	fileMenu.add(fullscreenMenuItem);
	fileMenu.add(exitMenuItem);

	return fileMenu;
    }

    private JMenu createChartMenu() {

	// TODO: Extract hard-coded strings

	JMenu chartMenu = new JMenu(CHARTS);
	JMenuItem logMARRandomMenuItem = new JMenuItem(LOGMAR_RANDOM);
	logMARRandomMenuItem.addActionListener(e -> {
	    EyeChartGenerator.generateLogmar("RANDOM", new Dimension(1920, 1080), 96, 6, true, parent.getMainContent());
	});
	JMenuItem logMAR1UMenuItem = new JMenuItem(LOGMAR_1_U);
	logMAR1UMenuItem.addActionListener(e -> {
	    EyeChartGenerator.generateLogmar("NCKZORHSDKDOVHRCZRHSONHRC", new Dimension(1920, 1080), 96, 6, true, parent.getMainContent());
	});
	JMenuItem logMAR1BMenuItem = new JMenuItem(LOGMAR_1_B);
	logMAR1BMenuItem.addActionListener(e -> {
	    EyeChartGenerator.generateLogmar("DKSNVZSOKNCKDNRSRZKDHZOVCNVDOKVHCNOSVHCZOZDVK", new Dimension(1920, 1080), 96, 6, false,
		    parent.getMainContent());
	});
	JMenuItem logMAR2UMenuItem = new JMenuItem(LOGMAR_2_U);
	logMAR2UMenuItem.addActionListener(e -> {
	    EyeChartGenerator.generateLogmar("DSRKNCKZOHONRKDKZVDCVSHZO", new Dimension(1920, 1080), 96, 6, true, parent.getMainContent());
	});
	JMenuItem logMAR2BMenuItem = new JMenuItem(LOGMAR_2_B);
	logMAR2BMenuItem.addActionListener(e -> {
	    EyeChartGenerator.generateLogmar("HDKCRCSRHNSVZDKNCVOZRHSDVSNROHODHKRZKCSNCRHDV", new Dimension(1920, 1080), 96, 6, false,
		    parent.getMainContent());

	});
	chartMenu.add(logMARRandomMenuItem);
	chartMenu.add(logMAR1UMenuItem);
	chartMenu.add(logMAR1BMenuItem);
	chartMenu.add(logMAR2UMenuItem);
	chartMenu.add(logMAR2BMenuItem);

	return chartMenu;
    }

    private JMenu createHelpMenu() {
	JMenu helpMenu = new JMenu(HELP);
	JMenuItem aboutMenuItem = new JMenuItem(ABOUT);
	aboutMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

	    }
	});
	JMenuItem bugfixMenuItem = new JMenuItem(BUG_FIX);
	bugfixMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		parent.toggleMenu();
		parent.toggleMenu();
	    }
	});
	helpMenu.add(bugfixMenuItem);
	helpMenu.add(aboutMenuItem);

	return helpMenu;
    }

    private JMenu createNetworkMenu() {
	JMenu networkMenu = new JMenu(NETWORK);
	JMenuItem startNetworkMenuItem = new JMenuItem(START_NETWORK);
	startNetworkMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		parent.getNetwork().start();
	    }
	});
	JMenuItem stopNetworkMenuItem = new JMenuItem(STOP_NETWORK);
	stopNetworkMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		parent.getNetwork().stop();
	    }
	});
	JMenuItem startPollingNetworkMenuItem = new JMenuItem(START_POLLING);
	startPollingNetworkMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		parent.startPolling();
	    }
	});
	JMenuItem stopPollingNetworkMenuItem = new JMenuItem(STOP_POLLING);
	stopPollingNetworkMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		parent.stopPolling();
	    }
	});
	networkMenu.add(startNetworkMenuItem);
	networkMenu.add(stopNetworkMenuItem);
	networkMenu.add(startPollingNetworkMenuItem);
	networkMenu.add(stopPollingNetworkMenuItem);

	return networkMenu;
    }
}
