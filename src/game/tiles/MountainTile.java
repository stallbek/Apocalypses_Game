package game.tiles;

import game.system.Tile;
import game.system.TileType;

public class MountainTile extends Tile {
    public MountainTile() {
        super(100, 100, 100);
        this.type = TileType.Moutain;
    }
}
