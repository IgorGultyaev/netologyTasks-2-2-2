package com.company;

import java.util.Scanner;

public class Main {
    public static final int SIZE = 3;
    public static final char EMPTY = '-';
    public static final char CROSS = 'X';
    public static final char ZERO = 'O';
    public static void main(String[] args) {
        char[][] field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }

        Scanner scanner = new Scanner(System.in);

        boolean isCrossTurn = true;

        while (true) {
            printField(field);
            System.out.println("Ходят " + (isCrossTurn ? "крестики" : "нолики") + "!");
            String input = scanner.nextLine(); // "2 3"
            String[] parts = input.split(" "); // ["2" , "3"]
            int r = Integer.parseInt(parts[0]) - 1; // 2-1 = 1
            int c = Integer.parseInt(parts[1]) - 1; // 3-1 = 2
            if (field[r][c] != EMPTY) {
                System.out.println("Сюда ходить нельзя");
                continue;
            }

            field[r][c] = isCrossTurn ? CROSS : ZERO;
            if (isWin(field, isCrossTurn ? CROSS : ZERO)) {
                printField(field);
                System.out.println("Победили " + (isCrossTurn ? "крестики" : "нолики"));
                break;
            } else {
                if (isCrossTurn) {
                    isCrossTurn = false;
                } else {
                    isCrossTurn = true;
                }
            }
        }

        System.out.println("Игра закончена!");
    }

    // !!ВНИМАНИЕ!!
    // Работает только для 3x3
    // Этот метод вам и надо переписать
    public static boolean isWin(char[][] field, char player) {
        int countPlayerRow = 0;
        int countPlayerColumn = 0;
        int countPlayerUpDown = 0;
        int countPlayerDownUp = 0;
        boolean win = false;
        for (int column = 0; column < SIZE; column++) {

            for (int row = 0; row < SIZE; row++) {

                if (field[column][row] == player) {
                    countPlayerRow++;
                }
                if (field[row][column] == player) {
                    countPlayerColumn++;
                }
                if ((column == row) && (field[column][row] == player)) {
                    countPlayerUpDown++;
                }
                if ((column == (SIZE-row-1)) && (field[column][row] == player)) {
                    countPlayerDownUp++;
                }
            }
            if ((countPlayerRow == SIZE || (countPlayerColumn == SIZE) || (countPlayerDownUp == SIZE) || (countPlayerUpDown == SIZE))) {
                win = true;
            }
            countPlayerRow = 0;
            countPlayerColumn = 0;
        }

        return win;
    }

    public static void printField(char[][] field) {
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}