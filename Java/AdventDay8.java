import java.io.*;
import java.util.*;
public class AdventDay8 {
	public static void main(String[] args) {
		List<String> list;
		try {
			File file = new File("./Inputs/inputDay8.txt");
        	Scanner myScanner = new Scanner(file);
			list = new ArrayList<String>();
        	while (myScanner.hasNextLine()) list.add(myScanner.nextLine());
        	myScanner.close();
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
        String[][] input = new String[list.size()][10];
		String[][] output = new String[list.size()][4];
		for (int i=0; i<output.length; i++) {
			for (int j=0; j<output[1].length; j++) {
				output[i][j] = list.get(i).substring(list.get(i).indexOf("|")+2).split(" ")[j];
			}
		}
		for (int i=0; i<input.length; i++) {
			for (int j=0; j<input[1].length; j++) {
				input[i][j] = list.get(i).substring(0, list.get(i).indexOf("|")).split(" ")[j];
			}
		}
		int part1 = 0;
		int part2 = 0;
		for (int i=0; i<output.length; i++) {
			int codeDecifer = 0;
			for (int j=0; j<output[1].length; j++) {
				int num = getNum(output[i][j], input[i]);
				codeDecifer = (codeDecifer+num) * 10;
				if (num == 1 || num == 4 || num == 7 || num == 8) part1++;
			}
			part2 += (codeDecifer/10);
		}
		System.out.printf("Part 1: %d%n", part1);
		System.out.printf("Part 2: %d%n", part2);
	}
	public static int getNum(String code, String[] input) {
		int length = code.length();
		String sevenInLetters = "";
		for (int i=0; i<input.length; i++) {
			if (input[i].length() == 3) sevenInLetters = input[i];
		}
		String fourInLetters = "";
		for (int i=0; i<input.length; i++) {
			if (input[i].length() == 4) fourInLetters = input[i];
		}
		String oneInLetters = "";
		for (int i=0; i<input.length; i++) {
			if (input[i].length() == 2) oneInLetters = input[i];
		}
		int same = 0;
		if (length == 2) return 1;
		else if (length == 3) return 7;
		else if (length == 4) return 4;
		else if (length == 7) return 8;
		else if (length == 5) {
			same = 0;
			for (int i=0; i<sevenInLetters.length(); i++) {
				for (int j=0; j<5; j++) if (sevenInLetters.charAt(i) == code.charAt(j)) same++;
			}
			if (same == 3) return 3;
			same = 0;
			for (int i=0; i<fourInLetters.length(); i++) {
				for (int j=0; j<5; j++) if (fourInLetters.charAt(i) == code.charAt(j)) same++;
			}
			if (same == 2) return 2; else return 5;
		}
		else if (length == 6) {
			same = 0;
			for (int i=0; i<oneInLetters.length(); i++) {
				for (int j=0; j<6; j++) if (oneInLetters.charAt(i) == code.charAt(j)) same++;
			}
			if (same == 1) return 6;
			same = 0;
			for (int i=0; i<fourInLetters.length(); i++) {
				for (int j=0; j<6; j++) if (fourInLetters.charAt(i) == code.charAt(j)) same++;
			}
			if (same == 3) return 0; else return 9;
		} else return -1;
	}
}