import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by renju on 10/7/16.
 */
public class DepthFirstSearch extends Search {

    public void dfs() {

        Node node = expand(root);

        //add to list
        Stack<Node> stack = new Stack<>();

        for (Node child : node.children) {
            Node cNode = expand(child);
            stack.push(cNode);
        }

        Node winner = null;
        while (!stack.isEmpty()) {
            Node root = stack.pop();
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
                    stack.push(expand(kid));
                }
            }

            if(winner != null) {
                break;
            }
        }

        if(stack.isEmpty()) {
            System.out.println("Empty stack");
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