package com.sliding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BoardMovement {
    private int[][] board;
    private final int MASTER_BLOCK = 2;
    private final int EMPTY_SPACE = 0;
    private final int WINNER_SPACE = -1;
    private final int WALL = 1;
    public BoardMovement(int[][] board) {
        this.board = board;
    }

    public int[][] move(int block, String movement) {
        switch (movement) {
            case "up":
                HashMap<Integer, ArrayList<Integer>> hashMap = findBlock(block);
                Iterator iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) iterator.next();
                    System.out.println("Row: " + pair.getKey() + ", Columns: " + pair.getValue());
                }
                int key = getFirstKey(hashMap);
                int[] values = toArray(hashMap, key); //columns

                // if master block
                if (block == MASTER_BLOCK) {
                    if (!(board[key - 1][values[0]] == EMPTY_SPACE || board[key - 1][values[0]] == WINNER_SPACE)) {
                        System.out.println("Movement invalid");
                        break;
                    } else {
                        System.out.println("Moving BLOCK!");
                    }

                    iterator = hashMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        HashMap.Entry pair = (HashMap.Entry) iterator.next();
                        int tempKey = Integer.parseInt(pair.getKey().toString());
                        for (int i = 0; i < values.length; i++) {
                            board[tempKey - 1][values[i]] = block;
                            board[tempKey][values[i]] = 0;
                        }
                    }
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

    private HashMap<Integer, ArrayList<Integer>> findBlock(int block) {
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int x = 0; x < board[i].length; x++) {
                if (board[i][x] == block) {
                    list.add(x);
                    hashMap.put(i, list);
                }
            }
        }
        return hashMap;
    }

//    O(1)
    private int getFirstKey(HashMap<Integer, ArrayList<Integer>> hashMap) {
        Iterator iterator = hashMap.entrySet().iterator();

        HashMap.Entry pair = (HashMap.Entry) iterator.next();
        System.out.println("Row: " + pair.getKey() + ", Columns: " + pair.getValue());

        return Integer.parseInt(pair.getKey().toString());
    }
//    O(n)
    private int getLastKey(HashMap<Integer, ArrayList<Integer>> hashMap) {
        Iterator iterator = hashMap.entrySet().iterator();
        int lastKey = 0;
        while (iterator.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) iterator.next();
            lastKey = Integer.parseInt(pair.getKey().toString());
        }
        return lastKey;
    }

    private int[] toArray(HashMap<Integer, ArrayList<Integer>> hashMap, int key) {
        Object[] hashArray = hashMap.get(key).toArray();
        int length = hashArray.length;

        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(hashArray[i].toString());
        }
        return array;
    }

    //    it returns a list of all the moves the piece can perform
    public void getMoves(int board[][], int block) {
        //finds the blocks
        HashMap<Integer, ArrayList<Integer>> hashMap = findBlock(block);

        int key = getFirstKey(hashMap);
        int lastKey = getLastKey(hashMap);
        int[] values = toArray(hashMap, key); //columns

        HashMap<Integer, Integer> possibleMoves = new HashMap<>();
        // if master block..checking for up
        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;
        if (block == MASTER_BLOCK) {
            for(int i = 0; i < values.length; i++) {
                if (!(board[key - 1][values[i]] == EMPTY_SPACE || board[key - 1][values[i]] == WINNER_SPACE)) {
                    up = false;
                }
                // checking for down
                if (!(board[lastKey + 1][values[i]] == EMPTY_SPACE || board[lastKey + 1][values[i]] == WINNER_SPACE)) {
                    down = false;
                }
            }
        } else {
            for(int i = 0; i < values.length; i++) {
                if (!(board[key - 1][values[i]] == EMPTY_SPACE)) {
                    up = false;
                }
                // checking for down
                if (!(board[lastKey + 1][values[i]] == EMPTY_SPACE)) {
                    down = false;
                }
            }
        }

        System.out.println("Up: " + up + ", Down: " + down);
    }
}
