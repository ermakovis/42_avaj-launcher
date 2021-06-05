package ru.tcase.avaj_launcher;

import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft implements Flyable {
    private final String logName;
    private final Map<String, String> messageMap = new HashMap<>();
    private WeatherTower weatherTower;

    private static final Map<String, Coordinates> weatherMap = new HashMap<>() {{
        put("SUN", new Coordinates(10, 0, 2));
        put("RAIN", new Coordinates(5, 0, 0));
        put("FOG", new Coordinates(1, 0, 0));
        put("SNOW", new Coordinates(0,0, -12));
    }};

    private void initMessageMap() {
        messageMap.put("SUN", "SUN message");
        messageMap.put("RAIN", "RAIN message");
        messageMap.put("FOG", "FOG message");
        messageMap.put("SNOW", "SNOW message");
        messageMap.put("REGISTER", "REGISTER message");
        messageMap.put("UNREGISTER", "UNREGISTER message");
    }

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        logName = String.format("Helicopter#%s(%d)", name, id);
        initMessageMap();
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        coordinates = coordinates.change(weatherMap.get(weather));
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            Logger.log(String.format("%s: %s | coordinates: {longitude - %d, latitude - %d }%n ",
                    logName, messageMap.get("UNREGISTER"),
                    coordinates.getLongitude(), coordinates.getLatitude()));
        } else {
            Logger.log(String.format("%s: %s | coordinates: longitude - %d, latitude - %d, height - %d%n",
                    logName, messageMap.get(weather),
                    coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight()));
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Logger.log(String.format("%s: %s%n", logName, messageMap.get("REGISTER")));
    }
}
