package main.chess;

public class Vec2D {
    private int x;
    private int y;

    public Vec2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (!other.getClass().equals(this.getClass())) return false;

        Vec2D otherVec = (Vec2D)other;
        return otherVec.getX() == this.getX() && otherVec.getY() == this.getY();
    }
}
