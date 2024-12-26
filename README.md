Weather App
A simple Java-based weather application that uses the OpenWeatherMap API to fetch and display real-time weather information for a specified city.

Features
User Interface: A JavaFX GUI for entering the city name and displaying weather details.
Weather Information: Displays the temperature, humidity, and weather description for the entered city.
Configurable API Key: Securely fetches the API key from a configuration file to avoid hardcoding sensitive information.
Prerequisites
Java Development Kit (JDK): Version 8 or higher.
NetBeans IDE: (Optional, but used for this project).
OpenWeatherMap API Key: Create an account and generate your API key from OpenWeatherMap.
Project Structure:
src/main/java: Contains all source code.
src/main/resources: Contains the config.properties file with the API key.
Installation and Setup
1. Clone the Repository

git clone https://github.com/your-repository/weather-app.git
cd weather-app
2. Add Your API Key
Create a file named config.properties in the src/main/resources directory.
Add the following line to the file:
properties

WEATHER_API_KEY=your_actual_api_key
3. Compile and Run
Using NetBeans or your preferred IDE:

Open the project.
Build the application.
Run the WeatherApp1 class to launch the GUI.

How It Works
The user enters a city name into the GUI.
The WeatherService class calls the OpenWeatherMap API to fetch weather data.
The response is parsed into a WeatherData object.
Weather details are displayed in the JavaFX application.
Key Components
1. WeatherApp1 (JavaFX Application)
Provides the graphical interface for user interaction.
Displays weather details fetched by the WeatherService.
2. ConfigReader
Reads the API key from config.properties.
Ensures secure handling of sensitive information.
3. WeatherService
Handles the HTTP request to OpenWeatherMap API.
Parses JSON responses into the WeatherData model.
4. WeatherData
Data class for storing and formatting weather information.

Example Output
Input: London
Output:

City: London
Description: scattered clouds
Temperature: 15.0°C
Humidity: 75%
Dependencies
JavaFX: For building the graphical user interface.
org.json: For parsing JSON responses (ensure it’s included in the project library).
Future Enhancements
Add a feature to display a 5-day weather forecast.
Improve error handling for invalid city names or network issues.
Allow users to switch between metric and imperial units.
License
This project is licensed under the MIT License. See the LICENSE file for more details.
