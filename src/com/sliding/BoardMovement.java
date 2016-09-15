package com.sliding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BoardMovement {
    private int[][] board;

    public BoardMovement(int[][] board) {
        this.board = board;
    }

    public int[][] move(int block, String movement) {
        switch(movement) {
            case "up":
                System.out.println("up");
                HashMap<Integer, ArrayList<Integer>> hashMap = findBlock(block);
                Iterator iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry)iterator.next();
                    System.out.println("Row: " + pair.getKey() + ", Columns: " + pair.getValue());
                }
                if(movement.equals("up")) {
                    int key = getFirstKey(hashMap);
                    int[] values = toArray(hashMap, key); //columns
                    for(int x : values) {
                        System.out.println(x);
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

        for(int i = 0; i < board.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int x = 0; x < board[i].length; x++) {
                if(board[i][x] == block) {
                    list.add(x);
                    hashMap.put(i, list);
                }
            }
        }
        return hashMap;
    }

    private int getFirstKey(HashMap<Integer, ArrayList<Integer>> hashMap) {
        Iterator iterator = hashMap.entrySet().iterator();

            HashMap.Entry pair = (HashMap.Entry)iterator.next();
            System.out.println("Row: " + pair.getKey() + ", Columns: " + pair.getValue());

        return Integer.parseInt(pair.getKey().toString());
    }

    private int[] toArray(HashMap<Integer, ArrayList<Integer>> hashMap, int key) {
        Object[] hashArray = hashMap.get(key).toArray();
        int length = hashArray.length;

        int[] array = new int[length];
        for(int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(hashArray[i].toString());
        }
        return array;
    }
}
