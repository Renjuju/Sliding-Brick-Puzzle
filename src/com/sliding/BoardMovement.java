package com.sliding;

import java.util.*;

public class BoardMovement {
    private int[][] board;
    private final int MASTER_BLOCK = 2;
    private final int EMPTY_SPACE = 0;
    private final int WINNER_SPACE = -1;

    public BoardMovement(int[][] board) {
        this.board = board;
    }

    public int[][] applyMove(int block, String movement) {

        HashMap<String, Boolean> availableMoves = getMoves(board, block);
        HashMap<Integer, ArrayList<Integer>> hashMap = findBlock(block);
        ArrayList<Integer> keys = getKeys(hashMap);

        int key = keys.get(0);
        int[] values = toArray(hashMap, key); //columns
        Iterator iterator = hashMap.entrySet().iterator();


        switch (movement) {
            case "up":
                if(!availableMoves.get("up")) {
                    System.out.println("Unable to move up");
                    break;
                }
                while (iterator.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) iterator.next();
                    int tempKey = Integer.parseInt(pair.getKey().toString());
                    for (int i = 0; i < values.length; i++) {
                        board[tempKey - 1][values[i]] = block;
                        board[tempKey][values[i]] = 0;
                    }
                }
                break;
            case "down":
                if(!availableMoves.get("down")) {
                    System.out.println("Unable to move down");
                    break;
                }
                ArrayList<Integer> keyList = new ArrayList<>();

                while(iterator.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) iterator.next();
                    int tempKey = Integer.parseInt(pair.getKey().toString());
                    keyList.add(tempKey);
                }

                Collections.reverse(keyList);

                for(int aKey : keyList) {
                    for (int i = 0; i < values.length; i++) {
                        board[aKey + 1][values[i]] = block;
                        board[aKey][values[i]] = 0;
                    }
                }
                break;
            case "left":
                if(!availableMoves.get("left")) {
                    System.out.println("Unable to move left");
                    break;
                }
                while (iterator.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) iterator.next();
                    int tempKey = Integer.parseInt(pair.getKey().toString());
                    for (int i = 0; i < values.length; i++) {
                        board[tempKey][values[i] - 1] = block;
                        board[tempKey][values[i]] = 0;
                    }
                }
                break;
            case "right":
                if(!availableMoves.get("right")) {
                    System.out.println("Unable to move right");
                    break;
                }
                while (iterator.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) iterator.next();
                    int tempKey = Integer.parseInt(pair.getKey().toString());
                    for (int i = 0; i < values.length; i++) {
                        board[tempKey][values[i] + 1] = block;
                        board[tempKey][values[i]] = 0;
                    }
                }
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
    public HashMap<String, Boolean> getMoves(int board[][], int block) {

        HashMap<String, Boolean> availableMoves = new HashMap<>();

        //finds the blocks
        HashMap<Integer, ArrayList<Integer>> hashMap = findBlock(block);

        ArrayList<Integer> keys = getKeys(hashMap);

        int firstKey = keys.get(0);
        int lastKey = keys.get(keys.size()-1);
        int[] values = toArray(hashMap, firstKey); //columns

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

                // checking for right
                if(!(board[key][values[values.length-1] + 1] == EMPTY_SPACE)) {
                    right = false;
                }
            }
        }

        availableMoves.put("up", up);
        availableMoves.put("down", down);
        availableMoves.put("left", left);
        availableMoves.put("right", right);

        Iterator iterator = availableMoves.entrySet().iterator();

//        while(iterator.hasNext()) {
//            HashMap.Entry pair = (HashMap.Entry) iterator.next();
//            System.out.println("We " +
//                    ((pair.getValue().toString().equals("true")) ? "can go "
//                            + pair.getKey().toString() : "cannot go "
//                            + pair.getKey().toString()));
//        }

        return availableMoves;
    }
}
