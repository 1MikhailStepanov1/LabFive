package command;

import Data.Worker;
import Utility.Receiver;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CountLessThanStartDateCommand extends CommandAbstract{
    private final Receiver receiver;
    public CountLessThanStartDateCommand(Receiver receiver){
        super("Count_less_than_start_date","Show amount with field \"StartDate\" which is lower than indicated one");
        this.receiver = receiver;
    }
    @Override
    public void exe(String arg){
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");
        ZonedDateTime temporary = ZonedDateTime.parse(arg, formatter);
        for (Worker worker : receiver.GetCollection()){
            if (worker.getStartDate().compareTo(temporary)>0) {
                count++;
            }
        }
        System.out.println("Answer: "+count);
    }
}
