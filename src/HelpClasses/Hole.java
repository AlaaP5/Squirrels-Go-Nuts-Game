package HelpClasses;

import java.util.Objects;

public class Hole {
    public Pair position;
    public boolean isFilled;

    public Hole(Pair position) {
        this.position = position;
        this.isFilled = false;
    }

    public Hole(Pair position, boolean isFilled) {
        this.position = new Pair(position);
        this.isFilled = isFilled;
    }

    public Pair getPosition() {
        return position;
    }

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
        Hole hole = (Hole) o;
        return isFilled == hole.isFilled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isFilled);
    }
}
