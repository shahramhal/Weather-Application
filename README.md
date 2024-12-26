# Weather App 🌤️
A simple Java-based weather application that fetches real-time weather information using the OpenWeatherMap API.

## Features
📋 **Easy-to-use Interface**: A JavaFX GUI for entering city names.  
🌡️ **Weather Information**: Displays temperature, humidity, and weather description.  
🔒 **Secure API Handling**: Reads the API key from a `config.properties` file in the resources folder.

## Prerequisites
- Java Development Kit (JDK): Version 8 or higher.
- NetBeans IDE (Optional) or any Java-compatible IDE.
- OpenWeatherMap API Key: Get your API key from OpenWeatherMap.

## Setup Instructions
### 1. Clone the Repository
```python
git clone https://github.com/your-repository/weather-app.git
cd weather-app
```
### 2. Configure the API Key
- Navigate to src/main/resources/.
- Create a file named config.properties.
- Add the following line
```properties
WEATHER_API_KEY=your_actual_api_key
```
### 3. Compile and Run
- Use NetBeans or your preferred IDE:

- Open the project.
- Build the application.
- Run the WeatherApp1 class to launch the GUI.
