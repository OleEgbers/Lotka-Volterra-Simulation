# Lotka-Volterra Simulation (Räuber-Beute-Modell)

Dieses Java-Projekt simuliert das klassische Lotka-Volterra-Räuber-Beute-Modell mit einer grafischen Oberfläche. Die Populationen von Räubern (rot) und Beute (grün) werden in Echtzeit simuliert und als Kurve dargestellt.

## Features

- **Simulation von Räuber- und Beutepopulationen** auf einer 2D-Fläche
- **Konfigurierbare Parameter** (Geschwindigkeit, Reproduktionsrate, Startpopulation) über `config.properties`
- **Echtzeit-Grafik** der Populationen und deren Entwicklung als Liniendiagramm
- **Einfache Erweiterbarkeit** durch objektorientiertes Design

## Projektstruktur

- `Main.java` – Startpunkt der Anwendung, öffnet Simulations- und Diagrammfenster
- `SimulationPanel.java` – Zeichnet und simuliert die Entitäten (Räuber & Beute)
- `PopulationChart.java` – Zeigt den Verlauf der Populationen als Diagramm
- `Entity.java` – Abstrakte Basisklasse für `Prey` und `Predator`
- `Prey.java` – Implementiert das Verhalten der Beute
- `Predator.java` – Implementiert das Verhalten der Räuber
- `Vector2D.java` – Hilfsklasse für 2D-Vektorrechnung
- `AppConfig.java` – Lädt die Konfiguration aus `config.properties`
- `config.properties` – Konfigurationsdatei für Simulationsparameter

## Konfiguration

Passe die Datei `config.properties` an, um Parameter wie Geschwindigkeit, Reproduktionsrate und Startpopulation zu ändern:

```properties
Predator.Speed=1.6
Prey.Speed=1.2
Prey.ReproductionRate=0.002
Predator.ReproductionRate=0.001
Prey.StartingPopulation=150
Predator.StartingPopulation=25
```

## Kompilieren und Ausführen

1. **Kompilieren:**
   ```sh
   javac *.java
   ```

2. **Starten:**
   ```sh
   java Main
   ```

Stelle sicher, dass sich `config.properties` im Klassenpfad befindet (im selben Verzeichnis wie die `.class`-Dateien oder im Ressourcenordner).

## Hinweise

- Die Simulation läuft in Echtzeit und kann durch Anpassen der Parameter in `config.properties` beeinflusst werden.
- Die Diagrammansicht öffnet sich in einem separaten Fenster.
- Die Anwendung benötigt Java 8 oder höher.
