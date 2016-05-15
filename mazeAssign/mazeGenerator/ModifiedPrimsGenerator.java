package mazeGenerator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import maze.Cell;
import maze.Maze;

public class ModifiedPrimsGenerator implements MazeGenerator {
	public static final Random random = new Random();
	private HashSet<Cell> Z;
	private HashSet<Cell> F;
	
	public ModifiedPrimsGenerator() {
		Z = new HashSet<Cell>();	
		F = new HashSet<Cell>();
	}
	
	public void generateMaze(Maze maze) {
		Cell cell = maze.map[random.nextInt(maze.sizeR)][random.nextInt(maze.sizeC)];
		Z.add(cell);
		
		getNeighbours(cell);
//		print();
		Cell randomCell = null;
		do {
			randomCell = returnRandomCell();
			getNeighbours(randomCell);
			removeIllegalCellsInF();
			F.remove(randomCell);
			carvePath(randomCell);
			}while(Z.size() < (maze.sizeC*maze.sizeR));
	} // end of generateMaze()

	private void removeIllegalCellsInF() {
		HashSet<Cell> illegalCells = new HashSet<Cell>();
		for(Cell z: Z) {
			for(Cell f: F) {
				if(z.r == f.r && z.c == f.c) {
					illegalCells.add(f);
				}
			}
		}
		F.removeAll(illegalCells);
	}

	private Cell returnRandomCell() {
		int item = -1;
		Cell randomCell = null;
		do {
			item = random.nextInt(F.size());
			int i = 0;
			for(Cell cell : F){
				if ((i == item) && (cell != null)) {
					randomCell = cell;
					break;
				}
				i = i + 1;
			}
			if(randomCell != null)
				break;
		}while(true);
			
		return randomCell;
	}

	private void carvePath(Cell randomCell) {
		HashMap<Integer,Cell> neighbours = getNeighboursFromZ(randomCell);
		Integer[] keyset = neighbours.keySet().toArray(new Integer[0]);
		
		int dir = random.nextInt(keyset.length);
		Cell newAdditionToZ = neighbours.get(keyset[dir]);
		randomCell.wall[keyset[dir]].present = false;
		Z.add(randomCell);
//		print();
	}

//	private void print() {
////		System.out.println("Contents of Z");
//		
//		for(Cell cell: Z) {
////			 System.out.print("Cell["+cell.r+"][" + cell.c+"]\t");
//		}
////		
//		if(F.size() > 0) {
//		for(Cell cell: F) {
//			 System.out.print("Cell["+cell.r+"][" + cell.c+"]\t");
//		}
//		}
//		else {
//			System.out.println("F empty");
//		}
//	}

	private void getNeighbours(Cell cell) {
		for(int i=0; i < cell.neigh.length; i++) {
			if(cell.neigh[i] != null) {
				F.add(cell.neigh[i]);
			}
		}		
	}
	
	private HashMap<Integer, Cell> getNeighboursFromZ(Cell c1) {
		Cell[] neighboursOfC1 = new Cell[c1.neigh.length];
		for(int i=0; i < c1.neigh.length; i++)
			neighboursOfC1[i] = c1.neigh[i];
		 		
		HashMap<Integer, Cell> list = new HashMap<Integer, Cell>();
		for(int i = 0; i < neighboursOfC1.length; i++){
			for(Cell cell: Z) {
				if(neighboursOfC1[i]!=null && neighboursOfC1[i].r == cell.r && neighboursOfC1[i].c == cell.c) {
					/*
					 * Cell "cell" is located in direction "i" of c1  
					 */
					list.put(new Integer(i), cell);
				}
			}
		}
		return list;
	}

} // end of class ModifiedPrimsGenerator
