package HelpClasses;

import java.util.Objects;

public class Pair {


    public int row_index;
    public int column_index;


    public Pair() {}
    public Pair(int x,int y) {
        row_index=x;
        column_index=y;

    }

    public Pair(Pair pair)
    {
        this.row_index = pair.row_index;
        this.column_index = pair.column_index;
    }


    public Pair move(Positions direction) {
        return switch (direction) {
            case UP -> new Pair(row_index - 1, column_index);
            case DOWN -> new Pair(row_index + 1, column_index);
            case LEFT -> new Pair(row_index, column_index - 1);
            case RIGHT -> new Pair(row_index, column_index + 1);
        };
    }


    public int getRow_index() {
        return row_index;
    }

    public void setRow_index(int row_index) {
        this.row_index = row_index;
    }

    public int getColumn_index() {
        return column_index;
    }

    public void setColumn_index(int column_index) {
        this.column_index = column_index;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row_index == pair.row_index && column_index == pair.column_index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row_index, column_index);
    }
}
