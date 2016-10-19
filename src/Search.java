import java.io.IOException;
import java.util.*;

/**
 * Created by renju on 10/7/16.
 */
public class Search {

    int[][] board;
    Node root;
    protected HashMap<String, Node> visitedStates = new HashMap<>();

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

    BoardMovement boardMovement = new BoardMovement();

    public Node expand(Node parent) {

        if(boardMovement.isWinner(parent.board)) {
            parent.isWinner(true);
            return parent;
        }

        ArrayList<Integer> blocks = boardMovement.getAllBlocks(parent.board);

        HashMap<String, Boolean> availableMoves = new HashMap<>();
//        System.out.println("Depth: " + parent.depth);
//        System.out.println("Parent block: " + parent.block);
        for(int block: blocks) {

            availableMoves = boardMovement.getMoves(parent.board, block);
            //iterate through available moves, add to parent arrayList

            Iterator iterator = availableMoves.entrySet().iterator();

            while(iterator.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) iterator.next();
                if(pair.getValue().toString().equals("true")) {

//                    System.out.println("Block: " + block + ", " + "direction: " + pair.getKey().toString());
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

    protected int manhattanDistanceOf(int[][] board) {

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
