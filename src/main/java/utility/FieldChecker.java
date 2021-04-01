package utility;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FieldChecker {
    private final Console console;
    private Long tempLong = null;
    private Integer tempInt = null;
    private Double tempDouble = null;
    private ZonedDateTime tempZonedDateTime = null;

    public FieldChecker(Console console) {
        this.console = console;
    }

    public String readAndCheckString() {
        String tempString = null;
        while (tempString == null) {
            System.out.println("Enter worker`s name:");
            tempString = console.readln();
            if (tempString == null) {
                System.out.println("Input doesn't contain \"Name\". Please try again.");
            }
        }
        return tempString;
    }

    public Long readAndCheckLong(String name) {
        while (tempLong == null) {
            System.out.println("Enter " + name + ":");
            try {
                tempLong = Long.parseLong(console.readln());
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain " + name + ". Please try again.");
                tempLong = null;
            }
            if (name.equals("coordinate X")) {
                if (tempLong != null && tempLong > 768) {
                    System.out.println("Coordinate X should be less or equal 768.");
                    tempLong = null;
                }
            } else {
                if (tempLong != null && tempLong <= 0) {
                    System.out.println("Height should be more than 0.");
                    tempLong = null;
                }
            }
        }
        return tempLong;
    }

    public Integer readAndCheckInt(String name) {
        while (tempInt == null) {
            System.out.println("Enter " + name + ":");
            try {
                tempInt = Integer.parseInt(console.readln());
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain " + name + ". Please try again.");
                tempInt = null;
            }
            if (name.equals("weight")) {
                if (tempInt != null && tempInt <= 0) {
                    System.out.println("Weight should be more than 0.");
                    tempInt = null;
                }
            }
        }
        return tempInt;
    }

    public Double readAndCheckDouble(String name) {
        while (tempDouble == null) {
            System.out.println("Enter "+name+":");
            try {
                tempDouble = Double.parseDouble(console.readln());
                if (tempLong <= 0) {
                    System.out.println(name+" should be more than 0.");
                    tempLong = null;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain "+name+". Please try again.");
                tempDouble = null;
            } catch (NullPointerException exception) {
                System.out.println("Input is null. Please try again.");
                tempDouble = null;
            }
        }
        return tempDouble;
    }

    public ZonedDateTime readAndCheckZDT(String name) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");
        while (tempZonedDateTime == null) {
            System.out.println("Enter " + name + "date:");
            try {
                tempZonedDateTime = ZonedDateTime.parse(console.readln(), formatter);
            } catch (NullPointerException exception) {
                System.out.println("Input doesn't contain date. Please try again.");
                tempZonedDateTime = null;
            } catch (DateTimeParseException exception) {
                System.out.println("Format of input is incorrect. Use dd.mm.yyyy hh:mm:ss +/-hh:mm");
                tempZonedDateTime = null;
            }
        }
        return tempZonedDateTime;
    }
}

