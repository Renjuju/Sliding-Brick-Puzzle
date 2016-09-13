package com.sliding;

import java.io.IOException;

/**
 * Created by renju on 9/13/16.
 */
public class Menu {

    public void play() {
        try {
            RetrieveBoard boardArr = new RetrieveBoard("levels/SBP-level0.txt");
            int board[][] = boardArr.getBoard();
            for(int x = 0; x < board.length; x++) {
                for(int i = 0; i < board[x].length; i++) {
                    System.out.print(board[x][i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
