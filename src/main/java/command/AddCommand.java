package command;

import Exceptions.IncorrectValueException;
import Exceptions.NullFieldException;
import Utility.Receiver;
import Utility.WorkerFactory;

public class AddCommand extends CommandAbstract{
    private final WorkerFactory factory;
    private final Receiver receiver;
    public AddCommand(WorkerFactory factory, Receiver receiver){
        super("add {element}", "Add new element to collection.");
        this.factory = factory;
        this.receiver = receiver;
    }
    @Override
    public void exe(String arg){
        try {
            receiver.add(factory.GetWorkerFromConsole());
        }catch (IncorrectValueException| NullFieldException exception){
            System.out.println(exception.getMessage());
        }
    }
}
