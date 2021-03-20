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
        //Sudoku Board
        int[][] puzzle = generateMatrix(matrixSize, in);

        System.out.println();
        //User's Sudoku Grid
        for (int i = 0; i < puzzle.length; i++) {
            System.out.println(Arrays.toString(puzzle[i]));
        }
        //calling the fill matrix method to fill the 0's with the correct value
        fillMatrix(puzzle);
        System.out.println("\nComplete! Here is your solved Sudoku Puzzle!\n");

        for (int i = 0; i < puzzle.length ; i++) {
            System.out.println(Arrays.toString(puzzle[i]));
        }
    }


    /**
     * Generates a 2D array that represents the Sudoku puzzle board itself
     * @param matrixSize size of the grid (matrixSize.length).
     * @param in Input from user (I.e their values for the puzzle.
     * @return the matrix/Sudoku Board
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

    /**
     *This method is to fill the Sudoku puzzle/matrix
     * @param puzzle This is the sudoku puzzle/matrix
     * @return once solved.
     */
    public static boolean fillMatrix(int[][] puzzle){
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if (puzzle[i][j] == 0){
                    for (int k = 1; k <= puzzle.length; k++) {
                        puzzle[i][j] = k;
                        if(check(puzzle,i,j) && fillMatrix(puzzle)){
                            return true;
                        }
                    }
                    puzzle[i][j] = 0;
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  Checking if the user properly has the correct value for each index and blank as well
     * @param puzzle The sudoku puzzle/matrix
     * @param i row of empty or '0'
     * @param j column of empty or '0'
     * @return true if it passes
     */
    public static boolean check(int[][] puzzle, int i, int j){
        return checkRow(puzzle, i, j) && checkColumn(puzzle,i,j) && checkGrid(puzzle, i, j);
    }

    /**
     * Checking if the value for the solved value in each row is correct
     * @param puzzle the sudoku puzzle/matrix
     * @param i row of empty or '0'
     * @param j column of empty or '0'
     * @return true if it passes
     */
    public static boolean checkRow(int[][] puzzle, int i, int j){
        int[] check = puzzle[i];
        return checkArray(check);
    }

    /**
     *Checking if the value for the solved value in each column is correct
     * @param puzzle the sudoku puzzle/matrix
     * @param i row of empty or '0'
     * @param j column of empty or '0'
     * @return true if it passes
     */
    public static boolean checkColumn(int[][] puzzle, int i, int j){
        int[] check = new int[puzzle.length];
        for(int k = 0; k < puzzle.length; k++){
            check[k] = puzzle[k][j];
        }
        return checkArray(check);
    }

    /**
     * Check if there is a duplicated within the matrix/puzzle
     * @param check array to check if it's duplicate
     * @return true if the matrix/puzzle doesn't have any duplicates in either row or column.
     */
    public static boolean checkArray(int[] check){
        for (int k = 0; k < check.length; k++) {
            for (int l = 0; l < check.length; l++) {
                if (check[k] != 0 && l != k && check[k] == check[l]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  Checking if the value calculated for each 3x3 section is correct
     * @param puzzle the Sudpku Puzzle/matrix
     * @param i row number of the input puzzle
     * @param j column number of the input puzzle
     * @return true if it passes
     */
    public static boolean checkGrid(int[][] puzzle, int i, int j){
        int gridBoxSize = (int) Math.pow(puzzle.length,0.5);
        int row = i / gridBoxSize * gridBoxSize;
        int column = (j / gridBoxSize * gridBoxSize);
        int[] check = new int[puzzle.length];
        int counter = 0;
        for (int k = row; k < row + gridBoxSize; k++) {
            for (int l = column; l < column + gridBoxSize; l++) {
                check[counter] = puzzle[k][l];
                counter ++;
            }

        }
        return checkArray(check);
    }

}