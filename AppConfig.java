import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static volatile AppConfig instance;  // wichtig: volatile

    private double predatorSpeed;
    private double preySpeed;
    private double preyReproductionRate;
    private double predatorReproductionRate;
    private int preyStartingPopulation;
    private int predatorStartingPopulation;

    private AppConfig() {
        loadProperties();
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties nicht gefunden im Classpath");
            }
            props.load(input);

            this.predatorSpeed = Double.parseDouble(props.getProperty("Predator.Speed", "1.8"));
            this.preySpeed = Double.parseDouble(props.getProperty("Prey.Speed", "1.2"));
            this.preyReproductionRate = Double.parseDouble(props.getProperty("Prey.ReproductionRate", "0.002"));
            this.preyStartingPopulation = Integer.parseInt(props.getProperty("Prey.StartingPopulation", "100"));
            this.predatorStartingPopulation = Integer.parseInt(props.getProperty("Predator.StartingPopulation", "25"));
            this.predatorReproductionRate = Double.parseDouble(props.getProperty("Predator.ReproductionRate", "0.001"));

        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Fehler beim Laden der Konfiguration", e);
        }
    }

    // Getter
    public double getPredatorSpeed() {
        return predatorSpeed;
    }

    public double getPreySpeed() {
        return preySpeed;
    }

    public double getPreyReproductionRate() {
        return preyReproductionRate;
    }

    public int getPreyStartingPopulation() {
        return preyStartingPopulation;
    }

    public int getPredatorStartingPopulation() {
        return predatorStartingPopulation;
    }

    public double getPredatorReproductionRate() {
        return predatorReproductionRate;
    }
    
}
