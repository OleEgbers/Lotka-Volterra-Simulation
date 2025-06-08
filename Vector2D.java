public class Vector2D {
    public final double x, y;

    public Vector2D(double x, double y) { this.x = x; this.y = y; }

    public Vector2D add(Vector2D o) { return new Vector2D(x + o.x, y + o.y); }
    public Vector2D subtract(Vector2D o) { return new Vector2D(x - o.x, y - o.y); }
    public Vector2D multiply(double f) { return new Vector2D(x * f, y * f); }
    public Vector2D normalize() {
        double len = length();
        return len == 0 ? new Vector2D(0, 0) : new Vector2D(x / len, y / len);
    }
    public Vector2D limit(double max) { return length() > max ? normalize().multiply(max) : this; }
    public double length() { return Math.sqrt(x * x + y * y); }
    public double distanceTo(Vector2D o) { return subtract(o).length(); }
}
