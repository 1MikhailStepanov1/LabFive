package utility;

import data.Coordinates;
import data.Person;
import data.Position;
import data.Worker;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


/**
 * This class is for creating new instances of Worker class
 */
public class WorkerFactory {
    private final Console console;
    private Long id;

    public Long getId() {
        return id;
    }

    /**
     * @param startId - start point for id counter
     * @param console - console is used to get worker from input
     */
    public WorkerFactory(Long startId, Console console) {
        this.id = startId;
        this.console = console;
    }

    /**
     * Creates new worker with new id and creationDate
     * @param name - worker's name
     * @param coordinates - worker's coordinates
     * @param salary - worker's salary
     * @param startDate - worker's startDate
     * @param endDate - worker's endDate
     * @param position - worker's position
     * @param person - worker's height and weight
     * @return woker instance
     * @throws NullFieldException if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Worker createWorker(String name, Coordinates coordinates, double salary, ZonedDateTime startDate, ZonedDateTime endDate, Position position, Person person) throws NullFieldException, IncorrectValueException {
        return createWorkerWithIdAndCreationDate(++id, name, coordinates, new Date(), salary, startDate, endDate, position, person);
    }

    /**
     * Create worker with given id and creationDate
     * @param _id worker's id
     * @param name - worker's name
     * @param coordinates - worker's coordinates
     * @param excreationDate - worker's creationDate
     * @param salary - worker's salary
     * @param startDate - worker's startDate
     * @param endDate - worker's endDate
     * @param position - worker's position
     * @param person - worker's height and weight
     * @return worker instance
     * @throws NullFieldException if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Worker createWorkerWithIdAndCreationDate(Long _id, String name, Coordinates coordinates, Date excreationDate, double salary, ZonedDateTime startDate, ZonedDateTime endDate, Position position, Person person) throws NullFieldException, IncorrectValueException {
        if (name == null || name.length() == 0) {
            throw new NullFieldException("Name");
        }
        if (coordinates == null) {
            throw new NullFieldException("Coordinates");
        }

        if (salary <= 0) {
            throw new IncorrectValueException("Salary", "This field should be more than 0.");
        }
        if (startDate == null) {
            throw new NullFieldException("StartDate");
        }
        if (endDate == null) {
            throw new NullFieldException("EndDate");
        }
        if (position == null) {
            throw new NullFieldException("Position");
        }
        if (person == null) {
            throw new NullFieldException("Person");
        }

        Instant instant = excreationDate.toInstant();
        ZonedDateTime creationDate = instant.atZone(ZoneId.systemDefault());

        return new Worker(_id, name, coordinates, creationDate, salary, startDate, endDate, position, person);
    }

    /**
     * read worker from console
     * @return worker instance
     * @throws NullFieldException if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Worker getWorkerFromConsole() throws NullFieldException, IncorrectValueException {
        String name = null;
        Long x = null;
        Integer y = null;
        Double salary = null;
        ZonedDateTime startDate = null;
        ZonedDateTime endDate = null;
        Position position = null;
        Long height = null;
        Integer weight = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");

        while (name == null) {
            System.out.println("Enter worker`s name:");
            name = console.readln();
            if (name == null) {
                System.out.println("Input doesn't contain \"Name\". Please try again.");
            }
        }
        while (x == null) {
            System.out.println("Enter coordinate X:");
            try {
                x = Long.parseLong(console.readln());
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain coordinate. Please try again.");
                x = null;
            }
        }
        while (y == null) {
            System.out.println("Enter coordinate Y:");
            try {
                y = Integer.parseInt(console.readln());
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain coordinate. Please try again.");
                y = null;
            }
        }
        while (salary == null) {
            System.out.println("Enter salary:");
            try {
                salary = Double.parseDouble(console.readln());
                if (salary <= 0) {
                    System.out.println("Salary should be more than 0.");
                    salary = null;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain salary. Please try again.");
                salary = null;
            } catch (NullPointerException exception) {
                System.out.println("Input is null. Please try again.");
                salary = null;
            }
        }
        while (startDate == null) {
            System.out.println("Enter start date:");
            try {
                startDate = ZonedDateTime.parse(console.readln(), formatter);
            } catch (NullPointerException exception) {
                System.out.println("Input doesn't contain date. Please try again.");
                startDate = null;
            } catch (DateTimeParseException exception) {
                System.out.println("Format of input is incorrect. Use dd.mm.yyyy hh:mm:ss +/-hh:mm");
            }
        }
        while (endDate == null) {
            System.out.println("Enter end date:");
            try {
                endDate = ZonedDateTime.parse(console.readln(), formatter);
            } catch (NullPointerException exception) {
                System.out.println("Input doesn't contain date. Please try again.");
                endDate = null;
            } catch (DateTimeParseException exception) {
                System.out.println("Format of input is incorrect. Use dd.mm.yyyy hh:mm:ss +/-hh:mm");
            }
        }
        while (position == null) {
            System.out.println("Choose one position from the list:");
            for (Position pos : Position.values()) {
                System.out.println(pos.toString());
            }
            try {
                position = Position.valueOf(console.readln().toUpperCase());
            } catch (IllegalArgumentException exception) {
                System.out.println("Input doesn't contain any of allowed positions. Please try again.");
                position = null;
            } catch (NullPointerException exception) {
                System.out.println("Input doesn't contain any of allowed positions. Please try again.");
                position = null;
            }
        }
        while (height == null) {
            System.out.println("Enter person's height:");
            try {
                height = Long.parseLong(console.readln());
            } catch (NullPointerException exception) {
                System.out.println("Input doesn't contain height. Please try again.");
                height = null;
            }
            if (height != null && height <= 0) {
                System.out.println("Height should be more than 0.");
                height = null;
            }
        }
        while (weight == null) {
            System.out.println("Enter person's weight:");
            try {
                weight = Integer.parseInt(console.readln());
            } catch (NullPointerException exception) {
                System.out.println("Input doesn't contain weight. Please try again.");
                weight = null;
            }
            if (weight != null && weight <= 0) {
                System.out.println("Weight should be more than 0.");
                weight = null;
            }
        }
        return createWorker(name, new Coordinates(x, y), salary, startDate, endDate, position, new Person(height, weight));
    }

    /**
     * Set new start point for id counter
     * @param id1 - indicated start point for id counter
     */
    public void setStartId(long id1) {
        id = id1;
    }
}
