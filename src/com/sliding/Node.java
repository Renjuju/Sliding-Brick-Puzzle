package com.sliding;

import java.util.ArrayList;

/**
 * Created by renju on 10/3/16.
 */
public class Node {

    Node parent;
    ArrayList<Node> children;
    String move;
    int block;
    int[][] board;

    public Node(int[][] board) {
        this.board = board;
        this.children = new ArrayList<>();
    }

    public Node(Node parent) {
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public void setMove(String move) {
        this.move = move;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

}
