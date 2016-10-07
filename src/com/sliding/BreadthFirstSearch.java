package com.sliding;

import java.io.IOException;
import java.util.*;

/**
 * Created by renju on 10/4/16.
 */
public class BreadthFirstSearch {

    BoardMovement boardMovement = new BoardMovement();
    Node root;
    int[][] board;
    LinkedList<Node> graph = new LinkedList<>();
    int counter = 0;

    public BreadthFirstSearch() {
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard("levels/SBP-level1.txt");
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

    private void iterate(Node node) {
        for(Node n : node.children) {
            Node x = expand(n);
            System.out.println(n.block);
            for(Node child : x.children) {
                System.out.println("[" + child.block + child.move + "]");
            }
            System.out.println();
        }
    }
    public void bfs() {
        LinkedList<Node> linkedList = new LinkedList<>();

        Node node = expand(root);


            //add to list
            Queue<Node> queue = new LinkedList<>();

            for(Node child : node.children) {
                queue.add(expand(child));
            }

            Node winner = null;
            while(!queue.isEmpty()) {
                Node root = queue.poll();
                for(Node kid : root.children) {
                    queue.add(expand(kid));
                }

                if(root.isWinner()) {
                    System.out.println("Winning board!");
                    winner = root;
                    break;
                }

            }

            Stack<String> winningStack = new Stack<>();
            int completeBoard[][] = winner.board;
            while(winner.depth > 0) {
                String moves = "(" + winner.block + ", " + winner.move + ")";
                winningStack.push(moves);
                winner = winner.parent;
            }

            while(!winningStack.isEmpty()) {
                System.out.println(winningStack.pop());
            }

            System.out.println();
            boardMovement.printBoard(completeBoard);
    }

}

//From wikipedia
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