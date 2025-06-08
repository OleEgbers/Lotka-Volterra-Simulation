import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PopulationChart extends JPanel implements Runnable {
    private final SimulationPanel sim;
    private final LinkedList<Integer> preyData = new LinkedList<>();
    private final LinkedList<Integer> predatorData = new LinkedList<>();
    private int tick = 0;

    private static final int MAX_DATA_POINTS = 300;
    private static final int MARGIN_BOTTOM = 30;
    private static final int CHART_TOP_PADDING = 10;
    private volatile boolean running = true;

    public PopulationChart(SimulationPanel sim) {
        this.sim = sim;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (running) {
            tick++;
            preyData.add(sim.getPreyCount());
            predatorData.add(sim.getPredatorCount());

            if (preyData.size() > MAX_DATA_POINTS) {
                preyData.removeFirst();
                predatorData.removeFirst();
            }

            repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();


        int max = Math.max(
                preyData.stream().max(Integer::compareTo).orElse(0),
                predatorData.stream().max(Integer::compareTo).orElse(0)
        );
        if (max <= 0) max = 1; 

        g2.setColor(Color.GREEN);
        for (int i = 1; i < preyData.size(); i++) {
            int x1 = (i - 1) * w / MAX_DATA_POINTS;
            int y1 = h - MARGIN_BOTTOM - preyData.get(i - 1) * (h - MARGIN_BOTTOM - CHART_TOP_PADDING) / max;
            int x2 = i * w / MAX_DATA_POINTS;
            int y2 = h - MARGIN_BOTTOM - preyData.get(i) * (h - MARGIN_BOTTOM - CHART_TOP_PADDING) / max;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setColor(Color.RED);
        for (int i = 1; i < predatorData.size(); i++) {
            int x1 = (i - 1) * w / MAX_DATA_POINTS;
            int y1 = h - MARGIN_BOTTOM - predatorData.get(i - 1) * (h - MARGIN_BOTTOM - CHART_TOP_PADDING) / max;
            int x2 = i * w / MAX_DATA_POINTS;
            int y2 = h - MARGIN_BOTTOM - predatorData.get(i) * (h - MARGIN_BOTTOM - CHART_TOP_PADDING) / max;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setColor(Color.BLACK);
        g2.drawString("Tick: " + tick, 10, h - 10);
        g2.drawString("Beute: " + sim.getPreyCount(), 10, h - 25);
        g2.drawString("Räuber: " + sim.getPredatorCount(), 10, h - 40);

        g2.setColor(Color.GREEN);
        g2.drawString("Beute", w - 60, 20);
        g2.setColor(Color.RED);
        g2.drawString("Räuber", w - 60, 35);
    }

    public void stop() {
        running = false;
    }
}
