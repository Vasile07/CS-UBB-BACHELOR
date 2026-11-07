package Domain;

import java.util.Objects;

public class Pair<E1,E2>{
    private E1 left;
    private E2 right;

    public Pair(E1 left, E2 right) {
        this.left = left;
        this.right = right;
    }

    public E1 getLeft() {
        return left;
    }

    public E2 getRight() {
        return right;
    }

    public void setLeft(E1 left) {
        this.left = left;
    }

    public void setRight(E2 right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
