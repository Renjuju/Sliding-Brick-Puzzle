import java.util.*;

/**
 * Created by renju on 10/17/16.
 */
public class AStarSearch extends Search {

    public AStarSearch(String fileName) {
        super(fileName);
    }

    public int[][] search() {
        int mDistance = manhattanDistanceOf(root.board);

        Comparator<Node> comparator = (o1, o2) -> {
            if(o1.getFScore() > o2.getFScore()) {
                return 0;
            }
            return -1;
        };

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(10, comparator);

        Node node = expand(root);

        node.setFScore(mDistance);
//        //add to list

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
//        return root.board;
    }


    private int manhattanDistanceOf(int[][] board) {

        int x1Position = 0, x2position = 0;
        int y1Position = 0, y2position = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == -1 && x2position == 0 && y2position == 0) {
                    x2position = i;
                    y2position = j;
                }

                if(board[i][j] == 2 && x1Position == 0 && y1Position == 0) {
                    x1Position = i;
                    y1Position = j;
                }
            }
        }

        // Calculating the manhattan distance
        return Math.abs(x2position-x1Position) + Math.abs(y2position-y1Position);
    }

//    A* expansion
    public Node expand(Node parent) {

        if(boardMovement.isWinner(parent.board)) {
            parent.isWinner(true);
            return parent;
        }

        ArrayList<Integer> blocks = boardMovement.getAllBlocks(parent.board);

        HashMap<String, Boolean> availableMoves;

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
                    if(visitedStates.containsKey(board2Str(Board.normalizeState(boardMovement.getClone(newBoard))))) {
                        break;
                    } else {
                        visitedStates.put(board2Str(Board.normalizeState(boardMovement.getClone(newBoard))), parent);
                    }

                    Node child = new Node(parent);

                    child.isWinner(boardMovement.isWinner(parent.board));

                    child.setBlock(block);
                    child.setBoard(newBoard);
                    child.setMove(pair.getKey().toString());
                    int cost = 1 + manhattanDistanceOf(newBoard) + parent.getFScore();
                    child.setFScore(cost);
                    parent.addChild(child);
                }
            }
        }
        return parent;
    }
}
