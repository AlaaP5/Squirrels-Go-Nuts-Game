package Player;

import HelpClasses.Positions;
import HelpClasses.State;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class User {

    State state;

    public User (State state) {
        this.state = state;
    }


    public void Play() {

        Scanner input = new Scanner(System.in);

        while (true) {

            try {

                System.out.println("Current State :");
                state.board.displayBoard();

                HashSet<State> states = state.generateNextStates();
                System.out.println("  ");

                int j=1;
                for (State state1 : states) {
                    System.out.println("State" + j + ":");
                    state1.board.displayBoard();
                    System.out.println("==========================");
                    j++;
                }

                System.out.println("Enter squirrel number to move:");
                int numberSquirrel = input.nextInt();

                if (numberSquirrel > state.board.squirrels.size() || numberSquirrel < 1) {
                    System.out.println("Enter a valid squirrel number.");
                    continue;
                }

                List<Positions> directions = state.getPossibleActions(numberSquirrel);
                if (directions == null || directions.isEmpty()) {
                    System.out.println("No directions available for this squirrel.");
                    continue;
                }

                System.out.println("Choose direction for Squirrel " + numberSquirrel + ":");
                for (int i = 0; i < directions.size(); i++) {
                    System.out.println((i + 1) + "- " + directions.get(i));
                }

                int dirIndex = input.nextInt() - 1;
                if (dirIndex < 0 || dirIndex >= directions.size()) {
                    System.out.println("Invalid direction selection.");
                    continue;
                }

                Positions selectedDirection = directions.get(dirIndex);

                State nextState = state.ApplyMove(numberSquirrel, selectedDirection);

                if (nextState != state) {
                    state = nextState;
                }

                if (state.isWin()) {
                    System.out.println(" ========     You Win     ========");
                    System.out.println("Depth to win: " + state.depth);
                    return;
                }
            }
            catch (InputMismatchException e){

                System.out.println("Please enter a valid integer.");
                input.nextLine();

            }
        }
    }

}
