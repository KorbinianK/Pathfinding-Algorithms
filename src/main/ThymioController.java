package main;

import iw.ur.thymio.Thymio.Thymio;
import pathfinding.AStar;

public class ThymioController {

	 public static Thymio t;

	public ThymioController(){
		t = new Thymio("192.168.10.1");
    	t.setSpeed("ahead", 1000);
    	
	}

	public Thymio getThymio() {
		return t;
	}
}
