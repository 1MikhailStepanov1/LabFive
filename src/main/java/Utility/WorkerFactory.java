package Utility;

import Data.Coordinates;
import Data.Person;
import Data.Position;
import Data.Worker;
import Exceptions.IncorrectValueException;
import Exceptions.NullFieldException;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


public class WorkerFactory {
    private Console console;
    private Long id;

    public Long getId() {
        return id;
    }

    public WorkerFactory (Long StartId, Console console){
        this.id = StartId;
    }

    public Worker CreateWorker(String name, Coordinates coordinates, double salary, ZonedDateTime startDate, ZonedDateTime endDate, Position position, Person person) throws NullFieldException, IncorrectValueException{
        return CreateWorkerWithIdAndCreationDate(++id, name, coordinates, new Date(), salary, startDate, endDate, position, person);
    }

    public Worker CreateWorkerWithIdAndCreationDate(Long _id, String name, Coordinates coordinates, Date excreationDate, double salary, ZonedDateTime startDate, ZonedDateTime endDate, Position position, Person person) throws NullFieldException, IncorrectValueException{
        if (name == null||name.length() == 0) {
            throw new NullFieldException("Name");
        }
        if (coordinates==null){
            throw new NullFieldException("Coordinates");
        }

        if (salary<=0){
            throw new IncorrectValueException("Salary", "This field should be more than 0.");
        }
        if (startDate==null) {
            throw new NullFieldException("StartDate");
        }
        if (endDate==null) {
            throw new NullFieldException("EndDate");
        }
        if (position==null) {
            throw new NullFieldException("Position");
        }
        if (person==null){
            throw new NullFieldException("Person");
        }

        Instant instant = excreationDate.toInstant();
        ZonedDateTime creationDate = instant.atZone(ZoneId.systemDefault());

        return new Worker(_id, name, coordinates, creationDate, salary, startDate, endDate, position, person);
    }

    public Worker GetWorkerFromConsole() throws NullFieldException, IncorrectValueException{
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

        while (name==null){
            System.out.println("Enter worker`s name:");
            name=console.readln();
            if (name==null){
                System.out.println("Input doesn't contain \"Name\". Please try again.");
            }
        }
        while (x==null){
            System.out.println("Enter coordinate X:");
            try{
                x=Long.parseLong(console.readln());
            } catch (NumberFormatException exception){
                System.out.println("Input doesn't contain coordinate. Please try again.");
                x=null;
            }
        }
        while (y==null){
            System.out.println("Enter coordinate Y:");
            try{
                y=Integer.parseInt(console.readln());
            } catch (NumberFormatException exception){
                System.out.println("Input doesn't contain coordinate. Please try again.");
                y=null;
            }
        }
        while (salary==null){
            System.out.println("Enter salary:");
            try{
                salary=Double.parseDouble(console.readln());
                if (salary!=null&&salary<=0){
                    System.out.println("Salary should be more than 0.");
                    salary=null;
                }
            }catch (NumberFormatException exception){
                System.out.println("Input doesn't contain salary. Please try again.");
                salary=null;
            }
        }
        while (startDate==null){
            System.out.println("Enter start date:");
            try{
                startDate=ZonedDateTime.parse(console.readln(), formatter);
            }catch (NullPointerException exception){
                System.out.println("Input doesn't contain date. Please try again.");
                startDate=null;
            }catch (DateTimeParseException exception){
                System.out.println("Format of input is incorrect. Use dd.mm.yyyy hh:mm:ss +/-hh:mm");
            }
        }
        while (endDate==null){
            System.out.println("Enter end date:");
            try {
                endDate=ZonedDateTime.parse(console.readln(), formatter);
            }catch (NullPointerException exception){
                System.out.println("Input doesn't contain date. Please try again.");
                endDate=null;
            }catch (DateTimeParseException exception){
                System.out.println("Format of input is incorrect. Use dd.mm.yyyy hh:mm:ss +/-hh:mm");
            }
        }
        while (position==null){
            System.out.println("Choose one position from the list:");
            for (Position pos:Position.values()){
                System.out.println(pos.toString());
            }
            try {
                position = Position.valueOf(console.readln().toUpperCase());
            }catch (IllegalArgumentException exception){
                System.out.println("Input doesn't contain any of allowed positions. Please try again.");
                position=null;
            }catch (NullPointerException exception){
                System.out.println("Input doesn't contain any of allowed positions. Please try again.");
                position=null;
            }
        }
        while (height==null){
            System.out.println("Enter person's height:");
            try{
                height = Long.parseLong(console.readln());
            }catch (NullPointerException exception){
                System.out.println("Input doesn't contain height. Please try again.");
                height=null;
            }
            if (height!=null&&height<=0){
                System.out.println("Height should be more than 0.");
                height=null;
            }
        }
        while (weight==null){
            System.out.println("Enter person's weight:");
            try{
                weight=Integer.parseInt(console.readln());
            }catch (NullPointerException exception){
                System.out.println("Input doesn't contain weight. Please try again.");
                weight=null;
            }
            if (weight!=null&&weight<=0){
                System.out.println("Weight should be more than 0.");
                weight=null;
            }
        }
        return CreateWorker(name, new Coordinates(x,y), salary, startDate, endDate, position, new Person(height, weight));
    }
    public void setStartId(long id1){
        id = id1;
    }
}
