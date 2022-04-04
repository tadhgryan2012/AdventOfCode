import java.io.*;
public class AdventDay15 {
	public static void main(String[] args) {
		int[][] input = getInput();
		int LENGTH = input.length;
		int[][] map = new int[LENGTH][LENGTH];

		map[0][0] = 0;
		
		for (int len=1; len<LENGTH; len++) {
			for (int y=0; y<len; y++) {
				if (y==0) map[len][y] = input[len][y] + map[len-1][y];
				else map[len][y] = input[len][y] + Math.min(map[len-1][y], map[len][y-1]);
			}
			for (int x=0; x<=len; x++) {
				if (x==0) map[x][len] = input[x][len] + map[x][len-1];
				else map[x][len] = input[x][len] + Math.min(map[x-1][len], map[x][len-1]);
			}
			checkAround(map, input, len);
		}
		for (int i=0; i<LENGTH; i++) {
			checkAround(map, input, LENGTH);
		}
		System.out.printf("Part 1: %d%n", map[LENGTH/5-1][LENGTH/5-1]);
		System.out.printf("Part 2: %d%n", map[LENGTH-1][LENGTH-1]);
	}
	private static void checkAround(int[][] map, int[][] input, int len) {
		int LENGTH = map.length;
		for (int y1=0; y1<len; y1++) {
			for (int x1=0; x1<len; x1++) {
				if (x1==0 && y1==0) continue;
				else if (x1==LENGTH-1 && y1==LENGTH-1) map[x1][y1] = input[x1][y1] + Math.min(map[x1-1][y1], map[x1][y1-1]);
				else if (x1==LENGTH-1 && y1==0)  map[x1][y1] = input[x1][y1] + Math.min(map[x1-1][y1], map[x1][y1+1]);
				else if (x1==0 && y1==LENGTH-1)  map[x1][y1] = input[x1][y1] + Math.min(map[x1+1][y1], map[x1][y1-1]);
				else if (y1==0) map[x1][y1] = input[x1][y1] + Math.min(map[x1-1][y1], Math.min(map[x1][y1+1], map[x1+1][y1]));
				else if (x1==0) map[x1][y1] = input[x1][y1] + Math.min(map[x1][y1-1], Math.min(map[x1+1][y1], map[x1][y1+1]));
				else if (x1==LENGTH-1) map[x1][y1] = input[x1][y1] + Math.min(map[x1-1][y1], Math.min(map[x1][y1-1], map[x1][y1+1]));
				else if (y1==LENGTH-1) map[x1][y1] = input[x1][y1] + Math.min(map[x1-1][y1], Math.min(map[x1][y1-1], map[x1+1][y1]));
				else map[x1][y1] = input[x1][y1] + Math.min(map[x1][y1-1], Math.min(map[x1+1][y1], Math.min(map[x1][y1+1], map[x1-1][y1])));
			}
		}
	}
	private static int[][] getInput() {
		int[][] map = new int[0][0];
		try {
			File file = new File("./Inputs/inputDay15.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str = reader.readLine();
			map = new int[str.length()][str.length()];
			for (int i=0; i<str.length(); i++) {
				map[i][0] = Character.getNumericValue(str.charAt(i));
			}
			int counter = 1;
			while ((str = reader.readLine()) != null) {
				for (int i=0; i<str.length(); i++) {
					map[i][counter] = Character.getNumericValue(str.charAt(i));
				}
				counter++;
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return expandMap(map);
	}
	private static int[][] addToMap(int[][] map, int add) {
		int LENGTH = map.length;
		int[][] newMap = new int[LENGTH][LENGTH];

		for (int y=0; y<map.length; y++) {
			for (int x=0; x<map.length; x++) {
				newMap[x][y] = map[x][y] + add;
			}
		}
		for (int y=0; y<LENGTH; y++) {
			for (int x=0; x<LENGTH; x++)
			if (newMap[x][y]>9) newMap[x][y] -= 9;
		}
		return newMap;
	}
	private static int[][] expandMap(int[][] map) {
		int LENGTH = map.length;
		int[][] tempMap = new int[LENGTH][LENGTH];
		int[][] newMap = new int[LENGTH*5][LENGTH*5];
		for (int y=0; y<LENGTH; y++){
			for (int x=0; x<LENGTH; x++) {
				tempMap[x][y] = map[x][y];
			}
		}
		
		for (int cols=0; cols<5; cols++) {
			for (int rows=0; rows<5; rows++) {
				tempMap = addToMap(map, rows+cols);

				for (int y=0; y<LENGTH; y++){
					for (int x=0; x<LENGTH; x++) {
						newMap[x+rows*LENGTH][y+cols*LENGTH] = tempMap[x][y];
					}
				}
			}
		}
		return newMap;
	}
}