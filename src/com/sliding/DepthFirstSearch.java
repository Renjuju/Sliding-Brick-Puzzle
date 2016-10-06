package com.sliding;

import java.io.IOException;
import java.util.*;

/**
 * Created by renju on 10/4/16.
 */
public class DepthFirstSearch {

    BoardMovement boardMovement = new BoardMovement();
    Node root;
    int[][] board;
    LinkedList<Node> graph = new LinkedList<>();
    int counter = 0;

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
                    boardMovement.move(block, pair.getKey().toString(), newBoard);
                    Node child = new Node(parent);

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

    public void bfs() {
        LinkedList<Node> linkedList = new LinkedList<>();

        Node node = expand(root);

        for(Node n : node.children) {
            Node x = expand(n);
            System.out.println(n.block);
            for(Node child : x.children) {
                System.out.println("[" + child.block + child.move + "]");
            }
            System.out.println();
        }

//        System.out.println("Size of children: " + node.children.size());
//        for(Node child : node.children) {
//            System.out.println("Node: " + "[" + child.block + "," + child.move + "]");
//
//        }
    }

}

//Breadth-First-Search(Graph, root):
//        2
//        3     for each node n in Graph:
//        4         n.distance = INFINITY
//        5         n.parent = NIL
//        6
//        7     create empty queue Q
//        8
//        9     root.distance = 0
//        10     Q.enqueue(root)
//        11
//        12     while Q is not empty:
//        13
//        14         current = Q.dequeue()
//        15
//        16         for each node n that is adjacent to current:
//        17             if n.distance == INFINITY:
//        18                 n.distance = current.distance + 1
//        19                 n.parent = current
//        20                 Q.enqueue(n)