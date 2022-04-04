import java.io.*;
import java.util.*;
public class AdventDay10 {
	public static void main(String[] args) {
		List<String> list;
		try {
			File file = new File("./Inputs/inputDay10.txt");
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
		String[] data = new String[list.size()];
		for (int i=0; i< list.size(); i++) data[i] = list.get(i);
		List<String> list2 = new ArrayList<String>();
		Stack<Character> charStack = new Stack<>();
		int points = 0;
		for (int line=0; line<data.length; line++) {
			charStack.clear();
			char bracket = '\0';
			boolean flag = false;
			for (int i=0; i<data[line].length(); i++) {
				bracket = data[line].charAt(i);
				if (bracket == '(') charStack.push(')');
				else if (bracket == '[') charStack.push(']');
				else if (bracket == '{') charStack.push('}');
				else if (bracket == '<') charStack.push('>');
				else if (bracket == ')' || bracket == ']' || bracket == '}' || bracket == '>') {
					if (bracket != charStack.peek()) {
						if (bracket == ')') points += 3;
						else if (bracket == ']') points += 57;
						else if (bracket == '}') points += 1197;
						else if (bracket == '>') points += 25137;
						flag = true;
						break;
					}
					else charStack.pop();
				}
			}
			if (!flag) {
				list2.add(data[line]);
			}
		}
		String[] data2 = new String[list2.size()];
		for (int i=0; i<data2.length; i++) {
			data2[i] = list2.get(i);
		}
		Stack<Integer> intStack = new Stack<>();
		long[] scoreArray = new long[list2.size()];
		for (int line=0; line<data2.length; line++) {
			char bracket = '\0';
			intStack.clear();
			long score = 0;
			for (int i=0; i<data2[line].length(); i++) {
				bracket = data2[line].charAt(i);
				if (bracket == '(') intStack.push(1);
				else if (bracket == '[') intStack.push(2);
				else if (bracket == '{') intStack.push(3);
				else if (bracket == '<') intStack.push(4);
				else if (bracket == ')' || bracket == ']' || bracket == '}' || bracket == '>') intStack.pop();
			}
			while (!intStack.empty()) {
				score *= 5;
				score += intStack.pop();
			}
			scoreArray[line] = score;
		}
		Arrays.sort(scoreArray);
		System.out.printf("Part 1: %d%n", points);
		System.out.printf("Part 2: %d%n", scoreArray[scoreArray.length/2]);
	}
}