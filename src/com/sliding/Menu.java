package com.sliding;

import java.util.Scanner;

public class Menu {

    private String file;

    public Menu(String file) {
        this.file = file;
    }

    Scanner in = new Scanner(System.in);
    public void play() {

        Board board = new Board(file);

        String options = "0";
        while(!options.equals("4")) {
            printInstructions();
            options = in.nextLine();

            if(board.isWinner(board.getBoard())) {
                System.out.println("Winner, menu stopping");
                break;
            }

            switch(options) {
                case "1" :
                    board.randomWalks(1000000, false);
                    break;
                case "2" :
                    board.randomWalks(1000000, true);
                    break;
                case "3" :
                    System.out.println("How many moves to complete?");
                    String steps = in.nextLine();
                    try{
                        board.randomWalks(Integer.parseInt(steps), true);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Invalid input");
                    }
                    break;
                case "4":
                    System.out.println("Have a great day");
                    break;
                default:
                    break;

            }
        }

    }

    public void printInstructions() {
        System.out.println("Welcome to the Sliding Brick Puzzle Game");
        System.out.println("Please choose:");
        System.out.println("1: Random walks");
        System.out.println("2: Random walks with output -- there's a lot of output");
        System.out.println("3: Random walks where user sets N steps");
        System.out.println("4: Quit");
    }

}
