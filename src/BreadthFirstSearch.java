import java.util.*;

/**
 * Created by renju on 10/4/16.
 */
public class BreadthFirstSearch extends Search {

    public BreadthFirstSearch(String file) {
        super(file);
    }

    public void bfs() {

        Node node = expand(root);

        //add to list
        Queue<Node> queue = new LinkedList<>();

        for (Node child : node.children) {
            Node cNode = expand(child);
            queue.add(cNode);
        }

        Node winner = null;
        while (!queue.isEmpty()) {
            Node root = queue.remove();
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
                    queue.add(expand(kid));
                }
            }

            if(winner != null) {
                break;
            }
        }

        if(queue.isEmpty()) {
            System.out.println("Empty queue");
        }
        // Print the winning moves
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
    }

}