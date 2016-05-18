package mazeGenerator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import maze.Cell;
import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {
	private HashMap<Integer, Cell> visited;
	private HashMap<Integer, Cell> unvisited;
//	private HashSet<Cell> neighbours;
	private Maze maze;
	public final static Random random = new Random();
	
	public RecursiveBacktrackerGenerator() {
		visited = new HashMap<Integer, Cell>();
		unvisited = new HashMap<Integer,Cell>();
		
	}
	@Override
	public void generateMaze(Maze maze) {
		this.maze = maze;
//		for(int i = 0; i < maze.sizeR; i++) {
//			for(int j = 0; j < maze.sizeC; j++) {
//				unvisited.add(maze.map[i][j]);
//			}
//		}
		Cell cell = maze.map[random.nextInt(maze.sizeR)][random.nextInt(maze.sizeC)];
		visited.put(new Integer(((cell.r-1)*maze.sizeR)+cell.c), cell);
		
		carvePath(cell);

	} // end of generateMaze()

	private Cell getRandomNeighbour(Cell cell) {
		
	}
	private Cell carvePath(Cell cell) {
		Cell newCell = null;
		HashMap<Integer, Cell> list = new HashMap<Integer, Cell>();
		for(int i = 0; i < cell.neigh.length; i++) {
			if(cell.neigh[i]!=null && cell.wall[i].present == true) {
				list.put(new Integer(i), cell.neigh[i]);
			}
		}
		int randomDir = random.nextInt(list.keySet().size());
		int i = 0;
		int direction = -1;
		for(Integer dir: list.keySet()) {
			if(i == randomDir) {
				newCell = cell.neigh[dir];
				direction = dir;
			}
			i++;
		}
		
		list.remove(direction);
		cell.wall[direction].present = false;
		visited.put(new Integer(newCell.r*maze.sizeC + newCell.c),newCell);
//		HashSet<Cell> neigh = new HashSet<Cell>();
//		for(int i = 0; i < cell.neigh.length; i++) {
//			if(cell.neigh[i]!=null)
//				neigh.add(cell.neigh[i]);
//		}
//		Cell randomCell = null;
//		int flag = 0;
//		while(neigh.size()>0) {
//			int item = random.nextInt(neigh.size());
//			int i =0;
//			for(Cell c: neigh) {
//				if(i == item) {
//					randomCell = c;
//					flag = 1;
//					break;
//				}
//			}
//			if(flag == 1)
//				break;
//		}
//		
//		if(newCell == null)
//			return 
		
		
	}
	
} // end of class RecursiveBacktrackerGenerator
