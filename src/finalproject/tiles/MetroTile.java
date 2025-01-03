package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

import static finalproject.GraphTraversal.BFS;

public class MetroTile extends Tile {
	public double metroTimeCost = 100;
	public double metroDistanceCost = 100;
	public double metroCommuteFactor = 0.2;

    public MetroTile() {
        super(1, 1, 2);
        this.type = TileType.Metro;
    }
    

    public void fixMetro(Tile node) {
        Tile metrile = null;

        for (Tile t: BFS(node)){
            if (t instanceof MetroTile && node instanceof MetroTile){
                metrile = t;
            }
        }

        int X = node.xCoord - metrile.xCoord;
        int Y = node.yCoord - metrile.yCoord;

        if (X < 0){
            X = -1 * X;
        }
        if (Y < 0){
            Y = -1 * Y;
        }

        int total = X + Y;

        this.metroTimeCost = total * this.metroCommuteFactor;
        this.metroDistanceCost = total / this.metroCommuteFactor;
    }
}
