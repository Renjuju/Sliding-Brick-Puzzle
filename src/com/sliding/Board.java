package com.sliding;

import java.io.IOException;

/**
 * Created by renju on 9/13/16.
 */
public class Board {

    private int[][] board;

    public Board() {
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard("levels/SBP-level0.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        board = boardFetch.getBoard();
        printBoard(board);

    }

    public int[][] getClone() {
        return null;
    }

    public void printBoard(int[][] board) {
        for(int x = 0; x < board.length; x++) {
            for(int i = 0; i < board[x].length; i++) {
                System.out.print(board[x][i] + "  ");
            }
            System.out.println();
        }
    }

}
