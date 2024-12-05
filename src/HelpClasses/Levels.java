package HelpClasses;

import java.util.ArrayList;
import java.util.List;

public class Levels {

    private int numberLevel;

    public Levels(int numberLevel) {
        this.numberLevel = numberLevel;
    }

    public State getLevel() {

        State initialState = null;
        List<Squirrel> squirrels = new ArrayList<>();
        List<Hole> holes = new ArrayList<>();
        List<Pair> flowers = new ArrayList<>();

        switch (this.numberLevel) {

            case 1 -> {

                squirrels.add(new Squirrel(List.of(new Pair(1, 2), new Pair(2,3)), new Pair(2, 2),false));
                squirrels.add(new Squirrel(List.of(new Pair(1, 1)), new Pair(0, 1), false));
                holes.add(new Hole(new Pair(0, 2), false));
                holes.add(new Hole(new Pair(3,3), false));
                holes.add(new Hole(new Pair(2,1), false));
                flowers.addAll(List.of(new Pair(1, 0)));
                initialState = new State(new Board(squirrels, holes, flowers));
            }

            case 2 -> {

                squirrels.add(new Squirrel(List.of(new Pair(0, 0), new Pair(1,0)), new Pair(0, 1),false));
                squirrels.add(new Squirrel(List.of(new Pair(3, 1)), new Pair(3, 0), false));
                holes.add(new Hole(new Pair(1, 0), false));
                holes.add(new Hole(new Pair(3,3), false));
                holes.add(new Hole(new Pair(2,1), false));
                flowers.addAll(List.of(new Pair(0, 2)));
                initialState = new State(new Board(squirrels, holes, flowers));
            }

            case 3 -> {

                squirrels.add(new Squirrel(List.of(new Pair(1, 2), new Pair(0,3)), new Pair(1, 3),false));
                squirrels.add(new Squirrel(List.of(new Pair(2, 3), new Pair(3,3)), new Pair(3, 2), false));
                holes.add(new Hole(new Pair(0, 2), false));
                holes.add(new Hole(new Pair(3,3), false));
                holes.add(new Hole(new Pair(2,1), false));
                flowers.addAll(List.of(new Pair(1, 0)));
                initialState = new State(new Board(squirrels, holes, flowers));
            }

            case 4 -> {

                squirrels.add(new Squirrel(List.of(new Pair(3, 0), new Pair(2,1)), new Pair(3, 1),false));
                squirrels.add(new Squirrel(List.of(new Pair(3, 2)), new Pair(2, 2), false));
                holes.add(new Hole(new Pair(1, 0), false));
                holes.add(new Hole(new Pair(3,3), false));
                holes.add(new Hole(new Pair(2,1), false));
                flowers.addAll(List.of(new Pair(0, 2)));
                initialState = new State(new Board(squirrels, holes, flowers));
            }

            case 5 -> {

                squirrels.add(new Squirrel(List.of(new Pair(3, 0), new Pair(3,1)), new Pair(2, 0),false));
                squirrels.add(new Squirrel(List.of(new Pair(1, 0), new Pair(0,1)), new Pair(1, 1), false));
                holes.add(new Hole(new Pair(0, 2), false));
                holes.add(new Hole(new Pair(1,0), false));
                holes.add(new Hole(new Pair(2,1), false));
                flowers.addAll(List.of(new Pair(3, 3)));
                initialState = new State(new Board(squirrels, holes, flowers));
            }

            case 6 -> {

                squirrels.add(new Squirrel(List.of(new Pair(0, 2)), new Pair(0, 3),false));
                squirrels.add(new Squirrel(List.of(new Pair(1, 2), new Pair(2,3)), new Pair(1, 3), false));
                squirrels.add(new Squirrel(List.of(new Pair(2, 1), new Pair(3,1)), new Pair(2, 2),false));
                holes.add(new Hole(new Pair(0, 2), false));
                holes.add(new Hole(new Pair(1,0), false));
                holes.add(new Hole(new Pair(2,1), false));
                holes.add(new Hole(new Pair(3,3), false));
                initialState = new State(new Board(squirrels, holes, flowers));
            }

            case 7 -> {

                squirrels.add(new Squirrel(List.of(new Pair(0, 0), new Pair(0,1)), new Pair(1, 1),false));
                squirrels.add(new Squirrel(List.of(new Pair(1, 2), new Pair(2,3)), new Pair(1, 3), false));
                squirrels.add(new Squirrel(List.of(new Pair(3, 0)), new Pair(3, 1),false));
                squirrels.add(new Squirrel(List.of(new Pair(2, 2)), new Pair(3, 2),false));
                holes.add(new Hole(new Pair(0, 2), false));
                holes.add(new Hole(new Pair(1,0), false));
                holes.add(new Hole(new Pair(2,1), false));
                holes.add(new Hole(new Pair(3,3), false));
                initialState = new State(new Board(squirrels, holes, flowers));
            }

            case 8 -> {
                squirrels.add(new Squirrel(List.of(new Pair(0, 0), new Pair(0, 1)), new Pair(0, 2), false));
                squirrels.add(new Squirrel(List.of(new Pair(1, 1), new Pair(1, 2)), new Pair(1, 3), false));
                squirrels.add(new Squirrel(List.of(new Pair(3, 0), new Pair(3, 1)), new Pair(3, 2), false));
                holes.add(new Hole(new Pair(0, 3), false));
                holes.add(new Hole(new Pair(3, 3), false));
                holes.add(new Hole(new Pair(2, 2), false));
                flowers.addAll(List.of(new Pair(0, 1), new Pair(2, 1)));
                initialState = new State(new Board(squirrels, holes, flowers));
            }



            default -> {
                System.out.println("This level does not exist.");
            }
        }

        return initialState != null ? new State(initialState) : null;
    }
}
