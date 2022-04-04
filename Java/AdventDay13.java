import java.io.*;
import java.util.*;
public class AdventDay13 {
	public static void main(String[] args) {
		List<String> pointList;
		List<String> foldList;
		try {
			File file = new File("./Inputs/inputDay13.txt");
			Scanner myScanner = new Scanner(file);
			pointList = new ArrayList<>();
			foldList = new ArrayList<>();
			while (myScanner.hasNextLine()) {
				String str = myScanner.nextLine();
				if (!str.isEmpty()) {
					if (str.startsWith("fold along")) foldList.add(str.substring(str.lastIndexOf(" ")+1));
					else pointList.add(str);
				}
			}
			myScanner.close();
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		int[][] points = new int[pointList.size()][2];
		String[] foldPoints = new String[foldList.size()];
		int[][] grid = new int[1311][1311]; //1310
		for (int line=0; line<points.length; line++) {
			String str = pointList.get(line);
			points[line][0] = Integer.parseInt(str.substring(0, str.indexOf(",")));
			points[line][1] = Integer.parseInt(str.substring(str.indexOf(",")+1));
		}

		for (int i=0; i<foldPoints.length; i++) {
			foldPoints[i] = foldList.get(i);
		}
		for (int line=0; line<points.length; line++) grid[points[line][0]][points[line][1]] = 1;
		for (int y=0; y<grid[1].length; y++) {
			for (int x=0; x<grid.length; x++) {
			}
		}

		for (int folds=0; folds<foldPoints.length; folds++) {
			String str = foldPoints[folds];
			int foldpoint = Integer.parseInt(str.substring(str.indexOf("=")+1));
			int[][] tempGrid;
			if (str.startsWith("x")) {
				tempGrid = new int[foldpoint][grid[1].length];
				for (int y=0; y<grid[1].length; y++) {
					for (int x=0; x<foldpoint; x++) {
						tempGrid[x][y] = grid[x][y] + grid[foldpoint+(foldpoint-x)][y];
					}
				}
			} else {
				tempGrid = new int[grid.length][foldpoint];
				for (int y=0; y<foldpoint; y++) {
					for (int x=0; x<grid.length; x++) {
						tempGrid[x][y] = grid[x][y] + grid[x][foldpoint+(foldpoint-y)];
					}
				}
			}
			grid = tempGrid;
			if (folds == 0) System.out.printf("Part 1: %d%n", CountDots(grid));
		}
		PrintMap(grid);
	}
	private static int CountDots(int[][] grid) {
		int counter = 0;
		for (int y=0; y<grid[1].length; y++) {
			for (int x=0; x<grid.length; x++) {
				if (grid[x][y] > 0) counter++;
			}
		}
		return counter;
	}
	private static void PrintMap(int[][] grid) {
		System.out.println("Part 2: ");
		char[][] map = new char[40][6];
		for (int y=0; y<grid[1].length; y++) {
			for (int x=0; x<grid.length; x++) {
				if (grid[x][y] > 0) {
					map[x][y] = '#';
				} else map[x][y] = '.';
				System.out.printf("%2c", map[x][y]);
			}
			System.out.println();
		}
	}
}