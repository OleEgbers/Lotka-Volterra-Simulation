import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationPanel extends JPanel implements Runnable {
    private final List<Entity> entities = new ArrayList<>();
    private final Random random = new Random();
    public int preyCount = 0;
    public int predatorCount = 0;

    AppConfig config = AppConfig.getInstance();

    public SimulationPanel() {
        setBackground(Color.WHITE);
        initializeEntities();
        new Thread(this).start();
    }

    private void initializeEntities() {
        for (int i = 0; i < config.getPreyStartingPopulation(); i++) entities.add(new Prey(randomPosition()));
        for (int i = 0; i < config.getPredatorStartingPopulation(); i++) entities.add(new Predator(randomPosition()));
    }

    private Vector2D randomPosition() {
        return new Vector2D(random.nextInt(800), random.nextInt(600));
    }

    @Override
    public void run() {
        while (true) {
            updateSimulation();
            repaint();
            try { Thread.sleep(40); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    private void updateSimulation() {
        List<Entity> newEntities = new ArrayList<>();

        for (Entity e : entities) {
            e.update(entities, newEntities);
        }

        entities.removeIf(Entity::isDead);
        entities.addAll(newEntities);

        preyCount = (int) entities.stream().filter(e -> e instanceof Prey).count();
        predatorCount = (int) entities.stream().filter(e -> e instanceof Predator).count();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Entity e : entities) e.draw(g);

        g.setColor(Color.BLACK);
        g.drawString("Beute: " + preyCount, 20, 20);
        g.drawString("Räuber: " + predatorCount, 20, 40);
    }

    public int getPreyCount() { return preyCount; }
    public int getPredatorCount() { return predatorCount; }
}
