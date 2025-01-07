package weather.data;

/** Class representing the weather data */
public class WeatherData {
    private String cityName;
    private String description;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private boolean isDaytime;

    // Constructor to initialize all the fields
    public WeatherData(String cName, String desc, double temp, int humidity, double windSpeed, boolean isDaytime) {
        this.cityName = cName;
        this.description = desc;
        this.temperature = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.isDaytime = isDaytime;
    }

    // Getters and setters for the fields
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

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public boolean isDaytime() {
        return isDaytime;
    }

    public void setDaytime(boolean isDaytime) {
        this.isDaytime = isDaytime;
    }

    @Override
    public String toString() {
        return "City: " + cityName +
               "\nDescription: " + description +
               "\nTemperature: " + temperature + "°" +
               "\nHumidity: " + humidity + "%" +
               "\nWind Speed: " + windSpeed + " m/s" +
               "\nDaytime: " + (isDaytime ? "Yes" : "No");
    }
}
