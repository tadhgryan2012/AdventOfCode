import java.io.*;
import java.util.*;
public class AdventDay7 {
	public static void main(String[] args) {
		String[] stringArray;
		try {
			File file = new File("./Inputs/inputDay7.txt");
			Scanner myScanner = new Scanner(file);
			stringArray = myScanner.nextLine().split(",");
        	myScanner.close();
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
        int[] crabPos = new int[stringArray.length];
        for (int i=0; i<stringArray.length; i++) crabPos[i] = Integer.parseInt(stringArray[i]);

		System.out.printf("Part 1: %d%n", GetMinFuel(crabPos, true));
		System.out.printf("Part 2: %d%n", GetMinFuel(crabPos, false));
	}
	private static long GetMinFuel(int[] crabPos, boolean part1) {
		int max = 0;
		long fuel = 0;
		for (int i=0; i<crabPos.length; i++) if (crabPos[i] > max) max = crabPos[i];
		long minFuel = 2000000000;

		for (int i=0; i<max; i++) {
			fuel = 0;
			for (int j=0; j<crabPos.length; j++) {
				int tempfuel = Math.abs(crabPos[j] - i);
				if (!part1) for (int k=tempfuel-1; k>0; k--) tempfuel += k; 
				fuel += tempfuel;
			}
			if (minFuel > fuel) {
				minFuel = fuel;
			}
		}
		return minFuel;
	}
}