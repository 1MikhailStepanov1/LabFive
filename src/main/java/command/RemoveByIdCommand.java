package command;

import Exceptions.IncorrectValueException;
import Exceptions.NullFieldException;
import Utility.Console;
import Utility.Receiver;
import Utility.WorkerFactory;

public class RemoveByIdCommand extends CommandAbstract{
    private final Receiver receiver;
    public RemoveByIdCommand(Receiver receiver){
        super("Remove by id {id}", "Delete element with indicated id");
        this.receiver = receiver;
    }
    @Override
    public void exe(String arg){
        if (arg.length()==0){
            System.out.println("Argument is needed.");
            return;
        }
        long id;
        try {
            id = Long.parseLong(arg);
        }catch (NumberFormatException exception){
            System.out.println("Input isn't id.");
            return;
        }
        try {
            receiver.remove(receiver.getById(id));
            System.out.println("Element has been removed.");
        }catch (NullPointerException exception){
            System.out.println("Worker with detected id wasn't found.");
        }
    }
}
