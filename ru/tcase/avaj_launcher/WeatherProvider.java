package ru.tcase.avaj_launcher;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;

    String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {};

    public static WeatherProvider getWeatherProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(coordinates.getHeight()
                + coordinates.getLatitude()
                + coordinates.getLongitude()) % 4];
    }
}
