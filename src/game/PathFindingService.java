package game;

import game.system.Tile;
import game.tiles.MetroTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import static game.GraphTraversal.BFS;

public abstract class PathFindingService {
	Tile source;
	Graph g;
    private String cost;


	public PathFindingService(Tile start) {
    	this.source = start;
    }

    public void graphic(String cost) {
        this.cost = cost;

        if (cost.equals("distance")){
            ArrayList<Tile> reachableTiles = BFS(source);
            HashMap<Tile, Boolean> reachCheck = new HashMap<>();
            Graph graph = new Graph(new ArrayList<>());

            for (Tile t : reachableTiles) {
                graph.addVertice(t);
            }

            for (Tile t: reachableTiles){
                reachCheck.put(t, true);
            }

            for (Tile t : reachableTiles){
                for (Tile adj : t.adjacentTiles){
                    if (adj != null && adj.isWalkable() && reachCheck.containsKey(adj)){
                        if (t instanceof MetroTile && adj instanceof MetroTile){
                            graph.addEdge(t, adj, ((MetroTile) adj).metroDistanceCost);
                        }
                        else {
                            graph.addEdge(t, adj, adj.distanceCost);
                        }
                    }
                }
            }
            this.g = graph;
        }

        else if (cost.equals("time")){
            ArrayList<Tile> reachableTiles = BFS(source);
            HashMap<Tile, Boolean> reachCheck = new HashMap<>();
            Graph graph = new Graph(new ArrayList<>());

            for (Tile t : reachableTiles) {
                graph.addVertice(t);
            }

            for (Tile t: reachableTiles){
                reachCheck.put(t, true);
            }

            for (Tile t : reachableTiles){
                for (Tile adj : t.adjacentTiles){
                    if (adj != null && adj.isWalkable() && reachCheck.containsKey(adj)){
                        if (t instanceof MetroTile && adj instanceof MetroTile){
                            graph.addEdge(t, adj, ((MetroTile) adj).metroTimeCost);
                        }
                        else {
                            graph.addEdge(t, adj, adj.timeCost);
                        }
                    }
                }
            }
            this.g = graph;
        }
    }


	public abstract void generateGraph();

    private void INIT_SINGLE_SOURCE(ArrayList<Tile> V, Tile s){
        for (Tile v : V) {
            v.costEstimate = 999999999999999999999999999999999999999999999999999999.99;
            v.predecessor = null;
        }
        s.costEstimate = 0.0;
    }


    public ArrayList<Tile> findPath(Tile startNode) {
        ArrayList<Tile> vertices = g.getVertices();
        Tile destination = this.getDestination();
        ArrayList<Tile> optimaPath = new ArrayList<Tile>();

        if (vertices.isEmpty()) {
            return new ArrayList<>();
        }

        INIT_SINGLE_SOURCE(vertices, startNode);
        TilePriorityQ heap = new TilePriorityQ(vertices);

        while (heap.getSize() != 0) {
            Tile u = heap.removeMin();

            for (Tile v : u.adjacentTiles) {
                if (v != null && v.isWalkable()) {
                    if (cost.equals("distance")){
                        heap.RELAX(u , v, v.distanceCost);
                    }
                    else if (cost.equals("time")){
                        heap.RELAX(u , v, v.timeCost);
                    }

                }
            }
        }
        return PredPath(destination, optimaPath);
    }

    private ArrayList<Tile> PredPath(Tile d, ArrayList<Tile> path){
        Tile cur = d;

        while (cur != null) {
            path.add(0, cur);
            cur = cur.predecessor;
        }
        return path;
    }

    private Tile getDestination(){
        ArrayList<Tile> vertices = this.g.getVertices();

        for (Tile t : vertices){
            if (t.isDestination){
                return t;
            }
        }
        return null;
    }

    public ArrayList<Tile> findPath(Tile start, Tile end) {
        ArrayList<Tile> vertices = g.getVertices();
        ArrayList<Tile> optimaPath = new ArrayList<Tile>();


        if (vertices.isEmpty()) {
            return new ArrayList<>();
        }

        INIT_SINGLE_SOURCE(vertices, start);
        TilePriorityQ heap = new TilePriorityQ(vertices);
        while (heap.getSize() != 0) {
            Tile u = heap.removeMin();

            for (Tile v : u.adjacentTiles) {
                if (v != null && v.isWalkable()) {
                    if (cost.equals("distance")){
                        heap.RELAX(u , v, v.distanceCost);
                    }
                    else if (cost.equals("time")){
                        heap.RELAX(u , v, v.timeCost);
                    }
                }
            }
        }
        return PredPath(end, optimaPath);
    }

    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
    	waypoints.addLast(this.getDestination());
        ArrayList<Tile> result = new ArrayList<Tile>();

        Tile point1 = start;
        Tile point2 = null;

        while (!waypoints.isEmpty()){
            point2 = waypoints.removeFirst();
            ArrayList<Tile> list = findPath(point1, point2);

            if (!result.isEmpty() && !list.isEmpty()){
                Tile last = result.get(result.size() - 1);
                Tile first = list.get(0);

                if (last.equals(first)) {
                    list.remove(0);
                }
            }

            result = listAdd(result, list);
            point1 = point2;
        }

        return result;
    }

    public static ArrayList<Tile> listAdd(ArrayList<Tile> arr1, ArrayList<Tile> arr2){
        ArrayList<Tile> combo = new ArrayList<>(arr1.size() + arr2.size());

        combo.addAll(arr1);
        combo.addAll(arr2);
        return combo;
    }
        
}

