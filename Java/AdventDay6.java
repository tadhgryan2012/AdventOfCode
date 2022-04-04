import java.io.*;
import java.util.*;
public class AdventDay6 {
    final static int breedRate = 6;
    final static int adulthood = 2;
    public static void main(String[] args) {
        System.out.printf("Part 1: %d%n", Track(GetFishDaysLeft(), 80));
        System.out.printf("Part 2: %d%n", Track(GetFishDaysLeft(), 256));
    }
    private static long[] GetFishDaysLeft() {
        String[] stringArray;
        try {
            File file = new File("./Inputs/inputDay6.txt");
            Scanner myScanner = new Scanner(file);
            stringArray = myScanner.nextLine().split(",");
            myScanner.close();
        } catch (IOException e) {
            System.out.println(e);
            long[] empty = new long[0];
            return empty;
        }
        int[] allFish = new int[stringArray.length];
        for (int i=0; i<stringArray.length; i++) allFish[i] = Integer.parseInt(stringArray[i]);
        long[] fishDaysLeft = new long[breedRate+adulthood+1];
        for (int i : allFish) {
            fishDaysLeft[i]++;
        }
        return fishDaysLeft;
    }
    private static long Track(long[] fishDaysLeft, int days) {
        long fishCount = 0;
        for (int i=0; i<fishDaysLeft.length; i++) fishCount += fishDaysLeft[i];
        for (int day=1; day<=days; day++) {
            long newFish = fishDaysLeft[0];
            for (int i=0; i<fishDaysLeft.length-1; i++) {
                fishDaysLeft[i] = fishDaysLeft[i+1];
            }
            fishDaysLeft[breedRate] += newFish;
            fishDaysLeft[breedRate+adulthood] = newFish;
        }
        fishCount = 0;
        for (int i=0; i<fishDaysLeft.length; i++) fishCount += fishDaysLeft[i];
        return fishCount;
    }
}