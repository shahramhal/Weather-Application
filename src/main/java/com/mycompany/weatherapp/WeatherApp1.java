package com.mycompany.weatherapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import weather.api.WeatherService;
import weather.data.WeatherData;

/**
 * JavaFX application to display weather information
 */
public class WeatherApp1 extends Application {

    private WeatherService weatherService = new WeatherService();
    private String selectedUnit = "metric"; // Default unit
    private String selectedLang = "en"; // Default language

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the UI components
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
        langSelector.setValue("English"); // Default language
        langSelector.setOnAction(e -> {
            String lang = langSelector.getValue();
            switch (lang) {
                case "French" -> selectedLang = "fr";
                case "Spanish" -> selectedLang = "es";
                case "German" -> selectedLang = "de";
                default -> selectedLang = "en";
            }
        });

        // Unit Selector (Metric/Imperial)
        Label unitLabel = new Label("Select Units:");
        ToggleGroup unitToggleGroup = new ToggleGroup();
        RadioButton metricButton = new RadioButton("Metric");
        metricButton.setToggleGroup(unitToggleGroup);
        metricButton.setSelected(true); // Default to metric
        RadioButton imperialButton = new RadioButton("Imperial");
        imperialButton.setToggleGroup(unitToggleGroup);

        unitToggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (metricButton.isSelected()) {
                selectedUnit = "metric";
            } else if (imperialButton.isSelected()) {
                selectedUnit = "imperial";
            }
        });

        // Set up event handler for the button click
        getWeatherButton.setOnAction(e -> {
            progressIndicator.setVisible(true);
            String city = cityTextField.getText();
            if (!city.isEmpty()) {
                try {
                    WeatherData weatherData = weatherService.getWeather(city, selectedUnit, selectedLang);
                    weatherInfoLabel.setText(weatherData.toString());
                   
                   
                } catch (Exception ex) {
                    weatherInfoLabel.setText("Error: " + ex.getMessage());
   
                   
                } finally {
            progressIndicator.setVisible(false);  // Hide the spinner after data is fetched
            }
                
            }else {
                weatherInfoLabel.setText("Please enter a city name.");
           
                progressIndicator.setVisible(false);
            }
        });

        // Layout to arrange the components vertically
        VBox vbox = new VBox(10, cityLabel, cityTextField, langLabel, langSelector, unitLabel, metricButton, imperialButton, getWeatherButton, weatherInfoLabel, progressIndicator,themeToggleButton);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-alignment: center;");
        
        // Toggle Button to switch between dark and light themes
        themeToggleButton.setOnAction(e -> {
            if (themeToggleButton.isSelected()) {
                vbox.getStyleClass().add("dark-theme");
                vbox.getStyleClass().remove("light-theme");
                themeToggleButton.setText("Light Mode"); // Change text to Light Mode when dark mode is selected
            } else {
                vbox.getStyleClass().add("light-theme");
                vbox.getStyleClass().remove("dark-theme");
                themeToggleButton.setText("Dark Mode"); // Change text to Dark Mode when light mode is selected
            }
        });
        // Add more UI elements as needed

        // Set the initial theme to light mode
        vbox.getStyleClass().add("light-theme");


        // Scene and Stage setup
        Scene scene = new Scene(vbox, 400, 350);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());


        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
