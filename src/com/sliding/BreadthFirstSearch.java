package com.sliding;

import java.io.IOException;
import java.util.*;

/**
 * Created by renju on 10/4/16.
 */
public class BreadthFirstSearch extends Search {


    LinkedList<Node> graph = new LinkedList<>();
    int counter = 0;

    public BreadthFirstSearch() {
        super();
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