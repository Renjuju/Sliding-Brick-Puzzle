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
        while(!options.equals("7")) {
            printInstructions();

            if(board.isWinner(board.getBoard())) {
                System.out.println("Winner, menu stopping");
                break;
            }

            options = in.nextLine();

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
                    System.out.println("Breadth first search");
                    BreadthFirstSearch search = new BreadthFirstSearch(file);
                    double start = System.currentTimeMillis();
                    search.bfs();
                    double end = System.currentTimeMillis();
                    System.out.println("Took : " + ((end - start) / 1000 + " seconds"));
                    break;

                case "5":

                    System.out.println("Depth first search");
                    DepthFirstSearch dfsSearch = new DepthFirstSearch(file);
                    start = System.currentTimeMillis();
                    dfsSearch.dfs();
                    end = System.currentTimeMillis();
                    System.out.println("Took : " + ((end - start) / 1000 + " seconds"));
                    break;

                case "6":
                    System.out.println("Iterative deepening search");
                    IterativeDeepeningSearch idsSearch = new IterativeDeepeningSearch(file);
                    start = System.currentTimeMillis();
                    idsSearch.ids();
                    end = System.currentTimeMillis();
                    System.out.println("Took : " + ((end - start) / 1000 + " seconds"));
                    break;

                default:
                    break;
            }
        }
    }

    private void printInstructions() {
        System.out.println("Welcome to the Sliding Brick Puzzle Game");
        System.out.println("Please choose:");
        System.out.println("1: Random walks");
        System.out.println("2: Random walks with output");
        System.out.println("3: Random walks where user sets N steps");
        System.out.println("4: Breadth first search");
        System.out.println("5: Depth first search");
        System.out.println("6: Iterative deepening search");
        System.out.println("7: Exit");
    }

}
