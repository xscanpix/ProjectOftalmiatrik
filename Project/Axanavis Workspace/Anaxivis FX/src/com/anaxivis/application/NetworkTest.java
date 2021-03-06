package com.anaxivis.application;

import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetworkTest extends TimerTask {
    public ConcurrentLinkedQueue<String> queue;

    public NetworkTest() {
	queue = new ConcurrentLinkedQueue<>();

	queue.add("1");
	queue.add("1");
	queue.add("1");
	queue.add("1");

    }

    @Override
    public void run() {
	while (true) {
	    queue.add("2");

	    System.out.println("Network queue size: " + queue.size());
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }

}
