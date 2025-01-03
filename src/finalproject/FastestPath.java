package finalproject;

import finalproject.system.Tile;

public class FastestPath extends PathFindingService {
    public FastestPath(Tile start) {
        super(start);
        this.generateGraph();

    }

	@Override
	public void generateGraph() {
		super.graphic("time");
	}

}
