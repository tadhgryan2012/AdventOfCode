import java.io.*;
import java.util.*;
public class AdventDay14 {
	public static HashMap<String, Character> map = new HashMap<>();
	public static HashMap<String, Long> polymerHashMap = new HashMap<>();
	public static List<Character> polymer = new ArrayList<>();
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		try {
			File file = new File("./Inputs/inputDay14.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String S_polymer = reader.readLine();
			for (int letter=0; letter<S_polymer.length(); letter++) polymer.add(S_polymer.charAt(letter));
			String str;
			while ((str = reader.readLine()) != null) {
				if (!str.isBlank()) list.add(str);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		for (int line=0; line<list.size(); line++) {
			String str = list.get(line);
			String chars = String.valueOf(str.charAt(0)) + str.charAt(1);
			map.put(chars, str.charAt(6));
		}
		for (int letter=0; letter<polymer.size()-1; letter++) {
			String chain = String.valueOf(polymer.get(letter))+polymer.get(letter+1);
				Long c = polymerHashMap.get(chain);
				if (c == null) c = 0L;
				polymerHashMap.put(chain, c+1L);	
		}
		System.out.printf("Part 1: %d%n", answers(10));
		System.out.printf("Part 2: %d%n", answers(30));
	}
	public static long answers(int times) {
		for (int step=1; step<=times; step++) {
			HashMap<String, Long> tempPolymerHashMap = new HashMap<>();
			Object[] polymers = polymerHashMap.keySet().toArray();
			for (int i=0; i<polymerHashMap.size(); i++) {
				String polymer = (String) polymers[i];
				Long num = polymerHashMap.get(polymer);
				if (num > 0) {
					Long value1 = polymerHashMap.get(polymer);
					String polStrand1 = polymer.charAt(0) + map.get(polymer).toString();
					if (tempPolymerHashMap.get(polStrand1) != (null)) value1 += tempPolymerHashMap.get(polStrand1);
					tempPolymerHashMap.put(polStrand1, value1);
					
					Long value2 = polymerHashMap.get(polymer);
					String polStrand2 = map.get(polymer).toString() + polymer.charAt(1);
					if (tempPolymerHashMap.get(polStrand2) != (null)) value2 += tempPolymerHashMap.get(polStrand2);
					tempPolymerHashMap.put(polStrand2, value2);
					num--;
				}
			}
			polymerHashMap = new HashMap<>(tempPolymerHashMap);
		}

		HashMap<Character, Long> letterAmounts = new HashMap<>();
		Object[] polymers = polymerHashMap.keySet().toArray();
		for (int i=0; i<polymerHashMap.size(); i++) {
			String polymer = (String) polymers[i];
			Long num = polymerHashMap.get(polymer);

			Long value1 = letterAmounts.get(polymer.charAt(0));
			if (value1==null) value1 = 0L;
			letterAmounts.put(polymer.charAt(0), value1+num);

			Long value2 = letterAmounts.get(polymer.charAt(1));
			if (value2==null) value2 = 0L;
			letterAmounts.put(polymer.charAt(1), value2+num);
		}
		letterAmounts.put(polymer.get(0), letterAmounts.get(polymer.get(0))+1);
		letterAmounts.put(polymer.get(polymer.size()-1), letterAmounts.get(polymer.get(polymer.size()-1))+1);

		Object[] letters = letterAmounts.keySet().toArray();
		for (int i=0; i<letterAmounts.size(); i++) {
			char letter = (char) letters[i];
			Long num = letterAmounts.get(letter);
			letterAmounts.put(letter, num/2);
		}

		ArrayList<Long> letterAmountsList = new ArrayList<>(letterAmounts.values());
		letterAmountsList.sort(null);
		long max, min, answer;
		max = letterAmountsList.get(letterAmountsList.size()-1);
		min = letterAmountsList.get(0);
		answer = max - min;
		return answer;
	}
}