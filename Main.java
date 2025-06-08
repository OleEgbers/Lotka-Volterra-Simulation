import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Lotka-Volterra Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        SimulationPanel simulationPanel = new SimulationPanel();
        frame.add(simulationPanel);
        frame.setVisible(true);

        JFrame chartFrame = new JFrame("Populationskurve");
        chartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chartFrame.setSize(600, 400);
        chartFrame.setLocation(frame.getX() + frame.getWidth(), frame.getY());

        PopulationChart chart = new PopulationChart(simulationPanel);
        chartFrame.add(chart);
        chartFrame.setVisible(true);
    }
}
