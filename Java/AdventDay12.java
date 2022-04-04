import java.io.*;
import java.util.*;
public class AdventDay12 {
	public static String[][] data;
	public static void main(String[] args) {
		List<String> list;
		try {
			File file = new File("./Inputs/inputDay12.txt");
			Scanner myScanner = new Scanner(file);
			list = new ArrayList<>();
			while (myScanner.hasNextLine()) list.add(myScanner.nextLine());
			myScanner.close();
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		data = new String[list.size()][2];
		for (int line=0; line<data.length; line++) {
			String input = list.get(line);
			data[line][0] = input.substring(0, input.indexOf("-"));
			data[line][1] = input.substring(input.indexOf("-")+1);
		}
		List<List<String>> paths = GetLists();
		List<Integer> pathsToRemove = new ArrayList<>();

		for (int i=0; i<paths.size(); i++) {
			List<String> tempList = new ArrayList<>();
			for (int j=1; j<paths.get(i).size(); j++) {
				if (paths.get(i).get(j).equals("end")) continue;
				if (Character.isLowerCase(paths.get(i).get(j).charAt(0))) {
					tempList.add(paths.get(i).get(j));
				}
				if (!tempList.isEmpty()) {
					for (int k=0; k<tempList.size(); k++) {
						if (tempList.get(k).equals(paths.get(i).get(j))) {
							pathsToRemove.add(i);
							break;
						}
					}
				}
			}
		}
		pathsToRemove.sort(null);
		for (int i=pathsToRemove.size()-1; i>-1; i--) {
			paths.remove(pathsToRemove.get(i));
		}

		System.out.println("=".repeat(30));
		for (int i=0; i<paths.size(); i++) System.out.printf("%2d: %s%n", i, paths.get(i));
		System.out.println("=".repeat(30));
	}
	public static List<List<String>> GetLists() {
		List<List<String>> listOfLists = new ArrayList<>();
		List<String> tempList = new ArrayList<>();
		tempList = GetPaths("start");
		for (int i=0; i<tempList.size(); i++) {
			listOfLists.add(Arrays.asList("start", tempList.get(i)));
		}
		for (int times=0; times<4; times++) {
			int listOfListsSize = listOfLists.size();
			List<List<String>> tempListOfLists = new ArrayList<>();
			for (int i=0; i<listOfListsSize; i++) {
				List<String> tempElements = listOfLists.get(i);
				List<String> elements = new ArrayList<>();
				elements.addAll(tempElements);
				if (!elements.get(elements.size()-1).equals("end")) {
					List<String> pathsList = GetPaths(elements.get(elements.size()-1));
					List<String> elements2 = new ArrayList<>(elements);
					for (int j=0; j<pathsList.size(); j++) {
						elements2 = new ArrayList<>(elements);
						elements2.add(pathsList.get(j));
						tempListOfLists.add(elements2);
					}
				} else {
					tempListOfLists.add(elements);
				}
			}
			listOfLists = new ArrayList<>(tempListOfLists);
		}
		return listOfLists;
	}
	public static List<String> GetPaths(String input) {
		List<String> list = new ArrayList<>();
		for (int line=0; line<data.length; line++) {
			if (data[line][0].equals(input)) {
				if (!data[line][1].equals("start")) list.add(data[line][1]);
			}
			if (data[line][1].equals(input)) {
				if (!data[line][0].equals("start")) list.add(data[line][0]);
			}
		}
		return list;
	}
}