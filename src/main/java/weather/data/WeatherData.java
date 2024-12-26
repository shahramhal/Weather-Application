package weather.data;

/**
 * Class representing the weather data
 */
public class WeatherData {
    private String cityName;
    private String description;
    private double temperature;
    private int humidity;
    
    public WeatherData(String cName, String desc, double temp, int humidity) {
        this.cityName = cName;
        this.description = desc;
        this.temperature = temp;
        this.humidity = humidity;
    }
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cName) {
        this.cityName = cName;
    }

    public String getWeatherDesc() {
        return description;
    }

    public void setWeatherDesc(String desc) {
        this.description = desc;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "City: " + cityName +
               "\nDescription: " + description +
               "\nTemperature: " + temperature + "°C" +
               "\nHumidity: " + humidity + "%";
    }
}
