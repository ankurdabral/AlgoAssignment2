package mazeGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import maze.Cell;
import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {
	private Maze maze;
	HashMap<Cell, Boolean> cells;
//	private boolean[] visited;
	private static int count = 0;
	public final static Random random = new Random();
	
	public RecursiveBacktrackerGenerator() {
		cells = new HashMap<Cell, Boolean>();
		
	}
	@Override
	public void generateMaze(Maze maze) {
		this.maze = maze;
		for(int i=0; i<maze.map.length; i++) {
			for(int j=0; j<maze.map[i].length; j++) {
				cells.put(maze.map[i][j], false);			
			}
		}
//		visited = new boolean[cells.size()+1];
//		for(boolean b: visited)
//			b= false;
		
		do {
		int row = random.nextInt(maze.sizeR);
		int column = random.nextInt(maze.sizeC);
		Cell cell = maze.map[row][column];
		if(cell == null)
			continue;
		buildPaths(cell);
		break;
		}while(true);
	} // end of generateMaze()

	public void buildPaths(Cell cell) {
		count++;
//		System.out.println(count + "started");
//		System.out.println("Cell ["+cell.r+"]["+cell.c+"]");
		cells.put(cell, true);
		ArrayList<Cell> neighboursList = new ArrayList<Cell>();
		
		for(Cell c: cell.neigh) {
			if(c!=null && cells.containsKey(c) && !cells.get(c)) {
				neighboursList.add(c);
			}
		}
		if(neighboursList.size() == 0) {
//			System.out.println(count + "ended");
			count--;
			return;
		}
		
		do{
			Cell neighbour = neighboursList.get(random.nextInt(neighboursList.size())); 
			if(cells.get(neighbour)){
				neighboursList.remove(neighbour);
				continue;
			}
			
			
			
			if(!cells.get(neighbour)){
				neighboursList.remove(neighbour);
				cells.put(neighbour, true);
				int dir = getDirection(cell, neighbour);
				System.out.println("Neighbour ["+neighbour.r+"]["+neighbour.c+"] direction " + dir);
				cell.wall[dir].present = false;
				buildPaths(neighbour);
			}
		}while(neighboursList.size() > 0);
		
		System.out.println(count + "ended");
		count--;
	}
		
	private int getDirection(Cell host, Cell neighbour) {
		for(int i = 0; i < host.neigh.length; i++) {
			if(host.neigh[i]!=null) {
				if(host.neigh[i].r == neighbour.r && host.neigh[i].c == neighbour.c)
					return i;
			}
		}
		return -1;
	}
} // end of class RecursiveBacktrackerGenerator
