package com.sliding;

import java.util.Scanner;

public class Menu {

    Scanner in = new Scanner(System.in);
    public void play() {

        String options = "0";
        while(!options.equals("3")) {
            printInstructions();
            options = in.nextLine();
            switch(options) {
                case "1" :
                    String fileLocation = "SBP-level1.txt";
                    String directory="levels/";
                    Board board = new Board(directory + fileLocation);
                    board.randomWalks(1000000, false);
                    break;
                case "2" :
                    fileLocation = "SBP-level1.txt";
                    directory="levels/";
                    board = new Board(directory + fileLocation);
                    board.randomWalks(1000000, true);
                    break;
                case "3" :
                    System.out.println("Have a great day");
                    break;
                default: System.out.println("Please select a normal option");
                        options = in.nextLine();
                    break;

            }
        }

    }

    public void printInstructions() {
        System.out.println("Welcome to the Sliding Brick Puzzle Game");
        System.out.println("Please choose:");
        System.out.println("1: Random walks");
        System.out.println("2: Random walks with output -- there's a lot of output");
        System.out.println("3: Quit");
    }

}
