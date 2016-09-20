package com.sliding;

import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
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

                ArrayList<Integer> keys = getKeys(hashMap);

                int key = keys.get(0);
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

    private ArrayList<Integer> getKeys(HashMap<Integer, ArrayList<Integer>> hashMap) {
        ArrayList<Integer> keys = new ArrayList<>();

        Iterator iterator = hashMap.entrySet().iterator();

        while (iterator.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) iterator.next();
            keys.add(Integer.parseInt(pair.getKey().toString()));
        }

        return keys;
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

    //Write a function that given a state and a piece, it returns a list of all the moves the piece can perform
    public void getMoves(int board[][], int block) {
        //finds the blocks
        HashMap<Integer, ArrayList<Integer>> hashMap = findBlock(block);

        ArrayList<Integer> keys = getKeys(hashMap);

        int firstKey = keys.get(0);
        int lastKey = keys.get(keys.size()-1);
        int[] values = toArray(hashMap, firstKey); //columns

        HashMap<Integer, Integer> possibleMoves = new HashMap<>();

        // by default, they're all true possible moves unless proven otherwise
        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;

        if (block == MASTER_BLOCK) {
            for(int i = 0; i < values.length; i++) {
                // if master block..checking for up
                if (!(board[firstKey - 1][values[i]] == EMPTY_SPACE || board[firstKey - 1][values[i]] == WINNER_SPACE)) {
                    up = false;
                }
                // checking for down
                if (!(board[lastKey + 1][values[i]] == EMPTY_SPACE || board[lastKey + 1][values[i]] == WINNER_SPACE)) {
                    down = false;
                }
            }

            for(int key : keys) {
                // checking for left
                if(!(board[key][values[0] - 1] == EMPTY_SPACE || board[key][values[0] - 1] == WINNER_SPACE)) {
                    left = false;
                }

                // checking for left
                if(!(board[key][values[values.length-1] + 1] == EMPTY_SPACE || board[key][values[values.length-1] + 1] == WINNER_SPACE)) {
                    right = false;
                }
            }

        } else {
            // normal block checking for up
            for(int i = 0; i < values.length; i++) {
                if (!(board[firstKey - 1][values[i]] == EMPTY_SPACE)) {
                    up = false;
                }
                // checking for down
                if (!(board[lastKey + 1][values[i]] == EMPTY_SPACE)) {
                    down = false;
                }
            }

            for(int key : keys) {
                // checking for left
                if(!(board[key][values[0] - 1] == EMPTY_SPACE)) {
                    left = false;
                }

                // checking for left
                if(!(board[key][values[values.length-1] + 1] == EMPTY_SPACE)) {
                    right = false;
                }
            }
        }

        System.out.println("Up: " + up + ", Down: " + down);
        System.out.println("Left: " + left + ", Right: " + right);
    }
}
