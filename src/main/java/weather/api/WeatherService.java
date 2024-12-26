package weather.api;

import weather.data.WeatherData;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * Service for fetching weather information
 */
public class WeatherService {
    private String API_KEY ="da99a1c39deaa1b1e4b8ca62fecbb78a"; // Replace with your actual API key
    private String BASE_URL= "https://api.openweathermap.org/data/2.5/weather";
    
    public WeatherData getWeather(String cName) throws Exception{
        String urlString = BASE_URL + "?q=" + cName + "&units=metric&appid=" + API_KEY;
        URL url = new URL(urlString);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        JSONObject jsonResponse = new JSONObject(response.toString());
        String description = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
        double temp = jsonResponse.getJSONObject("main").getDouble("temp");
        int humidity = jsonResponse.getJSONObject("main").getInt("humidity");
        String city = jsonResponse.getString("name");

        return new WeatherData(city, description, temp, humidity);
    }
}
