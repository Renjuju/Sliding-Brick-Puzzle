import java.util.*;

/**
 * Created by renju on 10/17/16.
 */
public class AStarSearch extends Search {

    String heuristic;
    public AStarSearch(String fileName, String heuristic) {
        super(fileName, heuristic);
        this.heuristic = heuristic;
    }

    public int[][] search() {

        int distance = 0;

        System.out.println(heuristic);
        if(heuristic.equals("manhattan")) {
            distance = manhattanDistanceOf(root.board);
        } else {
            distance = euclideanDistanceOf(root.board);
        }

        Comparator<Node> comparator = (o1, o2) -> {
            if(o1.getFScore() > o2.getFScore()) {
                return 0;
            }
            return -1;
        };

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(10, comparator);

        Node node = expand(root);

        node.setFScore(1 + distance);

        for (Node child : node.children) {
            Node cNode = expand(child);
            priorityQueue.add(cNode);
        }

        Node winner = null;
        while (!priorityQueue.isEmpty()) {
            Node root = priorityQueue.poll();
            if (root.isWinner()) {
                System.out.println("Winning board!");
                winner = root;
                break;
            }

            for (Node kid : root.children) {
                Node sub = expand(kid);

                if(sub != null) {
                    if(sub.isWinner()) {
                        winner = sub;
                        break;
                    }
                }

                if(sub != null) {
                    priorityQueue.add(expand(kid));
                }
            }

            if(winner != null) {
                break;
            }
        }
        //        // Print the winning moves
        Stack<String> winningStack = new Stack<>();
        int completeBoard[][] = winner != null ? winner.board : new int[0][];
        while (winner.depth > 0) {
            String moves = "(" + winner.block + ", " + winner.move + ")";
            winningStack.push(moves);
            winner = winner.parent;
        }

        while (!winningStack.isEmpty()) {
            System.out.println(winningStack.pop());
        }

        System.out.println();
        Board.printBoard(completeBoard);
        System.out.println("Nodes visited: " + visitedStateSize());

        return completeBoard;
    }
}
