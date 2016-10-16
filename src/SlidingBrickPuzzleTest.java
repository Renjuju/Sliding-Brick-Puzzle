import org.junit.*;

/**
 * Created by renju on 10/16/16.
 */
public class SlidingBrickPuzzleTest {

    Board board = new Board("levels/SBP-level0.txt");

    @Test
    public void expectBoardToReturnA2DArray() {
        try {
            int[][] boardArr = board.getBoard();
            Assert.assertNotNull(boardArr);
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Testing that searching results in winning
    @Test
    public void expectBoardToBeWinnerWhenDepthFirstSearch() {
        try {
            DepthFirstSearch search = new DepthFirstSearch("levels/SBP-level0.txt");
            int[][] boardArr = search.dfs();

            for (int[] aBoardArr : boardArr) {
                for (int anABoardArr : aBoardArr) {
                    if (anABoardArr == -1) {
                        Assert.fail("Dfs resulted in no winner");
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void expectBoardToBeWinnerWhenBreadthFirstSearch() {
        try {
            BreadthFirstSearch search = new BreadthFirstSearch("levels/SBP-level0.txt");
            int[][] boardArr = search.bfs();

            for (int[] aBoardArr : boardArr) {
                for (int anABoardArr : aBoardArr) {
                    if (anABoardArr == -1) {
                        Assert.fail("Dfs resulted in no winner");
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void expectBoardToBeWinnerWhenIterativeDeepening() {
        try {
            IterativeDeepeningSearch search = new IterativeDeepeningSearch("levels/SBP-level0.txt");
            int[][] boardArr = search.ids();

            for (int[] aBoardArr : boardArr) {
                for (int anABoardArr : aBoardArr) {
                    if (anABoardArr == -1) {
                        Assert.fail("Dfs resulted in no winner");
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    //End of search testing
}
