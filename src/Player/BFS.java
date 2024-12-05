package Player;

import HelpClasses.State;

import java.util.*;

public class BFS {
    private final Queue<State> queue;
    private final Set<State> visitedStates;
    int numOfEnteredEle=0;
    int numOfPoppedEle=0;

    public BFS() {
        this.queue = new LinkedList<>();
        this.visitedStates = new HashSet<>();
    }

    public void findSolution(State initialState) {

        long startTime = System.currentTimeMillis();

        if (initialState == null) return;

        queue.add(initialState);

        System.out.println("The Initial State is :");
        initialState.board.displayBoard();

        int max = 0;
        while (!queue.isEmpty()) {
            State currentState = queue.remove();

            if(currentState.depth > max) {
                max = currentState.depth;
            }

            if (!visitedStates.contains(currentState)) {
                visitedStates.add(currentState);

                this.numOfPoppedEle++;

                System.out.println("---------------------------");

                if (currentState.isWin()) {
                    long endTime = System.currentTimeMillis();

                    System.out.println("Solution found!");
                    printSolutionPath(currentState);
                    System.out.println("Number of processed Elements: "+this.numOfPoppedEle);
                    System.out.println("Number of Entered Elements: "+this.numOfEnteredEle);
                    System.out.println("Time taken: " + (endTime - startTime) + " ms");
                    System.out.println("Max Depth : " + max);
                    System.out.println("Solution Depth : " + currentState.depth);
                    return;
                }

                HashSet<State>nextStates= currentState.generateNextStates();
                Iterator<State> it = nextStates.iterator();
                if (!nextStates.isEmpty()){
                    while (it.hasNext()) {
                        State s=it.next();
                        if (!visitedStates.contains(s)) {

                            s.setParent(currentState);
                            queue.add(s);
                            this.numOfEnteredEle++;
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("No solution found.");

        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

    private void printSolutionPath(State goalState) {
        Stack <State> path = new Stack<>();
        State currentState = goalState;

        while (currentState != null) {
            path.push(currentState);
            currentState = currentState.getParent();
        }

        while (!path.isEmpty()) {
            State state = path.pop();
            state.board.displayBoard();
            System.out.println("----");
        }
    }
}
