package org.anaxivis.network;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Network {

    private ConcurrentLinkedQueue<String> queue;
    private ScheduledThreadPoolExecutor ex;
    private Runnable task;
    private boolean initialized;
    private volatile boolean isRunning;

    public Network() {
	ex = new ScheduledThreadPoolExecutor(1);
	queue = new ConcurrentLinkedQueue<>();
	task = new Runnable() {
	    @Override
	    public void run() {
		if (isRunning) {
		    queue.add("Test");
		    System.out.println("Network queue size: " + queue.size());
		}
	    }
	};

	initialized = false;
	isRunning = false;
    }

    public void start() {
	if (!initialized) {
	    ex.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);
	    initialized = true;
	}
	isRunning = true;
    }

    public void stop() {
	isRunning = false;
    }

    public String poll() {
	return queue.poll();
    }

    public void dispose() {
	stop();
    }
}
