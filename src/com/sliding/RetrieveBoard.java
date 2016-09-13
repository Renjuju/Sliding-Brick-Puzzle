package com.sliding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RetrieveBoard {

    private int board[][];

    public RetrieveBoard (String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = br.readLine();
            String[] numbers = line.split(",");

            int cols = Integer.parseInt(numbers[0]);
            int rows = Integer.parseInt(numbers[1]);
            board = new int[rows][cols];

            int count = 0;
            numbers = br.readLine().split(",");
            while (numbers != null) {
                if(count > rows-1) {
                    return;
                }
                for(int i = 0; i < cols; i++) {
                    board[count][i] = Integer.parseInt(numbers[i]);
                }
                count++;
                String temp = br.readLine();

                if(temp != null) {
                    numbers = temp.split(",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public int[][] getBoard() {
        return board;
    }
}
