package com.mycompany.weatherapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import weather.api.WeatherService;
import weather.data.WeatherData;

/**
 * JavaFX application to display weather information
 */
public class WeatherApp1 extends Application {

    private WeatherService weatherService = new WeatherService();

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

        // Set up event handler for the button click
        getWeatherButton.setOnAction(e -> {
            String city = cityTextField.getText();
            if (!city.isEmpty()) {
                try {
                    WeatherData weatherData = weatherService.getWeather(city);
                    weatherInfoLabel.setText(weatherData.toString());
                } catch (Exception ex) {
                    weatherInfoLabel.setText("Error: " + ex.getMessage());
                }
            } else {
                weatherInfoLabel.setText("Please enter a city name.");
            }
        });

        // Layout to arrange the components vertically
        VBox vbox = new VBox(10, cityLabel, cityTextField, getWeatherButton, weatherInfoLabel);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Scene and Stage setup
        Scene scene = new Scene(vbox, 350, 250);
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
