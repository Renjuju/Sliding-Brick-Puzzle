package com.sliding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by renju on 9/13/16.
 */
public class BoardMovement {
    private int[][] board;

    public BoardMovement(int[][] board) {
        this.board = board;
    }

    public int[][] move(int block, String movement) {
        switch(movement) {
            case "up":
                System.out.println("up");
                HashMap<Integer, Integer> hashMap = findBlock(block);
                Iterator iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry)iterator.next();
                    System.out.println(pair.getKey() + "," + pair.getValue());
                }
                break;
            case "down":
                System.out.println("down");
                break;
            case "left":
                System.out.println("down");
                break;
            case "right":
                System.out.println("down");
                break;
            default:
                System.out.println("Move names are: up, down, left, right");
        }
        return board;
    }

    private HashMap<Integer, Integer> findBlock(int block) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for(int i = 0; i < board.length; i++) {
            for(int x = 0; x < board[i].length; x++) {
                if(board[i][x] == block) {
                    hashMap.put(i, x);
                }
            }
        }
        return hashMap;
    }
}
