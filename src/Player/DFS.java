package Player;

import HelpClasses.State;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class DFS {
    private final Stack<State> stack;
    private final Set<State> visitedStates;
    int numOfEnteredEle=0;
    int numOfPoppedEle=0;

    public DFS() {
        this.stack = new Stack<>();
        this.visitedStates = new HashSet<>();
    }

    public void findSolution(State initialState) {

        long startTime = System.currentTimeMillis();

        if (initialState == null) return;

        stack.push(initialState);

        System.out.println("The Initial State is :");
        initialState.board.displayBoard();

        int max = 0;
        while (!stack.isEmpty()) {

            State currentState = stack.pop();

            if(currentState.depth > max) {
                max = currentState.depth;
            }


            if (!visitedStates.contains(currentState)) {
                visitedStates.add(currentState);
                numOfPoppedEle++;

                System.out.println("---------------------------");
                if (currentState.isWin()) {

                    long endTime = System.currentTimeMillis();

                    System.out.println("Solution found!");

                    currentState.preStates();
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
                            stack.push(s);
                            numOfEnteredEle++;
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("No solution found.");

        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
