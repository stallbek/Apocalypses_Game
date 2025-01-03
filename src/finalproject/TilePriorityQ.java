package finalproject;

import java.util.ArrayList;
import java.util.Arrays;


import finalproject.system.Tile;

public class TilePriorityQ {
	private ArrayList<Tile> heap;
	private int size;


	public TilePriorityQ (ArrayList<Tile> vertices) {
		this.heap = new ArrayList<Tile>();
		this.heap.add(null);
		this.size = 0;
		for (Tile t : vertices){
			if (t != null){
				this.add(t);
			}
		}
	}

	public int getSize(){
		return this.size;
	}

	public void add(Tile t){
		this.size = this.size + 1;
		this.heap.add(size, t);

		int i = size;
		while (i > 1 && this.heap.get(i).costEstimate < this.heap.get(i / 2).costEstimate){
			Tile temp = this.heap.get(i);
			this.heap.set(i, this.heap.get(i/2));
			this.heap.set(i/2, temp);
			i = i/2;
		}
	}

	public Tile removeMin() {
		Tile temp = this.heap.get(1);
		this.heap.set(1, heap.get(this.size));
		this.size--;
		downHeap(1, size);
		return temp;
	}

	public void downHeap(int start, int max){
		int i = start;

		while (2 * i <= max){
			int child = 2 * i;

			if (child < max) {
				if (heap.get(child + 1).costEstimate < heap.get(child).costEstimate){
					child = child + 1;
				}
			}
			if (heap.get(child).costEstimate < heap.get(i).costEstimate) {
				Tile temp = this.heap.get(i);
				this.heap.set(i, this.heap.get(child));
				this.heap.set(child, temp);
				i = child;
			}
			else {
				break;
			}
		}
	}

	public void updateKeys(Tile t, Tile newPred, double newEstimate) {

		t.predecessor = newPred;
		t.costEstimate = newEstimate;

		ArrayList<Tile> updatedHeap = new ArrayList<>();
		for (int i = 1; i <= size; i++){
			if (heap.get(i) != null){
				updatedHeap.add(heap.get(i));
			}
		}

		this.size = 0;
		this.heap = new ArrayList<>();
		this.heap.add(null);

		for (Tile tile : updatedHeap){
			this.add(tile);
		}

	}

	public void RELAX(Tile t, Tile newPred, double w) {
		if (newPred.costEstimate > t.costEstimate + w) {
			this.updateKeys(newPred, t, t.costEstimate + w);
		}
	}
}
