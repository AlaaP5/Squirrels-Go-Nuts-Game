package HelpClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {
    private final int size = 4;
    public String[][] grid;
    public List<Squirrel> squirrels;
    public List<Hole> holes;
    public List<Pair> flowers;

    public List<Squirrel> getSquirrels() {
        return squirrels;
    }

    public void setSquirrels(List<Squirrel> squirrels) {
        this.squirrels = squirrels;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    public List<Pair> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Pair> flowers) {
        this.flowers = flowers;
    }

    public Board(List<Squirrel> squirrels, List<Hole> holes, List<Pair> flowers) {
        grid = new String[size][size];
        this.squirrels = squirrels;
        this.holes = holes;
        this.flowers = flowers;
        updateGrid();
    }


    public Board(Board other) {
        this.grid = new String[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(other.grid[i], 0, this.grid[i], 0, size);
        }
        this.squirrels = new ArrayList<>();
        for (Squirrel squirrel : other.squirrels) {
            this.squirrels.add(new Squirrel(squirrel));
        }
        this.holes = new ArrayList<>();
        for (Hole hole : other.holes) {
            this.holes.add(new Hole(hole.getPosition(), hole.isFilled()));
        }
        this.flowers = new ArrayList<>();
        for (Pair flower : other.flowers) {
            this.flowers.add(new Pair(flower));
        }
        updateGrid();
    }

//    public List<Squirrel> Squirrels() {
//        return this.squirrels;
//    }


    private void updateGrid() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                    grid[i][j] = "E";
            }
        }

        for(Pair flower : flowers) {
            grid[flower.getRow_index()][flower.getColumn_index()] = "F";
        }

        for (Hole hole : holes) {
            grid[hole.getPosition().getRow_index()][hole.getPosition().getColumn_index()] = hole.isFilled() ? "H*" : "H";
        }

        int squirrelIndex = 1;
        for (Squirrel squirrel : squirrels) {
            for (Pair cell : squirrel.getCells()) {
//
                grid[cell.getRow_index()][cell.getColumn_index()] = "S" + squirrelIndex;
            }
            Pair nutPosition = squirrel.getNutPosition();
            grid[nutPosition.getRow_index()][nutPosition.getColumn_index()] = squirrel.isFilled() ? "S" + squirrelIndex : "N" + squirrelIndex;
            squirrelIndex++;
        }
    }


    public void displayBoard() {
        updateGrid();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(Objects.equals(grid[i][j], "F") || Objects.equals(grid[i][j], "H") || Objects.equals(grid[i][j], "E"))
                    System.out.print(grid[i][j] + "  ");
                else
                    System.out.print(grid[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.deepEquals(grid, board.grid) && Objects.deepEquals(squirrels, board.squirrels) && Objects.deepEquals(holes, board.holes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(grid), squirrels, holes);
    }


    public boolean isHoleFilled(Pair position) {
        for (Hole hole : holes) {
            if (hole.getPosition().equals(position)) {
                return hole.isFilled();
            }
        }
        return false;
    }


    public void setHoleFilled(Pair position, boolean filled) {
        for (Hole hole : holes) {
            if (hole.getPosition().equals(position)) {
                hole.setFilled(filled);
            }
        }
    }
}