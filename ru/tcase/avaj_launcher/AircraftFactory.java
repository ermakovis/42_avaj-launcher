package ru.tcase.avaj_launcher;

public class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws IncorrectCoordinateException {
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new IncorrectCoordinateException();
        }

        Flyable flyable;
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        if ("HELICOPTER".equalsIgnoreCase(type)) {
            flyable = new Helicopter(name, coordinates );
        } else if ("JETPLANE".equalsIgnoreCase(type)) {
            flyable = new JetPlane(name, coordinates);
        } else if ("BALOON".equalsIgnoreCase(type)) {
            flyable = new Baloon(name, coordinates);
        } else throw new UnknownAircraftTypeException();
        return flyable;
    }
}
