package ru.tcase.avaj_launcher;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
