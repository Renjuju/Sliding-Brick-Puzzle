package com.sliding;

import java.io.IOException;
import java.util.Arrays;

public class Board {

    private int[][] board;
    private BoardMovement movement;
    public Board() {

    }

    public Board(String file) {
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        board = boardFetch.getBoard();
        int b2[][] = getClone(board);

        b2[3][3] = 14;
        printBoard(board);

        if(isWinner(board)) {
            System.out.println("Winner!");
        }

        movement = new BoardMovement();
    }

    public void applyMove(int block, String direction) {
        movement.move(block, direction, board);
        printBoard(board);
        if(isWinner(board)) {
            System.out.println("Winner!");
        } else {
            System.out.println("Keep playing!");
        }
    }

    public int[][] applyMoveCloning(int block, String direction) {
        int[][] newBoard = getClone(board);
        movement.move(block, direction, newBoard);
        return newBoard;
    }

    public int[][] getClone(int[][] board) {
        int[][] result = new int[board.length][];
        for(int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    public void printBoard(int[][] board) {
        for(int x = 0; x < board.length; x++) {
            for(int i = 0; i < board[x].length; i++) {
                System.out.printf("%4d", board[x][i]);
            }
            System.out.println();
        }
        Arrays.deepToString(board);
        System.out.println((char)27 + "[31m------------------------------" + (char)27 + "[0m");
    }

    public boolean isWinner(int [][] board) {
        for(int x = 0; x < board.length; x++) {
            for(int i =0; i < board[x].length; i++) {
                if(board[x][i] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    // State comparison function
    public boolean isIdentical(int[][] board1, int[][] board2 ) {
        // Checks for sizes
        if(board1.length != board2.length) {
            return false;
        }

        if(board1[board1.length-1].length != board2[board2.length-1].length) {
            return false;
        }

        for(int i = 0; i < board1.length; i++) {
            for(int j = 0; j < board1[i].length; j++) {
                if(board1[i][j] != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
