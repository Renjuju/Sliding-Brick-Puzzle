package com.sliding;

import java.util.ArrayList;

/**
 * Created by renju on 10/4/16.
 */
public class DepthFirstSearch {

    BoardMovement boardMovement = new BoardMovement();
    Node root;
    int[][] board;

//    public DepthFirstSearch(int board[][], BoardMovement boardMovement) {
//        ArrayList<Integer> blocks = boardMovement.getAllBlocks(board);
//        root = new Node(board);
//        for(int block : blocks) {
//            boardMovement.getMoves(board, block);
//        }
//    }

    public Node expand(Node parent) {
        ArrayList<Integer> blocks = boardMovement.getAllBlocks(board);
        ArrayList<Node> children = new ArrayList<>();

        for(int block: blocks) {

        }

        return root;
    }

    public void bfs() {

        expand(root);
    }

}
