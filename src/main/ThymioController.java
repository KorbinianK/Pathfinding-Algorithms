package main;

import iw.ur.thymio.Thymio.Thymio;
import pathfinding.AStar;

public class ThymioController {

	 public static Thymio t;

	public static void main(String[] args) {
	    	t = new Thymio("192.168.10.1");
	    	t.setSpeed("ahead", 1000);
	    	Controller c = new Controller();
	    	AStar.calculate();
		}
}
