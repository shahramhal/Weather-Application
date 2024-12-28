package weather.api;

import weather.data.WeatherData;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** Service for fetching weather information */
public class WeatherService {
    private String API_KEY;
    private String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherService() {
        this.API_KEY = ConfigReader.getApiKey();
    }

    public WeatherData getWeather(String cName, String units, String lang) throws Exception {
        String urlString = BASE_URL + "?q=" + cName + "&units=" + units + "&lang=" + lang + "&appid=" + API_KEY;
        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new Exception("Error fetching weather: " + conn.getResponseMessage());
        }

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
        double windSpeed = jsonResponse.getJSONObject("wind").getDouble("speed");
        String city = jsonResponse.getString("name");
        

        return new WeatherData(city, description, temp, humidity, windSpeed);
    }
}
