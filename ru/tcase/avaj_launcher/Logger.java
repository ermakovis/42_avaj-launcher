package ru.tcase.avaj_launcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {
    private static final Path path = Paths.get("simulation.txt");

    private Logger() {}

    public static void log(String string) {
        try {
            Files.writeString(path, string,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (Exception ignored) {}
    }
}
