package HelpClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Squirrel {
    public List<Pair> cells;
    public Pair nutPosition;
    public boolean isFilled;


    public Squirrel(List<Pair> cells, Pair nutPosition, boolean isFilled) {
        this.cells = cells;
        this.nutPosition = nutPosition;
        this.isFilled = isFilled;
    }


    public Squirrel(Squirrel other) {
        this.cells = new ArrayList<>();
        for (Pair cell : other.cells) {
            this.cells.add(new Pair(cell));
        }
        this.nutPosition = new Pair(other.nutPosition);
        this.isFilled = other.isFilled;
    }


    public void setCells(List<Pair> cells) {
        this.cells = cells;
    }

    public List<Pair> getCells() {
        return cells;
    }


    public void setNutPosition(Pair nutPosition) {
        this.nutPosition = nutPosition;
    }

    public Pair getNutPosition() { return new Pair(nutPosition.getRow_index(), nutPosition.getColumn_index()); }


    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squirrel squirrel = (Squirrel) o;
        return isFilled == squirrel.isFilled && Objects.deepEquals(cells, squirrel.cells) && Objects.deepEquals(nutPosition, squirrel.nutPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells, nutPosition, isFilled);
    }
}
