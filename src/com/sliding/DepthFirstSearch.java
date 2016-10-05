package com.sliding;

import java.util.ArrayList;

/**
 * Created by renju on 10/4/16.
 */
public class DepthFirstSearch {


    Node root;

    public DepthFirstSearch(int board[][], BoardMovement boardMovement) {

        ArrayList<Integer> blocks = boardMovement.getAllBlocks(board);
        root = new Node(board);

        for(int block : blocks) {

        }
    }

}
