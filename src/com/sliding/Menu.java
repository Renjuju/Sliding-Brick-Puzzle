package com.sliding;

public class Menu {

    public void play() {
            String directory="levels/";
            String fileLocation = "SBP-level3.txt";
            Board board = new Board(directory + fileLocation);
            board.applyMove(8, "up");
            board.applyMove(5, "down");
            board.applyMove(8, "down");
            board.applyMove(5, "down");
            board.applyMove(8, "right");
    }

}
