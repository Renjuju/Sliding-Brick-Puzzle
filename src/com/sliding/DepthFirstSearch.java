package com.sliding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by renju on 10/4/16.
 */
public class DepthFirstSearch {

    BoardMovement boardMovement = new BoardMovement();
    Node root;
    int[][] board;

    public DepthFirstSearch() {
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard("levels/SBP-level3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        board = boardFetch.getBoard();
        root = new Node(board);
    }

    public Node expand(Node parent) {

        ArrayList<Integer> blocks = boardMovement.getAllBlocks(board);

        root = new Node(board);

        ArrayList<Node> children = new ArrayList<>();
        HashMap<String, Boolean> availableMoves = new HashMap<>();

        for(int block: blocks) {
            availableMoves = boardMovement.getMoves(board, block);
            //iterate through available moves, add to parent arrayList

            Iterator iterator = availableMoves.entrySet().iterator();
            while(iterator.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) iterator.next();
                if(pair.getValue().toString().equals("true")) {

//                 We know the block and the possible move state so we add it to a child node
                    int[][] newBoard = boardMovement.getClone(board);
                    boardMovement.move(block, pair.getKey().toString(), newBoard);
                    Node child = new Node(parent);

                    child.setBlock(block);
                    child.setBoard(newBoard);
                    child.setMove(pair.getKey().toString());

                    parent.addChild(child);
                }
            }

        }
        return parent;
    }

    public void bfs() {

        Node node = expand(root);
        System.out.println("Size of children: " + node.children.size());
        for(Node child : node.children) {
            System.out.println("Node: " + "[" + child.block + "," + child.move + "]");
            Board.printBoard(child.board);
        }
    }

}
