import java.io.*;
public class AdventDay16 {
	public static void main(String[] args) {

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
		return map;
	}
}