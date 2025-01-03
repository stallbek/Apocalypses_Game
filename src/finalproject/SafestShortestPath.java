package finalproject;


import java.util.ArrayList;
import java.util.LinkedList;

import finalproject.system.Tile;

public class SafestShortestPath extends ShortestPath {
	public int health;
	public Graph costGraph;
	public Graph damageGraph;
	public Graph aggregatedGraph;

	public SafestShortestPath(Tile start, int health) {
		super(start);
		this.health = health;
	}

	
	public void generateGraph() {

	}

}
