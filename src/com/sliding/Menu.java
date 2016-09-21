package com.sliding;

public class Menu {

    public void play() {
            String directory="levels/";
            String fileLocation = "SBP-level3.txt";
            Board board = new Board(directory + fileLocation);
            board.applyMove(8, "up");
    }

}
