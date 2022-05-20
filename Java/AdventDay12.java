import java.io.*;
import java.util.*;
public class AdventDay12 {
	public static void main(String[] args) {
		System.out.printf("Part 1: %d%n", part1());
		System.out.printf("Part 2: %d%n", part2());
	}
	private static int part1() {
		String[][] data = getData();
		List<String> completePaths = new ArrayList<>();
		completePaths.add(",start");
		while (!isFinished(completePaths)) {
			List<String> tempPaths = new ArrayList<>();
			for (int path=0; path<completePaths.size(); path++) {
				String pathOn = completePaths.get(path).substring(completePaths.get(path).lastIndexOf(",")+1);
				if (pathOn.equals("end")) {
					tempPaths.add(completePaths.get(path));
					continue;
				}
				String[] paths = getPaths(data, pathOn);
				for (int j=0; j<paths.length; j++) {
					if (paths[j].equals(paths[j].toLowerCase()) && completePaths.get(path).contains(paths[j])) continue;
					tempPaths.add(completePaths.get(path)+","+paths[j]);
				}
			}
			completePaths = new ArrayList<>(tempPaths);
		}
		// for (int i=0; i<completePaths.size(); i++) System.out.println(completePaths.get(i));
		return completePaths.size();
	}
	private static int part2() {
		String[][] data = getData();
		List<String> completePaths = new ArrayList<>();
		completePaths.add(",start");
		while (!isFinished(completePaths)) {
			List<String> tempPaths = new ArrayList<>();
			for (String path : completePaths) {
				String pathOn = path.substring(path.lastIndexOf(",")+1);
				if (pathOn.equals("end")) {
					tempPaths.add(path);
					continue;
				}
				String[] paths = getPaths(data, pathOn);
				for (String p : paths) {
					if (canAdd(path, p)) tempPaths.add(path+","+p);
				}
			}
			completePaths = new ArrayList<>(tempPaths);
		}
		// for (int i=0; i<completePaths.size(); i++) System.out.println(completePaths.get(i));
		return completePaths.size();
	}
	private static boolean canAdd(String path, String cave) {
		if (!path.contains(cave)) return true;
		else if (cave.equals(cave.toUpperCase())) return true;
		else {
			String[] A_path = path.split(",");
			for (String p : A_path) {
				String tempPath = path;
				if (p.equals(p.toLowerCase()) && path.length() - tempPath.replace(p, "").length() > p.length()) return false;
			}
		} return true;
	}
	private static String[][] getData() {
		List<String> tempData = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./Inputs/inputDay12.txt")));
			String line;
			while ((line = reader.readLine()) != null) {
				tempData.add(line);
			}
		} catch (IOException e) { System.out.println(e); }

		String[][] data = new String[tempData.size()][2];
		for (int i=0; i<tempData.size(); i++) {
			data[i] = tempData.get(i).split("-");
		}
		return data;
	}
	private static String[] getPaths(String[][] data, String start) {
		List<String> paths = new ArrayList<>();
		for (int i=0; i<data.length; i++) {
			if (data[i][0].equals(start) && !data[i][1].equals("start")) paths.add(data[i][1]);
			if (data[i][1].equals(start) && !data[i][0].equals("start")) paths.add(data[i][0]);
		}
		if (paths.isEmpty()) return null;
		else return paths.toArray(new String[0]);
	}
	private static boolean isFinished(List<String> paths) {
		for (String path : paths) {
			String lastPath = path.substring(path.lastIndexOf(",")+1);
			if (!lastPath.equals("end")) return false;
		}
		return true;
	}
}