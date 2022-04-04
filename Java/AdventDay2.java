import java.io.*;
import java.util.*;
public class AdventDay2 {
    public static String[] Data() {
        List<String> list = new ArrayList<String>();
        try {
            File myObj = new File("./Inputs/inputDay2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data =  myReader.nextLine();
                list.add(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        String[] array = new String[list.size()];
        for(int i = 0; i < list.size(); i++) array[i] = list.get(i);
        return array;
    }
    public static void main(String[] args) {
        String[] data = Data();
        int horizontalPos = 0;
        int depth = 0;
        int aim;
        for (String i : data) {
            if (i.startsWith("forward")) horizontalPos += Integer.parseInt(i.substring(i.length()-1));
            if (i.startsWith("up")) depth -= Integer.parseInt(i.substring(i.length()-1));
            if (i.startsWith("down"))  depth += Integer.parseInt(i.substring(i.length()-1));
        }
        System.out.printf("Part 1 = %d%n", horizontalPos*depth);
        horizontalPos = 0;
        depth = 0;
        aim = 0;
        for (String i : data) {
            if (i.contains("forward")) {
                int num = Integer.parseInt(i.substring(i.length()-1));
                horizontalPos += num;
                depth += aim*num;
            }
            if (i.contains("up")) {
                aim -= Integer.parseInt(i.substring(i.length()-1));
            }
            if (i.contains("down")) {
                aim += Integer.parseInt(i.substring(i.length()-1));
            }
        }
        System.out.printf("Part 2 = %d%n", horizontalPos*depth);
    }
}