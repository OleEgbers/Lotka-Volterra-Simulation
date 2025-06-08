import java.awt.*;
import java.util.List;
import java.util.Random;

public class Prey extends Entity {
    private final Random random = new Random();
    AppConfig config = AppConfig.getInstance();

    public Prey(Vector2D pos) {
        super(pos);
        this.speed = config.getPreySpeed();
    }

    @Override
    public void update(List<Entity> entities, List<Entity> newEntities) {
        age++;

        velocity = velocity.add(new Vector2D(random.nextDouble() - 0.5, random.nextDouble() - 0.5).multiply(0.2));

        for (Entity other : entities) {
            if (other instanceof Predator) {
                double dist = position.distanceTo(other.position);
                if (dist < 20) dead = true; // direktes Fressen
                if (dist < 100) {
                    Vector2D away = position.subtract(other.position).normalize().multiply(2.5);
                    velocity = velocity.add(away); // starke Flucht
                }
            }
        }

        if (position.x < 50 || position.x > 750 || position.y < 50 || position.y > 550) {
            Vector2D center = new Vector2D(400, 300);
            velocity = velocity.add(center.subtract(position).normalize().multiply(0.5));
        }

        if (random.nextDouble() < config.getPreyReproductionRate()) {
            newEntities.add(new Prey(new Vector2D(
                position.x + random.nextInt(20) - 10,
                position.y + random.nextInt(20) - 10)));
        }
        move();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval((int) position.x, (int) position.y, 5, 5);
    }
}
