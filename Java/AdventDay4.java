import java.io.*;
import java.util.*;
public class AdventDay4 {
    public static void main(String[] args) {
        int[] bingoNumbers;
        int[][][] bingoBoard;
        try {
            File myObj = new File("./Inputs/inputDay4.txt");
            Scanner myReader = new Scanner(myObj);
            String bingoNumbersTogether =  myReader.nextLine();
            String[] bingoNumbersStrings = bingoNumbersTogether.split(",");
            bingoNumbers = new int[bingoNumbersStrings.length];
            for (int i=0; i<bingoNumbersStrings.length; i++) bingoNumbers[i] = Integer.parseInt(bingoNumbersStrings[i]);
            bingoBoard = new int[100][5][5];
            while (myReader.hasNextInt()) {
                for (int l=0; l<100; l++) { 
                    for (int i=0; i<5; i++) {
                        for (int j=0; j<5; j++) {
                            bingoBoard[l][i][j] = myReader.nextInt();
                        }
                    }
                }
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

        int winningNum = -1;
        int losingBoard = 0;
        int numOfLosingBoards = 0;
        boolean[] winningBoards = new boolean[100];
        boolean firstWin = true;
        for (int i=0; i<winningBoards.length; i++) winningBoards[i] = false;
        for (int num : bingoNumbers) {
            winningNum = num;
            for (int l=0; l<100; l++) { // Marks off called number and Checks if any board won
                for (int i=0; i<5; i++) {
                    for (int j=0; j<5; j++) {
                        if (num == bingoBoard[l][i][j]) bingoBoard[l][i][j] = 0;
                    }
                }
                if (CheckWon(bingoBoard, l)) {
                    winningBoards[l] = true;
                    if (firstWin) {
                        CalcScore(bingoBoard, l, winningNum, 1);
                        firstWin = false;
                    }
                }
            }
            numOfLosingBoards = 0;
            for (int i=0; i<winningBoards.length; i++) { // Sets losingBoard and counts numOfLosingBoards
                if (winningBoards[i] == false) {
                    losingBoard = i;
                    numOfLosingBoards++;
                }
            }
            if (numOfLosingBoards == 0) break;
        }
		CalcScore(bingoBoard, losingBoard, winningNum, 2);
    }
    public static void CalcScore(int[][][] board, int boardNum, int winningNum, int part) {
        int unmarkedNums = 0;
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                unmarkedNums += board[boardNum][i][j];
            }
        }
        System.out.printf("Part %d: %d%n", part, unmarkedNums*winningNum);
    }
    public static boolean CheckWon(int[][][] array, int iteration) {
        int countRow = 0, countCol = 0;
        for (int i=0; i<5; i++) {
            countRow = 0; 
            countCol = 0;
            for (int j=0; j<5; j++) { 
                countRow += array[iteration][i][j];
                countCol += array[iteration][j][i];
            }
            if (countRow == 0 || countCol == 0) return true;
        }
        return false;
    }
}