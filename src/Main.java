import HelpClasses.*;
import Player.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n======  Welcome to Logic Squirrels Go Nuts Game  ======");
                System.out.println("Choose one of the level to start playing:");
                System.out.println("1 to 8");

                int numberLevel = input.nextInt();
                if (numberLevel < 1 || numberLevel > 8) {
                    System.out.println("Enter a number between 1 and 8.");
                    continue;
                }

                Levels level = new Levels(numberLevel);
                State state = level.getLevel();

                System.out.println("""
                        Choose Play Type:
                        1- User
                        2- DFS
                        3- BFS
                        4- UCS
                        5- A*
                        6- Stop
                        """);

                int playType = input.nextInt();

                switch (playType) {
                    case 1 -> {
                        User user = new User(state);
                        user.Play();
                    }

                    case 2 -> {
                        DFS dfs = new DFS();
                        dfs.findSolution(state);
                    }
                    case 3 -> {
                        BFS bfs = new BFS();

                        bfs.findSolution(state);
                    }
                    case 4 -> {
                        UCS ucs = new UCS();
                        ucs.findSolution(state);
                    }
                    case 5 -> {
                        AStarAlgorithm a = new AStarAlgorithm();
                        a.solve(state);
                    }
                    case 6 -> {
                        System.out.println("The Game is stopped");
                        return;
                    }
                    default -> System.out.println("Invalid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                input.nextLine();
            }
        }
    }
}