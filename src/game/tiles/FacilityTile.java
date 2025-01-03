package game.tiles;

import game.system.Tile;
import game.system.TileType;

public class FacilityTile extends Tile {
    public FacilityTile() {
        super(1, 2, 0);
        this.type = TileType.Facility;
    }
}
