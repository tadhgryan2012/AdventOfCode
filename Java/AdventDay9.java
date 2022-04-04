import java.io.*;
import java.util.*;
public class AdventDay9 {
	private static int[][] heightMap = new int[100+2][100+2];
	public static void main(String[] args) {
		List<String> list;
		try {
			File file = new File("./Inputs/inputDay9.txt");
			Scanner myScanner = new Scanner(file);
			list = new ArrayList<String>();
			while (myScanner.hasNextLine()) list.add(myScanner.nextLine());
			myScanner.close();
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		for (int y=0; y<heightMap[1].length; y++) {		// Adds a border of 9's to the map
			for (int x=0; x<heightMap.length; x++) {
				heightMap[x][y] = 9;
			}
		}
		for (int y=0; y<list.size(); y++) {				// Maps the data to a 2d array
			for (int x=0; x<list.get(0).length(); x++) {
				heightMap[x+1][y+1] = Character.getNumericValue(list.get(y).charAt(x));
			}
		}
		System.out.printf("Part 1: %d%n", Part1());
		System.out.printf("Part 2: %d%n", Part2());
	}
	private static int Part1() {
		int riskLevel = 0;
		for (int y=1; y<heightMap[1].length-1; y++) {
			for (int x=1; x<heightMap.length-1; x++) {
				if (heightMap[x][y]<heightMap[x+1][y] && heightMap[x][y]<heightMap[x-1][y] && heightMap[x][y]<heightMap[x][y+1] && heightMap[x][y]<heightMap[x][y-1]) {
					riskLevel += heightMap[x][y] + 1;
				}
			}
		}
		return riskLevel;
	}
	private static int Part2() {
		List<Integer> basins = new ArrayList<Integer>();
		for (int y=1; y<heightMap[1].length-1; y++) {
			for (int x=1; x<heightMap.length-1; x++) {
				if (heightMap[x][y] != 9) basins.add(basinSize(x, y));
			}
		}
		int first = 0, second = 0, third = 0;
		for (int i : basins) {
			if (i > third) {
				if (i > second) {
					if (i > first) {
						third = second;
						second = first;
						first = i;
					} else second = i;
				} else third = i;
			}
		}
		return first * second * third;
	}
	private static int basinSize(int x, int y) {
		int size = 0;
		size++;
		heightMap[x][y] = 9;
		while (heightMap[x][y+1] != 9) size += basinSize(x, y+1);
		while (heightMap[x+1][y] != 9) size += basinSize(x+1, y);
		while (heightMap[x][y-1] != 9) size += basinSize(x, y-1);
		while (heightMap[x-1][y] != 9) size += basinSize(x-1, y);
		return size;
	}
}