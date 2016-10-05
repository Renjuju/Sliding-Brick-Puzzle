package com.sliding;

import java.util.ArrayList;

public class Node {

    Node parent;
    ArrayList<Node> children;
    String move;
    int block;
    int[][] board;
    int depth;

    public Node(int[][] board) {
        this.depth = 0;
        this.board = board;
        this.children = new ArrayList<>();
    }

    public Node(Node parent) {
        this.parent = parent;
        this.depth = this.parent.depth + 1;
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
