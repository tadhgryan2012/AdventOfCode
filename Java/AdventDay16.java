import java.io.*;
public class AdventDay16 {
	public static void main(String[] args) {
		System.out.printf("Part 1: %d%n", part1());
	}
	private static String getInput() {
		String input;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Inputs/inputDay16.txt")));
			input = reader.readLine();
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return input;
	}
	private static int part1() {
		String data = getInput();
		
	}
}