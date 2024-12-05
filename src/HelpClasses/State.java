package HelpClasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class State {

    public State parent;
    public Board board;
    public int depth;


    public State(Board board) {
        this.board = board;
        this.parent = null;
        this.depth = 0;

    }


    public State(State otherState) {
            this.parent = otherState.parent != null ? new State(otherState.parent) : null;
            this.board = new Board(otherState.board);
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }


    public List<Positions> getPossibleActions(int squirrelIndex) {
        List<Positions> possibleActions = new ArrayList<>();

        for (Positions direction : Positions.values()) {
            State nextState = this.ApplyMove(squirrelIndex, direction);

            if (nextState != this) {
                possibleActions.add(direction);
            }
        }
        return possibleActions;
    }



    public State ApplyMove(int squirrelIndex, Positions direction) {
        State nextState = new State(this);
        Squirrel squirrel = nextState.board.squirrels.get(squirrelIndex - 1);

        if (nextState.canMove(squirrel, direction)) {
            List<Pair> newCells = new ArrayList<>();
            for (Pair cell : squirrel.getCells()) {
                newCells.add(cell.move(direction));
            }

            squirrel.setCells(newCells);

            Pair oldNutPosition = squirrel.getNutPosition();
            Pair newNutPosition = oldNutPosition.move(direction);
            squirrel.setNutPosition(newNutPosition);

            if (!squirrel.isFilled()) {

                String targetCell = nextState.board.grid[newNutPosition.getRow_index()][newNutPosition.getColumn_index()];

                for (Hole hole : nextState.board.holes){
                    if(hole.getPosition().getRow_index() == newNutPosition.getRow_index() &&
                            hole.getPosition().getColumn_index() == newNutPosition.getColumn_index() &&
                            !hole.isFilled()){
                        hole.setFilled(true);
                        squirrel.setFilled(true);

                    }
                }

                if (targetCell.equals("H")) {
                    nextState.board.setHoleFilled(newNutPosition, true);
                    squirrel.setFilled(true);
                }
            }
            nextState.depth = this.depth + 1;
            nextState.parent = this;
            return nextState;
        } else {
            return this;
        }
    }


    public HashSet<State> generateNextStates() {
        HashSet<State> nextStates = new HashSet<>();

        for (int i = 0; i < board.squirrels.size(); i++) {
            int squirrelIndex = i + 1;

            List<Positions> possibleDirections = getPossibleActions(squirrelIndex);

            for (Positions direction : possibleDirections) {
                State nextState = this.ApplyMove(squirrelIndex, direction);
                if (!nextState.equals(this)) {
                    nextStates.add(nextState);
                }
            }
        }
        return nextStates;
    }


    public boolean canMove(Squirrel squirrel, Positions direction) {

        for (Pair part : squirrel.getCells()) {
            Pair newPos = part.move(direction);

            if (isWithinBounds(newPos)){
                String Pos1 = board.grid[part.getRow_index()][part.getColumn_index()];
                String Pos2 = board.grid[newPos.getRow_index()][newPos.getColumn_index()];

                if(Pos2 == "E" || Pos2 == "H" || Pos2 == "H*") {
                    continue;
                }
                else if(Pos1.length() == Pos2.length()) {
                    if(Pos1.charAt(1) != Pos2.charAt(1)) {return false;}
                }
                else {
                    return false;
                }
            } else {
                return false;
            }
        }

        Pair NutPosition = squirrel.getNutPosition();
        Pair newNutPosition = NutPosition.move(direction);

        if (isWithinBounds(newNutPosition)) {
            String Nut1 = board.grid[NutPosition.getRow_index()][NutPosition.getColumn_index()];
            String Nut2 = board.grid[newNutPosition.getRow_index()][newNutPosition.getColumn_index()];

            if(Nut2 == "E" || Nut2 == "H" || Nut2 == "H*") {
                return true;
            }
            else if (Nut1.length() == Nut2.length()) {
                if(Nut1.charAt(1) == Nut2.charAt(1)) {
                    return true;
                }
            }
            else {
                return false;
            }

        }
        return false;
    }


    public boolean isWithinBounds(Pair position) {
        return position.getRow_index() >= 0 && position.getRow_index() < 4 &&
                position.getColumn_index() >= 0 && position.getColumn_index() < 4;
    }


    public boolean isWin() {
        return board.squirrels.stream().allMatch(Squirrel::isFilled);
    }



    public void preStates() {
        if (this.parent != null) {
            this.parent.preStates();
        }
        this.board.displayBoard();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.deepEquals(board, state.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
