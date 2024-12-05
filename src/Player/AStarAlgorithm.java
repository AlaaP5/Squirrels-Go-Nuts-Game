package Player;

import java.util.*;
import HelpClasses.*;

public class AStarAlgorithm {


public void solve(State initialState) {

    PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));

    Map<State, Integer> gScoreMap = new HashMap<>();

    Set<State> closedList = new HashSet<>();


    openList.add(new Node(initialState, null, 0, heuristic(initialState)));
    gScoreMap.put(initialState, 0);

    while (!openList.isEmpty()) {

        Node currentNode = openList.poll();
        State currentState = currentNode.state;


        if (currentState.isWin()) {
            System.out.println("Solution Found!");
            currentState.preStates();
            return;
        }


        closedList.add(currentState);


        HashSet<State> nextStates = currentState.generateNextStates();
        for (State nextState : nextStates) {
            if (closedList.contains(nextState)) continue;

            int g = currentNode.g + 1;
            int h = heuristic(nextState);
            int f = g + h;


            if (!gScoreMap.containsKey(nextState) || g < gScoreMap.get(nextState)) {

                gScoreMap.put(nextState, g);
                openList.add(new Node(nextState, currentNode, g, f));
            }
        }
    }

    System.out.println("No solution found!");
}

    private int heuristic(State state) {
        int heuristicValue = 0;

        for (Squirrel squirrel : state.board.getSquirrels()) {
            if (!squirrel.isFilled()) {
                Pair nutPosition = squirrel.getNutPosition();
                int minDistance = Integer.MAX_VALUE;

                for (Hole hole : state.board.getHoles()) {
                    if (!hole.isFilled()) {
                        Pair holePosition = hole.getPosition();
                        int distance = Math.abs(nutPosition.getRow_index() - holePosition.getRow_index()) +
                                Math.abs(nutPosition.getColumn_index() - holePosition.getColumn_index());
                        minDistance = Math.min(minDistance, distance);
                    }
                }
                heuristicValue += minDistance;
            }
        }

        return heuristicValue;
    }



    private static class Node {
        State state;
        Node parent;
        int g;
        int f;

        public Node(State state, Node parent, int g, int f) {
            this.state = state;
            this.parent = parent;
            this.g = g;
            this.f = f;
        }
    }
}

