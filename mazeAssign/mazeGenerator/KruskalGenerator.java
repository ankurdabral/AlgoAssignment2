package mazeGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;

import maze.Cell;
import maze.Maze;

public class KruskalGenerator implements MazeGenerator {
	private final static Random random = new Random();
	private HashSet<Edge> edges;
	private HashSet<TreeMap<Integer, Cell>> trees;
	private TreeMap<Integer, Cell> treeMap;
	private Maze maze;
	private HashSet<Edge> set;
	
	public KruskalGenerator() {
		edges = new HashSet<Edge>();
		this.treeMap = new TreeMap<Integer, Cell>();
		trees = new HashSet<TreeMap<Integer, Cell>>();
	}
	
	@Override
	public void generateMaze(Maze maze) {
		this.maze = maze;
		initialSetup(); //step-1
		Edge edge = null;
		do{
			set = new HashSet<Edge>();
			edge = selectRandomEdge();
			if(joinTwoTrees(edge))
				break;
		}while(edges.size() != 0);
		
	} // end of generateMaze()

	private boolean joinTwoTrees(Edge edge) {
		
	}
	
	private void initialSetup() {
		Cell[][] map = maze.map;
		int index = 0;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				treeMap = new TreeMap<Integer, Cell>();
				treeMap.put(new Integer(index), map[i][j]);
				trees.add(treeMap);
				index++;
				for(int k = 0; k < maze.WEST; k++) {
					if(map[i][j].neigh[k]!=null) {
						edges.add(new Edge(map[i][j], map[i][j].neigh[k], k));
					}
				}
			}
		}
	}

	private Edge selectRandomEdge() {
		int item;
		Edge selectedEdge = null;
		int i = 0;
		do {
			int flag = 0;
			item = random.nextInt(edges.size());
			for(Edge edge: edges) {
				if(i == item) {
					selectedEdge = edge;
					flag = 1;
					break;
				}
				if(flag == 1)
					break;
			}
		}while(i < edges.size());
		
		if(selectedEdge != null)
			edges.remove(selectedEdge);
		
		return selectedEdge;
	}
	
//	private void carvePath(Edge edge) {
//		Cell c1 = edge.cell1;
//		Cell c2 = edge.cell2;
//		
//		int index = 0;
//		int i = 0;
//		for(TreeMap<Integer, Cell> map1: trees) {
//			for(TreeMap<Integer, Cell> map2: trees) {
//				compareMaps(map1, map2, c1, c2);
//			}
//		}
//	}
//
//	private void compareMaps(TreeMap<Integer, Cell> map1, TreeMap<Integer, Cell> map2, Cell c1, Cell c2) {
//		int flag = 0;
//		for(Cell cell: map1.values()) {
//			if(cell.r == c1.r && cell.c == c1.c) {
//				flag++;
//			}
//			if(cell.r == c2.r && cell.c == c2.c) {
//				flag++;
//			}
//		}
//		
//		
//		
//	}



	private class Edge {
		Cell cell1;
		Cell cell2;
		int dir;
		public Edge(Cell c1, Cell c2, int dir) {
			cell1 = c1;
			cell2 = c2;
			this.dir = dir;
		}
	}
} // end of class KruskalGenerator
