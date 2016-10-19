import java.util.ArrayList;

public class Node {

    Node parent;
    ArrayList<Node> children;
    int block;
    int[][] board;
    int depth;
    String move;
    int gScore;
    Integer fScore;
    boolean isWinner = false;

    public Node(int[][] board) {
        this.depth = 0;
        this.board = board;
        this.children = new ArrayList<>();
        this.gScore = 0;
        this.fScore = 0;
    }

    public Node(Node parent) {
        this.parent = parent;
        this.depth = this.parent.depth + 1;
        this.children = new ArrayList<>();
        this.fScore = parent.getFScore();
    }

    public void setMove(String move) {
        this.move = move;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void isWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public void setG(int gScore) {
        this.gScore = gScore;
    }

    public int getG() {
        return this.gScore;
    }

    public void setFScore(Integer fScore) {
        this.fScore = fScore;
    }

    public Integer getFScore() {
        return this.fScore;
    }

    public void killChildren() {
        this.children = new ArrayList<>();
    }

    public boolean isWinner() {
        return this.isWinner;
    }

}
