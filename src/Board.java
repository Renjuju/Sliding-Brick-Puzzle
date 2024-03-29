import java.io.IOException;
import java.util.*;

public class Board {

    private int[][] board;
    private BoardMovement movement;
    public Board() {

    }

    // constructor
    public Board(String file) {
        // File fetching class
        RetrieveBoard boardFetch = null;
        try {
            boardFetch = new RetrieveBoard(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        board = boardFetch.getBoard();

        printBoard(board);

        if(isWinner(board)) {
            System.out.println("Winner!");
        }

        movement = new BoardMovement();
    }

    // applies Move, not used for now, using separate method
    public void applyMove(int block, String direction) {
        movement.move(block, direction, board);
        printBoard(board);
        if(isWinner(board)) {
            System.out.println("Winner!");
        } else {
            System.out.println("Keep playing!");
        }
    }

    // Cloning moves, isn't used yet
    public int[][] applyMoveCloning(int block, String direction) {
        int[][] newBoard = getClone(board);
        movement.move(block, direction, newBoard);
        return newBoard;
    }

    // clones
    public int[][] getClone(int[][] board) {
        int[][] result = new int[board.length][];
        for(int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    //    prints board, it uses color!
    public static void printBoard(int[][] board) {
        for(int x = 0; x < board.length; x++) {
            for(int i = 0; i < board[x].length; i++) {
                System.out.printf("%4d", board[x][i]);
            }
            System.out.println();
        }
        Arrays.deepToString(board);
        System.out.println((char)27 + "[31m------------------------------" + (char)27 + "[0m");
    }

    // checks for the winner
    public boolean isWinner(int [][] board) {
        for (int[] aBoard : board) {
            for (int anABoard : aBoard) {
                if (anABoard == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    // normalizes the state
    public void normalizeState() {
        int [][] matrix = getClone(board);

        int nextIdx = 3;
        for(int i = 0;i < matrix.length;i++) {
            for(int j = 0;j < matrix[i].length;j++) {
                if (matrix[i][j]==nextIdx) {
                    nextIdx++;
                } else if (matrix[i][j] > nextIdx) {
                    swapIdx(nextIdx, matrix[i][j]);
                    nextIdx++;
                }
            }
        }
    }

    public static int[][] normalizeState(int[][] board) {
        int nextIdx = 3;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j]==nextIdx) {
                    nextIdx++;
                } else if (board[i][j] > nextIdx) {
                    swapIdx(nextIdx, board[i][j], board);
                    nextIdx++;
                }
            }
        }
        return board;
    }

    private static void swapIdx(int idx1, int idx2, int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j] == idx1) {
                    board[i][j] = idx2;
                } else if (board[i][j] == idx2) {
                    board[i][j]=idx1;
                }
            }
        }
    }

    private void swapIdx(int idx1, int idx2) {
        int[][] matrix = board;

        for(int i = 0;i < matrix.length;i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == idx1) {
                    matrix[i][j] = idx2;
                } else if (matrix[i][j] == idx2) {
                    matrix[i][j]=idx1;
                }
            }
        }
    }

    // State comparison function
    public boolean isIdentical(int[][] board1, int[][] board2 ) {
        // Checks for sizes
        if(board1.length != board2.length) {
            return false;
        }

        if(board1[board1.length-1].length != board2[board2.length-1].length) {
            return false;
        }

        for(int i = 0; i < board1.length; i++) {
            for(int j = 0; j < board1[i].length; j++) {
                if(board1[i][j] != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // option to print the random walks
    public void randomWalks(int steps, boolean isPrint) {
        int count = 0;
        while(count < steps) {
            if(randomMove()) {
                count++;
                if(isPrint) {
                    printBoard(board);
                }
            }

            normalizeState();

            if(isWinner(board)) {
                System.out.println("Winner!");
                System.out.println(count + " moves");
                printBoard(board);
                break;
            }
        }
    }

    public boolean randomMove() {
        ArrayList<Integer> blockList = new ArrayList<>();
        ArrayList<String>  directionList = new ArrayList<>();

        // Gets list of viable blocks that can move
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != 1 && board[i][j] != -1 && board[i][j] != 0) {
                    if(blockList.indexOf(board[i][j]) == -1) {
                        blockList.add(board[i][j]);
                    }
                }
            }
        }

        Random random = new Random();
        int block = blockList.get(random.nextInt(blockList.size()));

        HashMap<String, Boolean> directionMap = movement.getMoves(board, block);

        Iterator iterator = directionMap.entrySet().iterator();
        while(iterator.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) iterator.next();
            if( (Boolean) pair.getValue() ) {
                directionList.add(pair.getKey().toString());
            }

        }

        // get random direction, then move the board
        if(directionList.size() > 0) {
            String direction = directionList.get(random.nextInt(directionList.size()));
            System.out.println(block + ": " + direction);
            movement.move(block, direction, board);
//            printBoard(board);
            return true;
        }
        return false;
    }

    public int[][] getBoard() {
        return board;
    }
}
