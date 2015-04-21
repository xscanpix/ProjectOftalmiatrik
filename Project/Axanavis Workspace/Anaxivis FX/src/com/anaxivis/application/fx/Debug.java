package com.anaxivis.application.fx;

public class Debug {
    private static final boolean debug = true;

    public static void print(String s) {
	if (debug)
	    System.out.println(s);
    }
}
