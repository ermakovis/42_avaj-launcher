package ru.tcase.avaj_launcher;

public abstract class Aircraft {
    private static long idCounter = 0;
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    public Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private long nextId() {
        return ++idCounter;
    }
}
