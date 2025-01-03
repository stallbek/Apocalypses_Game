package game.tiles;

import game.system.Tile;
import game.system.TileType;

public class PlainTile extends Tile {
    public PlainTile() {
        super(3,1,0);
        this.type = TileType.Plain;
    }
}
