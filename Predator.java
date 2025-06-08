import java.awt.*;
import java.util.List;
import java.util.Random;

public class Predator extends Entity {
    private final Random random = new Random();
    AppConfig config = AppConfig.getInstance();

    public Predator(Vector2D pos) {
        super(pos);
        this.speed = config.getPredatorSpeed();
    }

    @Override
    public void update(List<Entity> entities, List<Entity> newEntities) {
        age++;
        Vector2D nearestPrey = null;
        double minDist = Double.MAX_VALUE;

        for (Entity e : entities) {
            if (e instanceof Prey p && !p.isDead()) {
                double dist = position.distanceTo(p.position);
                if (dist < minDist) {
                    minDist = dist;
                    nearestPrey = p.position;
                }
            }
        }

        velocity = nearestPrey != null ? nearestPrey.subtract(position).normalize() :
                                         new Vector2D(random.nextDouble() - 0.5, random.nextDouble() - 0.5);

        if (random.nextDouble() < config.getPredatorReproductionRate())
            newEntities.add(new Predator(new Vector2D(position.x + random.nextInt(20) - 10,
                                                      position.y + random.nextInt(20) - 10)));

        move();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) position.x, (int) position.y, 5, 5);
    }
}
