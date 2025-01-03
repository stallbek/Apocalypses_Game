package finalproject;

import finalproject.system.Tile;

public class ShortestPath extends PathFindingService {
    public ShortestPath(Tile start) {
        super(start);
        this.generateGraph();
    }


	@Override
	public void generateGraph() {
        super.graphic("distance");
	}
}