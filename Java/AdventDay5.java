import java.io.*;
import java.util.*;
public class AdventDay5 {
    public static void main(String[] args) {
        Part(true);
        Part(false);
    }
    private static void Part(boolean part1) {
        List<String> lineList;
        try {
            File myObj = new File("./Inputs/inputDay5.txt");
            Scanner myReader = new Scanner(myObj);
            lineList = new ArrayList<String>();
            while (myReader.hasNextLine()) lineList.add(myReader.nextLine());
            myReader.close();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
        int[][] x1y1x2y2 = new int[lineList.size()][4];
        for(int i = 0; i<lineList.size(); i++) {
            int x1 = Integer.parseInt(lineList.get(i).substring(0, lineList.get(i).indexOf(',')));
            int y1 = Integer.parseInt(lineList.get(i).substring(lineList.get(i).indexOf(',')+1, lineList.get(i).indexOf(' ')));
            int x2 = Integer.parseInt(lineList.get(i).substring(lineList.get(i).lastIndexOf(' ')+1, lineList.get(i).lastIndexOf(',')));
            int y2 = Integer.parseInt(lineList.get(i).substring(lineList.get(i).lastIndexOf(',')+1));
            x1y1x2y2[i][0] = x1;
            x1y1x2y2[i][1] = y1;
            x1y1x2y2[i][2] = x2;
            x1y1x2y2[i][3] = y2;
        }
        int[][] map = new int[999][999];
        for (int i=0; i<x1y1x2y2.length; i++) {
            if (x1y1x2y2[i][1]==x1y1x2y2[i][3]) {
                for (int j=0; j<=Math.abs(x1y1x2y2[i][0]-x1y1x2y2[i][2]); j++) {
                    map[Math.min(x1y1x2y2[i][0], x1y1x2y2[i][2])+j][x1y1x2y2[i][1]] += 1;
                }
            }
            else if (x1y1x2y2[i][0]==x1y1x2y2[i][2]) {
                for (int j=0; j<=Math.abs(x1y1x2y2[i][1]-x1y1x2y2[i][3]); j++) {
                    map[x1y1x2y2[i][0]][Math.min(x1y1x2y2[i][1], x1y1x2y2[i][3])+j] += 1;
                }
            }
            else if (!part1) for (int j=0; j<=Math.abs(x1y1x2y2[i][0]-x1y1x2y2[i][2]); j++) {
                int x = Math.min(x1y1x2y2[i][0], x1y1x2y2[i][2])+j;
                int y;
                if (x1y1x2y2[i][0] < x1y1x2y2[i][2]) {
                    if (x1y1x2y2[i][1] < x1y1x2y2[i][3]) y = x1y1x2y2[i][1]+j; else y = x1y1x2y2[i][1]-j;
                } else if (x1y1x2y2[i][1] < x1y1x2y2[i][3]) y = x1y1x2y2[i][3]-j; else y = x1y1x2y2[i][3]+j;
                map[x][y] += 1;
            }
        }
        if (part1) System.out.printf("Part 1: %d%n", CountOverlaps(map));
        else System.out.printf("Part 2: %d%n", CountOverlaps(map));
    }
    private static int CountOverlaps(int[][] map) {
        int overlapCount = 0;
        for(int y=0; y<map.length; y++) {
           for(int x=0; x<map[0].length; x++) { 
               if (map[x][y] > 1) overlapCount++;
           }
        }
        return overlapCount;
    }
}