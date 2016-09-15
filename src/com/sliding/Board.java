package com.sliding;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by renju on 9/13/16.
 */
public class Board {

    private int[][] board;
    private BoardMovement movement;

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
        } else {
            System.out.println("Keep playing!");
        }
        movement = new BoardMovement(board);
        movement.move(2, "up");
    }

    //Deep copy
    public int[][] getClone(int[][] board) {
        int[][] result = new int[board.length][];
        for(int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    public void printBoard(int[][] board) {
        System.out.println("-----------------");
        for(int x = 0; x < board.length; x++) {
            for(int i = 0; i < board[x].length; i++) {
                System.out.print(board[x][i] + "  ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
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

}
