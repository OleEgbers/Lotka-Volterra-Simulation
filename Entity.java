import java.awt.*;
import java.util.List;

public abstract class Entity {
    protected Vector2D position;
    protected Vector2D velocity = new Vector2D(0, 0);
    protected double speed = 1.5;
    protected boolean dead = false;
    protected int age = 0;
    protected int lifespan = 1000;

    public Entity(Vector2D pos) {
        this.position = pos;
    }

    public abstract void update(List<Entity> entities, List<Entity> newEntities);
    public abstract void draw(Graphics g);

    protected void move() {
        position = position.add(velocity.limit(speed));
        double x = position.x;
        double y = position.y;
        int width = 800;
        int height = 600;

        boolean reflected = false;

        if (x < 0 || x > width - 5) {
            velocity = new Vector2D(-velocity.x, velocity.y);
            reflected = true;
        }
        if (y < 0 || y > height - 5) {
            velocity = new Vector2D(velocity.x, -velocity.y);
            reflected = true;
        }

        if (reflected) {
            velocity = velocity.add(new Vector2D(Math.random() - 0.5, Math.random() - 0.5)).normalize();
        }

        position = new Vector2D(
            Math.max(0, Math.min(width - 5, position.x)),
            Math.max(0, Math.min(height - 5, position.y))
        );
    }

    public boolean isDead() {
        return dead || age > lifespan;
    }

    public int getAge() {
        return age;
    }
}
