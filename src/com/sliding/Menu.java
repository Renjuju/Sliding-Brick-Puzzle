package com.sliding;

public class Menu {

    public void play() {
            String directory="levels/";
            String fileLocation = "SBP-level3.txt";
//          String fileLocation = "SBP-test-not-normalized.txt";
            Board board = new Board(directory + fileLocation);
            board.randomWalks(1000000);


    }

}
