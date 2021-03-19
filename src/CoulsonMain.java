/*
 * Coulson is a personal project of mine which is a Sudoku Solver/Calculator.
 * Coulson can solve any size Sudoku Puzzle not just any regular 9 x 9.
 *
 * Coulson counts from 0
 *
 * @author Dave Chuck
 */

import java.util.*;

public class CoulsonMain {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //General Welcome Message stored in a string block.
        System.out.println("""
                                
                Welcome! My name is Coulson your personal Sudoku Calculator!
                I'm able to solve Sudoku puzzle no matter what the size and not your average 9 x 9 Sudoku puzzle!
                The sizes of the matrices should be square(i.e. 9, 16, 25, 36... etc)""");

        //Rules on how Coulson will operate and interpret user's size of the Sudoku Puzzle.
        System.out.println("""
                                
                Before we begin, I do have some rules you have to know. 
                1.The structure is the same as any regular 9 x 9 Sudoku puzzle.
                2.If the matrix is of size n, i.e. n*n, you can fill integer numbers from 1-n.
                3.There CANNOT be any duplicate numbers in each row, column, or box.
                4.The size of each box is (n^0.5) * (n*0.5).
                                
                You understand? If yes Great! Now we continue.
                                
                Please input the size of your current Sudoku puzzle (9 for (9x9 matrix), 16 for (16x16 matrix) etc).
                """);
        //User inputs size of their puzzle.
        int matrixSize = in.nextInt();

        //output advising user to see what
        System.out.println("""
                                
                Now it's time to show me your sudoku puzzle!
                Please type in your sudoku puzzle below and the order is from left to right
                please use the number '0' to represent a blank space.
                                
                                
                """);
        int[][] puzzle = generateMatrix(matrixSize, in);

        for (int i = 0; i < puzzle.length; i++) {
            System.out.println(Arrays.toString(puzzle[i]));
        }
    }


    /**
     * @param matrixSize
     * @param in
     * @return
     */
    public static int[][] generateMatrix(int matrixSize, Scanner in) {
        int[][] display = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                display[i][j] = in.nextInt();
            }
        }
        return display;
    }
}