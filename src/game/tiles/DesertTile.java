package game.tiles;

import game.system.Tile;
import game.system.TileType;

public class DesertTile extends Tile {
    public DesertTile() {
        super(2, 6, 3);
        this.type = TileType.Desert;
    }
}
