package Player;

import HelpClasses.State;
import java.util.*;

public class UCS {

    int numOfEnteredEle=0;
    int numOfPoppedEle=0;

    public void findSolution(State initialState) {
        long startTime = System.currentTimeMillis();

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        HashSet<State> visited = new HashSet<>();
        Map<State, Integer> distance = new HashMap<>(); // Map to track the shortest distance for each state

        priorityQueue.add(new Node(initialState, 0));
        distance.put(initialState, 0); // Distance to the initial state is 0

        int max = 0;

        while (!priorityQueue.isEmpty()) {

            Node currentNode = priorityQueue.poll();
            State currentState = currentNode.getState();

            // Track max depth reached
            if (currentState.depth > max) {
                max = currentState.depth;
            }

            // Check if goal state is reached
            if (currentState.isWin()) {
                long endTime = System.currentTimeMillis();

                System.out.println("Solution Found!");
                currentState.preStates();
                System.out.println("Number of processed Elements: " + this.numOfPoppedEle);
                System.out.println("Number of Entered Elements: " + this.numOfEnteredEle);
                System.out.println("Total Cost: " + currentNode.getCost());
                System.out.println("Max Depth : " + max);
                System.out.println("Time taken: " + (endTime - startTime) + " ms");
                return;
            }

            // Skip already visited states
            if (visited.contains(currentState)) continue;

            numOfPoppedEle++;
            visited.add(currentState);

            // Generate and evaluate next states
            for (State nextState : currentState.generateNextStates()) {
                int tentativeDistance = currentNode.getCost() + 1;

                // If nextState is unvisited or we found a shorter path
                if (!distance.containsKey(nextState) || tentativeDistance < distance.get(nextState)) {
                    distance.put(nextState, tentativeDistance); // Update shortest distance
                    priorityQueue.add(new Node(nextState, tentativeDistance)); // Add to priority queue
                    this.numOfEnteredEle++;
                }
            }
        }

        // If no solution is found
        long endTime = System.currentTimeMillis();
        System.out.println("No solution found.");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }


    private static class Node {
        private final State state;
        private final int cost;

        public Node(State state, int cost) {
            this.state = state;
            this.cost = cost;
        }

        public State getState() {
            return state;
        }

        public int getCost() {
            return cost;
        }
    }
}
