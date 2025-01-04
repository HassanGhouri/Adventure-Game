package views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

public class HealthBar extends HBox {
    public Label healthBarLabel = new Label("HEALTH     ");
    public ProgressBar progressBar;
    public HealthBar(double health) {
        // Initialize ProgressBar
        progressBar = new ProgressBar();
        double normalizedHealth = health / 100.0;
        progressBar.setProgress(normalizedHealth);
        this.setAlignment(Pos.CENTER);
        progressBar.setPrefHeight(20);
        progressBar.setPrefWidth(500);
        progressBar.setStyle("-fx-accent: red; -fx-border-width: 1;");

        healthBarLabel.setStyle("-fx-text-fill: White; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        Label offset = new Label("           ");
        offset.setStyle("-fx-text-fill: BLACK; -fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");

        // Add ProgressBar to StackPane
        getChildren().add(healthBarLabel);
        getChildren().add(progressBar);
        getChildren().add(offset);
    }

    // Set health value (0.0 for empty, 1.0 for full)
    public void setHealth(double health) {
        double normalizedHealth = health / 100.0;
        progressBar.setProgress(normalizedHealth);
    }
}