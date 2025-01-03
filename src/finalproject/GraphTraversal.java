package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphTraversal
{


	public static ArrayList<Tile> BFS(Tile s) {
		LinkedList<Tile> qu = new LinkedList<>();
		ArrayList<Tile> result = new ArrayList<>();
		HashSet<Tile> visited = new HashSet<>();

		visited.add(s);
		qu.addLast(s);

		Tile cur;
		while (!qu.isEmpty()){
			cur = qu.removeFirst();
			result.add(cur);

			for (Tile tile : cur.adjacentTiles){
				if (tile != null && tile.isWalkable() && !visited.contains(tile)){
					visited.add(tile);
					qu.addLast(tile);
				}
			}
		}

		return result;
	}


	public static ArrayList<Tile> DFS(Tile s) {
		LinkedList<Tile> stack = new LinkedList<>();
		ArrayList<Tile> result = new ArrayList<>();
		HashSet<Tile> visited = new HashSet<>();


		visited.add(s);
		stack.addFirst(s);

		Tile cur;
		while (!stack.isEmpty()){
			cur = stack.removeFirst();
			result.add(cur);

			for (Tile tile : cur.adjacentTiles){
				if(tile != null && tile.isWalkable() && !visited.contains(tile)){
					visited.add(tile);
					stack.addFirst(tile);
				}

			}
		}
		return result;
	}





}  