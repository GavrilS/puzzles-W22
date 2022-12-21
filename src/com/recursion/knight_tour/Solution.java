package com.recursion.knight_tour;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String args[]) throws IOException {
        // Get the dimensions of the board from the user
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int boardDimensions = Integer.parseInt(bufferedReader.readLine().trim());

        int[][] board = createBoard(boardDimensions);

        // Print initial board setup
        printBoard(board);
        int[] rowMoves = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] colMoves = {1, 2, 2, 1, -1, -2, -2, -1};
        int currentRow = 0;
        int currentCol = 0;
        int currentMove = 1;
        board[0][0] = 0;

        boolean canVisitAllSquares = travelBoard(board, rowMoves, colMoves, currentRow, currentCol, currentMove);
        if (canVisitAllSquares) {
            printBoard(board);
        } else {
            System.out.println("Board cannot be completed!");
        }
    }


    private static boolean travelBoard(int[][] board, int[] rowMoves, int[] colMoves, int currentRow, int currentCol, int currentMove) {
        if (currentMove == board.length*board.length) {
            return true;
        }

        int nextRow, nextCol;

        for (int i = 0; i < rowMoves.length; i++) {
            nextRow = currentRow + rowMoves[i];
            nextCol = currentCol + colMoves[i];
            if (isSafe(nextRow, nextCol, board)) {
                board[nextRow][nextCol] = currentMove;
                if (travelBoard(board, rowMoves, colMoves, nextRow, nextCol, currentMove+1)) {
                    return true;
                } else {
                    board[nextRow][nextCol] = -1;
                }
            }
        }

        return false;
    }

    private static int[][] createBoard(int dimension) {
        int[][] board = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = -1;
            }
        }
        return board;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + "     ");
            }
            System.out.println();
        }
    }

    private static boolean isSafe(int row, int col, int[][] board) {
        return (row >= 0 && row < board.length && col >= 0 && col < board.length && board[row][col] == -1);
    }

}
