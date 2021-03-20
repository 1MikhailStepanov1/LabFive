package command;

import Data.Worker;
import Utility.Receiver;
import Utility.WorkerToUser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FilterGreaterThanStartDateCommand extends CommandAbstract{
    private final WorkerToUser wtu;
    private final Receiver receiver;
    public FilterGreaterThanStartDateCommand(WorkerToUser wtu, Receiver receiver){
        super("Filter_greater_than_start_date","Show elements with value of field \"StartDate\", which is bigger than indicated one");
        this.wtu = wtu;
        this.receiver = receiver;
    }
    @Override
    public void exe(String arg){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");
        ZonedDateTime temporary = ZonedDateTime.parse(arg, formatter);
        for (Worker worker : receiver.GetCollection()){
            if (worker.getStartDate().compareTo(temporary)<0) {
                wtu.WorkerToConsole(worker);
            }
        }
    }
}
