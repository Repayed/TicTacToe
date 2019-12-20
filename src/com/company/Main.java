package com.company;

import java.util.*;

public class Main {

    private static ArrayList<Integer> playerPositions = new ArrayList<>();
    private static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while (true) {
            System.out.println("Enter your placement (1-9):");
            int playerPos = scanner.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("That position is already taken, please try again.");
                System.out.println("Enter your placement (1-9):");
                playerPos = scanner.nextInt();
            }
            pickPlacement("player", gameBoard, playerPos);

            int cpuPosition = random.nextInt(9) + 1;
            while (cpuPositions.contains(cpuPosition) || playerPositions.contains(cpuPosition)) {
                cpuPosition = random.nextInt(9) + 1;
            }
            pickPlacement("cpu", gameBoard, cpuPosition);

            printGameBoard(gameBoard);

            if(checkWinner().length() > 0) {
                printGameBoard(gameBoard);
                printWinner();
                break;
            }
        }
    }

    private static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void printWinner() {
        if (checkWinner().equals("player")) {
            System.out.println("Congratulations! You (the player) have won the game.");
        } else if (checkWinner().equals("cpu")) {
            System.out.println("Aww! Better luck next time. The cpu has won the game.");
        } else if (checkWinner().equals("tie")) {
            System.out.println("You and the cpu have been tied.");
        }
    }

    private static void pickPlacement(String user, char[][] gameBoard, int pos) {
        char symbol = 'X';

        if (user.equals("player")) {
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    /**
     * @return the string name of the winner or "tie" if none if
     * neither the player nor the cpu met the winning conditions.
     */

    private static String checkWinner() {
        final int MAX_PLAYS = 9;

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List list : winningConditions) {
            if (playerPositions.containsAll(list)) {
                return "player";
            } else if (cpuPositions.containsAll(list)) {
                return "cpu";
            } else if (playerPositions.size() + cpuPositions.size() == MAX_PLAYS) {
                return "tie";
            }
        }

        return "";
    }

}
