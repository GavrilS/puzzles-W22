package com.recursion.maze_rat;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

//        Set test = new HashSet();
//        String test1 = "1-0";
//        String test2 = "1-1";
//        String test3 = "0-1";
//        String test4 = "1-1";
//        System.out.println(test.add(test1));
//        System.out.println(test.add(test2));
//        System.out.println(test.add(test3));
//        System.out.println(test.add(test4));

        Scanner sc = new Scanner(System.in);
        int dimentions = sc.nextInt();

        int[][] maze = getMaze(dimentions);

        ArrayList<String> path = findPath(maze, dimentions);

        if (path.size() == 0) {
            System.out.println("-1");
        } else {
            for (String elem: path) {
                System.out.println(elem);
            }
        }
    }

    private static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> path = new ArrayList<String>();
        System.out.println("Check 1");
        if ((m[0][0] == 0) || (m[m.length -1][m.length - 1] == 0)) {
            return path;
        }

        System.out.println("Check 2");

        // Posible ways - {Up, Down, Left, Right}
        int[] possibleRows = {-1, 1, 0, 0};
        int[] possibleCols = {0, 0, -1, 1};

        int currentRow = 0;
        int currentCol = 0;
//        Map visitedCells = new HashMap();
        Set<String> visitedCells = new HashSet<String>();
        String firstCell = String.valueOf(0) + "-" + String.valueOf(0);
        visitedCells.add(firstCell);
        String currentPath = "";

        solution(path, m, possibleRows, possibleCols, currentRow, currentCol, visitedCells, currentPath);
        System.out.println("Check 3");
        return path;
    }

    private static boolean solution(ArrayList<String> path, int[][] maze, int[] possibleRows, int[] possibleCols, int currentRow, int currentCol,
                                    Set<String> visitedCells, String currentPath){

        if ((currentRow == maze.length - 1) && (currentCol == maze.length -1)) {
            path.add(currentPath);
//            System.out.printf("Path is: ", );
            return true;
        }

        int nextRow, nextCol;
        for (int i=0; i < possibleCols.length; i++) {
            nextRow = currentRow + possibleRows[i];
            nextCol = currentCol + possibleCols[i];

            if (!isSafe(nextRow, nextCol, maze, visitedCells)) {
                continue;
            } else {
                String nextDirection = returnDirection(i);
                currentPath += nextDirection;
                if (solution(path, maze, possibleRows, possibleCols, nextRow, nextCol, visitedCells, currentPath)) {
                    return true;
                } else {
                    if ((currentPath != null) && (currentPath.length() > 0)) {
                        currentPath = currentPath.substring(0, currentPath.length() - 1);
                    } else {
                        currentPath = "";
                    }
                    String visited = String.valueOf(nextRow) + "-" + String.valueOf(nextCol);
                    visitedCells.remove(visited);
                }
            }
        }

        return false;
    }

    private static String returnDirection(int possiblePath) {
        switch (possiblePath) {
            case 0:
                return "U";
            case 1:
                return "D";
            case 2:
                return "L";
            case 3:
                return "R";
        }
        return "";
    }

    private static boolean isSafe(int row, int col, int[][] maze, Set<String> visitedCells) {
        String newVisited = String.valueOf(row) + "-" + String.valueOf(col);
        boolean visited = visitedCells.add(newVisited);
        if (row >= 0 && row < maze.length && col >= 0 && col < maze.length && maze[row][col] == 1 && visited) {
            return true;
        } else {
            return false;
        }
    }

    private static int[][] getMaze(int dimentions) {
        if (dimentions == 2) {
            int[][] m = {{1,0}, {1, 0}};
            return m;
        } else if (dimentions==4) {
            int[][] m = {{1, 0,0,0},
                        {1,1,0,0},
                        {1,1,0,1},
                        {0,1,1,1}};
            return m;
        } else {
            int[][] m = {{0, 1,0},
                    {1,0,1},
                    {1,1,1}};
            return m;
        }
    }
}
