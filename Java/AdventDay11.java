import java.io.*;
import java.util.*;
public class AdventDay11 {
	private static int[][] data;
	private static boolean[][] flash;
	private static int flashesAfter100;
	public static void main(String[] args) {
		List<String> list;
		try {
			File file = new File("./Inputs/inputDay11.txt");
			Scanner myScanner = new Scanner(file);
			list = new ArrayList<String>();
			while (myScanner.hasNextLine()) {
				list.add(myScanner.nextLine());
			}
			myScanner.close();
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		data = new int[list.get(0).length()][list.size()];
		flash = new boolean[data.length][data[1].length];
		for (int y=0; y<data[1].length; y++) {
			for (int x=0; x<data.length; x++) {
				data[x][y] = Character.getNumericValue(list.get(y).charAt(x));
				flash[x][y] = false;
			}
		}
		System.out.printf("Part 2: %d%n", Part2());
		System.out.printf("Part 1: %d%n", flashesAfter100);
	}
	private static int Part2() {
		int totalFlashes = 0;
		int flashesThatStep = 0;
		int step=1;
		while (flashesThatStep<(data[1].length*data.length)) {
			step++;
			totalFlashes += flashesThatStep;
			if (step == 102) flashesAfter100 = totalFlashes;
			flashesThatStep = 0;
			for (int y=0; y<data[1].length; y++) {
				for (int x=0; x<data.length; x++) {
					data[x][y]++;
				}
			}
			boolean flashAgain = true;
			while (flashAgain) {
				flashAgain = false;
				flashesThatStep += doFlash();
				for (int y=0; y<data[1].length; y++) {
					for (int x=0; x<data.length; x++) {
						if (data[x][y]>9 && !flash[x][y]) flashAgain = true;
					}
				}
			}
			for (int y=0; y<data[1].length; y++) {
				for (int x=0; x<data.length; x++) {
					if (flash[x][y]) data[x][y] = 0;
				}
			}
			for (int y=0; y<data[1].length; y++) {
				for (int x=0; x<data.length; x++) {
					flash[x][y] = false;
				}
			}
		}
		return step-1;
	}
	private static int doFlash() {
		int flashes = 0;
		for (int y=0; y<data[1].length; y++) {
			for (int x=0; x<data.length; x++) {
				if (data[x][y] > 9 && !flash[x][y]) {
					if (y+1<data[1].length) data[x][y+1]++;
					if (x+1<data.length && y+1<data[1].length) data[x+1][y+1]++;
					if (x+1<data.length) data[x+1][y]++;
					if (x+1<data.length && y-1>=0) data[x+1][y-1]++;
					if (y-1>=0) data[x][y-1]++;
					if (x-1>=0 && y-1>=0) data[x-1][y-1]++;
					if (x-1>=0) data[x-1][y]++;
					if (x-1>=0 && y+1<data[1].length) data[x-1][y+1]++;
					flash[x][y] = true;
					flashes++;
				}
			}
		}
		return flashes;
	}
}