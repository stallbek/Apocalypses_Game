package game.tiles;

import game.system.Tile;
import game.system.TileType;

public class ZombieInfectedRuinTile extends Tile {
    public ZombieInfectedRuinTile() {
        super(1, 3, 5);
        this.type = TileType.ZombieInfectedRuin;
    }
}
