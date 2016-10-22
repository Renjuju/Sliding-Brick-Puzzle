import java.io.IOException;
import java.util.*;

/**
 * renju, 10/7/16.
 */
public class Search {

    int[][] board;
    Node root;
    private HashMap<String, Node> visitedStates = new HashMap<>();

    private int x1Position = 0, x2position = 0;
    private int y1Position = 0, y2position = 0;
    private String heuristic;

    public Search(String file) {
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        board = boardFetch.getBoard();
        root = new Node(board);
    }

    public Search(String file, String heuristic) {
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        board = boardFetch.getBoard();
        root = new Node(board);
        this.heuristic = heuristic;
    }

    private BoardMovement boardMovement = new BoardMovement();

    public Node expand(Node parent) {

        if(boardMovement.isWinner(parent.board)) {
            parent.isWinner(true);
            return parent;
        }

        ArrayList<Integer> blocks = boardMovement.getAllBlocks(parent.board);

        HashMap<String, Boolean> availableMoves = new HashMap<>();
        for(int block: blocks) {

            availableMoves = boardMovement.getMoves(parent.board, block);

            for (Object o : availableMoves.entrySet()) {
                HashMap.Entry pair = (HashMap.Entry) o;
                if (pair.getValue().toString().equals("true")) {

                    //We know the block and the possible move state so we add it to a child node
                    int[][] newBoard = boardMovement.getClone(parent.board);

                    boardMovement.move(block, pair.getKey().toString(), newBoard);
                    if (visitedStates.containsKey(board2Str(Board.normalizeState(boardMovement.getClone(newBoard))))) {
                        break;
                    } else {
                        visitedStates.put(board2Str(Board.normalizeState(boardMovement.getClone(newBoard))), parent);
                    }

                    Node child = new Node(parent);

                    child.isWinner(boardMovement.isWinner(parent.board));

                    child.setBlock(block);
                    child.setBoard(newBoard);
                    child.setMove(pair.getKey().toString());

                    int heuristic = 0;


                    if(this.heuristic != null && this.heuristic.equals("manhattan")) {
                        heuristic = manhattanDistanceOf(newBoard);
                    } else if(this.heuristic != null && this.heuristic.equals("euclidean")){
                        heuristic = euclideanDistanceOf(newBoard);
                    }

                    int cost = parent.depth + 1 + heuristic + parent.getFScore();
                    child.setFScore(cost);

                    parent.addChild(child);
                }
            }
        }
        return parent;
    }

    protected void setGoalPositions(int[][] board) {
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
    }

    protected int manhattanDistanceOf(int[][] board) {
        setGoalPositions(board);
        // Calculating the manhattan distance
        return Math.abs(x2position-x1Position) + Math.abs(y2position-y1Position);
    }

    protected int euclideanDistanceOf(int[][] board) {
        setGoalPositions(board);
        // Calculating the euclidean distance heuristic
        return (int) Math.sqrt(Math.pow(2, Math.abs(x2position-x1Position)) + Math.pow(2 ,Math.abs(y2position-y1Position)));
    }

    public String board2Str(int board[][]) {
        StringBuilder sb = new StringBuilder();
        for(int[] s1 : board){
            sb.append(Arrays.toString(s1)).append('\n');
        }
        return sb.toString();
    }

    public int visitedStateSize() {
        return visitedStates.size();
    }
}
