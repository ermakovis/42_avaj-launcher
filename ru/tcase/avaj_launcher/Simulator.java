package ru.tcase.avaj_launcher;

import javax.naming.directory.InvalidAttributesException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulator {
    public static void main(String[] args) {
        try {
            if (args.length != 1) throw new ArrayIndexOutOfBoundsException();
            Scanner scanner = new Scanner(new File(args[0]));
            int simulationsCount = Integer.parseInt(scanner.nextLine());
            if (simulationsCount < 0) throw new IncorrectSimulationCountException();

            AircraftFactory aircraftFactory = new AircraftFactory();
            WeatherTower weatherTower = new WeatherTower();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.matches("\\s*")) continue;
                String[] words = line.split("\\s+");
                if (words.length != 5) throw new InvalidAttributesException();
                Flyable flyable = aircraftFactory.newAircraft(words[0], words[1],
                        Integer.parseInt(words[2]), Integer.parseInt(words[3]), Integer.parseInt(words[4]));
                flyable.registerTower(weatherTower);
            }

            while (simulationsCount-- > 0) weatherTower.changeWeather();
        } catch (FileNotFoundException ignored) {
            System.out.println("Failed to access file");
        } catch (NumberFormatException ignored) {
            System.out.println("Incorrect number format");
        } catch (IncorrectCoordinateException ignored) {
            System.out.println("Incorrect coordinate");
        } catch (IncorrectSimulationCountException ignored) {
            System.out.println("Incorrect simulation count exception");
        } catch (UnknownAircraftTypeException ignored) {
            System.out.println("Unknown aircraft type");
        } catch (InvalidAttributesException ignored) {
            System.out.println("Incorrect attributes count exception");
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.println("Not enough arguments");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
