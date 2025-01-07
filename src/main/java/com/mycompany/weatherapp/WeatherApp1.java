package com.mycompany.weatherapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import weather.api.WeatherService;
import weather.data.WeatherData;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * JavaFX application to display weather information
 */
public class WeatherApp1 extends Application {

    private final WeatherService weatherService = new WeatherService();
    private String selectedUnit = "metric"; // Default unit
    private String selectedLang = "en"; // Default language

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // UI Components
        Label cityLabel = new Label("Enter City Name:");
        TextField cityTextField = new TextField();
        Button getWeatherButton = new Button("Get Weather");
        Label weatherInfoLabel = new Label();
        ToggleButton themeToggleButton = new ToggleButton("Dark Mode");
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);

        // Language Selector
        Label langLabel = new Label("Select Language:");
        ComboBox<String> langSelector = new ComboBox<>();
        langSelector.getItems().addAll("English", "French", "Spanish", "German");
        langSelector.setValue("English");
        langSelector.setOnAction(e -> {
            String lang = langSelector.getValue();
            switch (lang) {
                case "French" -> selectedLang = "fr";
                case "Spanish" -> selectedLang = "es";
                case "German" -> selectedLang = "de";
                default -> selectedLang = "en";
            }
        });

        // Unit Selector
        Label unitLabel = new Label("Select Units:");
        ToggleGroup unitToggleGroup = new ToggleGroup();
        RadioButton metricButton = new RadioButton("Metric");
        metricButton.setToggleGroup(unitToggleGroup);
        metricButton.setSelected(true);
        RadioButton imperialButton = new RadioButton("Imperial");
        imperialButton.setToggleGroup(unitToggleGroup);

        unitToggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            selectedUnit = metricButton.isSelected() ? "metric" : "imperial";
        });

        VBox vbox = new VBox(10, cityLabel, cityTextField, langLabel, langSelector,
                unitLabel, metricButton, imperialButton, getWeatherButton,
                weatherInfoLabel, progressIndicator, themeToggleButton);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-alignment: center;");

        // Event Handling
        getWeatherButton.setOnAction(e -> {
            String city = cityTextField.getText();
            if (!city.isEmpty()) {
                try {
                    WeatherData weatherData = weatherService.getWeather(city, selectedUnit, selectedLang);
                    weatherInfoLabel.setText(weatherData.toString());

                    // Example weather condition logic
                    String weatherCondition = weatherData.getWeatherDesc();
                    String timeOfDay = weatherData.isDaytime() ? "day" : "night";
                    setWeatherBackground(vbox, weatherCondition, timeOfDay);

                } catch (Exception ex) {
                    weatherInfoLabel.setText("Error: " + ex.getMessage());
                }
            } else {
                weatherInfoLabel.setText("Please enter a city name.");
            }
        });

        // Theme Toggle
        themeToggleButton.setOnAction(e -> {
            if (themeToggleButton.isSelected()) {
                vbox.getStyleClass().add("dark-theme");
                vbox.getStyleClass().remove("light-theme");
                themeToggleButton.setText("Light Mode");
            } else {
                vbox.getStyleClass().add("light-theme");
                vbox.getStyleClass().remove("dark-theme");
                themeToggleButton.setText("Dark Mode");
            }
        });

        // Default Theme
        vbox.getStyleClass().add("light-theme");

        // Scene Setup
        Scene scene = new Scene(vbox, 400, 350);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());

        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true); // Allow resizing

        // Enable Full Screen
        primaryStage.setFullScreenExitHint("Press Esc to exit full screen.");
        primaryStage.setFullScreen(true);

        // Handle Full Screen Resizing
        primaryStage.fullScreenProperty().addListener((obs, wasFullScreen, isFullScreen) -> {
            if (isFullScreen) {
                cityTextField.setMaxWidth(Double.MAX_VALUE); // Stretch the TextField in full screen
                vbox.setSpacing(20); // Increase spacing for better visibility
            } else {
                cityTextField.setMaxWidth(400); // Reset width when exiting full screen
                vbox.setSpacing(10); // Reset spacing
            }
        });

        primaryStage.show();
    }

    private void setWeatherBackground(VBox vbox, String weatherCondition, String timeOfDay) {
        // Example logic to change background based on weather condition and time of day
        String backgroundClass = "weather-default";

        if ("clear".equals(weatherCondition)) {
            backgroundClass = timeOfDay.equals("day") ? "weather-clear" : "weather-clear";
        } else if ("rain".equals(weatherCondition)) {
            backgroundClass = "weather-rain";
        } else if ("snow".equals(weatherCondition)) {
            backgroundClass = "weather-snow";
        } else if ("clouds".equals(weatherCondition)) {
            backgroundClass = "weather-clouds";
        }

        vbox.getStyleClass().removeAll("weather-clear", "weather-rain", "weather-snow", "weather-clouds", "weather-default");
        vbox.getStyleClass().add(backgroundClass);
    }
}
