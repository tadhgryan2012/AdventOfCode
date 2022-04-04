import java.io.*;
import java.util.*;
public class AdventDay3 {
    public static String[] Data() {
        List<String> list = new ArrayList<String>();
        try {
            File myObj = new File("./Inputs/inputDay3.txt");
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

        int[] bits = new int[12];

        for (String i : data) {
            for (int j=0; j<i.length(); j++) {
                bits[j] += Integer.parseInt(String.valueOf(i.charAt(j)));
            }
        }
        int halfWayPoint = data.length / 2;
        String gammaRate = "";
        String epsilonRate = "";
        for (int i : bits) {
            if (i > halfWayPoint) { 
                gammaRate += "1";
                epsilonRate += "0";
            } else { 
                gammaRate += "0";
                epsilonRate += "1";
            }
        }
        int gammaRateNum = Integer.parseInt(gammaRate, 2);
        int epsilonRateNum = Integer.parseInt(epsilonRate, 2);
        System.out.printf("Part 1: %d%n", gammaRateNum*epsilonRateNum);
        int OxygenGeneratorRating = Integer.parseInt(Ratings(data, 0, true)[0], 2);
        int CO2ScrubberRating = Integer.parseInt(Ratings(data, 0, false)[0], 2);
        System.out.printf("Part 2: %d%n", OxygenGeneratorRating*CO2ScrubberRating);
    }
    public static String[] Ratings(String[] data, int position, Boolean oxygen) {
        if (data.length > 1) {
            List<String> newDataList = new ArrayList<String>();
            int bits = 0;
            char filterBit;
            for (String i : data) {
                bits += Integer.parseInt(String.valueOf(i.charAt(position)));
            }
            if (oxygen) {
                if (bits >= (double) data.length / 2) {
                    filterBit = '1';
                } else {
                    filterBit = '0';
                }
            } else {
                if (bits < (double) data.length / 2) {
                    filterBit = '1';
                } else {
                    filterBit = '0';
                }
            }
            for (String i : data) {
                if (i.charAt(position) == filterBit) {
                    newDataList.add(i);
                }
            }

            String[] newData = new String[newDataList.size()];
            for(int i = 0; i < newDataList.size(); i++) newData[i] = newDataList.get(i);
            return Ratings(newData, position+1, oxygen);
        } else { 
            return data; 
        }
    }
}