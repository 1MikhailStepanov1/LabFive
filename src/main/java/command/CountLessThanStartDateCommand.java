package command;

import data.Worker;
import utility.CollectionManager;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Count less than start date command
 * Count and show amount of elements with field StartDAte which is lower than indicated one
 */
public class CountLessThanStartDateCommand extends CommandAbstract {
    private final CollectionManager collectionManager;
    private String temp;

    /**
     * Command constructor
     *
     * @param collectionManager - collection manager, receiver
     */
    public CountLessThanStartDateCommand(CollectionManager collectionManager) {
        super("Count_less_than_start_date", "Show amount of elements with field \"StartDate\" which is lower than indicated one");
        this.collectionManager = collectionManager;
        temp = "";
    }

    @Override
    public void exe(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Argument is required.");
            return;
        }
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");
        try {
            ZonedDateTime temp = ZonedDateTime.parse(arg, formatter);
            long compare = 0;
            for (Worker worker : collectionManager.getCollection()) {
                if (worker.getStartDate().compareTo(temp) < 0) {
                    count++;
                }
            }
            System.out.println("Answer: " + count);
        } catch (DateTimeParseException exception) {
            System.out.println("Incorrect data format. Use dd.MM.uuuu HH:mm:ss +/-zz:zz");
        }

    }
}
