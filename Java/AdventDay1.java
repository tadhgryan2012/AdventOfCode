import java.io.*;
import java.util.*;
public class AdventDay1 {
    public static Integer[] Data() {
        List<Integer> list = new ArrayList<Integer>();
        try {
            File myObj = new File("./Inputs/inputDay1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                Integer data =  Integer.parseInt(myReader.nextLine());
                list.add(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        Integer[] array = new Integer[list.size()];
        for(int i = 0; i < list.size(); i++) array[i] = list.get(i);
        return array;
    }
    public static void main(String[] args) {
        System.out.println("Java");
        Integer[] data = Data();

        int count = 0;
        for (int i=1; i<data.length; i++) {
            if (data[i] > data[i-1]) { count++; }
        }
        System.out.printf("Part 1:%nIncreases %d times.%n", count);

        count = 0;
        for (int i=3; i<data.length; i++) {
            if (data[i] > data[i-3]) {count++;}
        }
        System.out.printf("Part 2:%nIncreases %d times.%n", count);
    }
}