package finalproject;

import java.util.ArrayList;

import finalproject.system.Tile;

public class Graph {
	private ArrayList<Tile> vertices;
    private ArrayList<Edge> edges;


	public Graph(ArrayList<Tile> vertices) {
		this.vertices = vertices;
        this.edges = new ArrayList<Edge>();
	}

    public ArrayList<Tile> getVertices(){
        return this.vertices;
    }

    public void addVertice(Tile vertice){
        this.vertices.add(vertice);
    }
	

    public void addEdge(Tile origin, Tile destination, double weight){
    	this.edges.add(new Edge(origin, destination, weight));
    }
    

	public ArrayList<Edge> getAllEdges() {
		return this.edges;
	}



	public ArrayList<Tile> getNeighbors(Tile t) {
        for (Edge e : this.edges){
            e.adje(t);
        }

        return t.adjacentTiles;
    }

    public double computePathCost(ArrayList<Tile> path) {
        double totalCost = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            Tile cur = path.get(i);
            Tile next = path.get(i + 1);

            double weight = 0.0;
            boolean found = false;

            for (Edge e : this.edges) {
                if (e.origin.equals(cur) && e.destination.equals(next)) {
                    weight = e.weight;
                    found = true;
                    break;
                }
            }

            totalCost += weight;
        }
        return totalCost;
    }

    public static class Edge{
    	Tile origin;
    	Tile destination;
    	double weight;


        public Edge(Tile s, Tile d, double cost){
        	origin = s;
            destination = d;
            weight = cost;
        }

        public  void adje(Tile t){
            if (t == null) {
                return ;
            }
            if (this.origin.equals(t)) {
                t.adjacentTiles.add(this.destination);
            }
        }



        public Tile getStart(){
            return this.origin;
        }

        

        public Tile getEnd() {
            return this.destination;
        }
        
    }
    
}