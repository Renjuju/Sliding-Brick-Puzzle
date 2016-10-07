package com.sliding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by renju on 10/7/16.
 */
public class Search {

    int[][] board;
    Node root;

    public Search() {
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard("levels/SBP-level0.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        board = boardFetch.getBoard();
        root = new Node(board);
    }

    BoardMovement boardMovement = new BoardMovement();

    public Node expand(Node parent) {
        ArrayList<Integer> blocks = boardMovement.getAllBlocks(board);

        HashMap<String, Boolean> availableMoves = new HashMap<>();

        for(int block: blocks) {
            availableMoves = boardMovement.getMoves(parent.board, block);
            //iterate through available moves, add to parent arrayList

            Iterator iterator = availableMoves.entrySet().iterator();
            while(iterator.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) iterator.next();
                if(pair.getValue().toString().equals("true")) {

                    //We know the block and the possible move state so we add it to a child node
                    int[][] newBoard = boardMovement.getClone(parent.board);
                    if(boardMovement.isWinner(parent.board)) {
                        parent.isWinner(true);
                        return parent;
                    }

                    boardMovement.move(block, pair.getKey().toString(), newBoard);
                    Node child = new Node(parent);

                    child.isWinner(boardMovement.isWinner(parent.board));

                    child.setBlock(block);
                    child.setBoard(newBoard);
                    child.setMove(pair.getKey().toString());

                    parent.addChild(child);
                }
            }
        }

        if(parent.children.size() == 0 ) {
            return parent;
        }

        return parent;
    }
}
