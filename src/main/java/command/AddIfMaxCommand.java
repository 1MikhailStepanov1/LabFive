package command;

import Data.Worker;
import Exceptions.IncorrectValueException;
import Exceptions.NullFieldException;
import Utility.Console;
import Utility.Receiver;
import Utility.WorkerFactory;

public class AddIfMaxCommand extends CommandAbstract{
    private final WorkerFactory factory;
    private final Receiver receiver;
    public AddIfMaxCommand(WorkerFactory factory, Receiver receiver) {
        super("Add_if_max", "Add new element to the collection, if new element`s value is bigger than element`s maximum in collection");
        this.factory = factory;
        this.receiver = receiver;
    }
    @Override
    public void exe(String arg){
        try {
            Worker worker = factory.GetWorkerFromConsole();
            if (worker.compareTo(receiver.GetMax())<0){
                receiver.add(worker);
                System.out.println("Worker has been added.");
            }else {
                System.out.println("Entered worker isn't greater than max from collection.");
            }
        }catch (IncorrectValueException| NullFieldException exception){
            System.out.println(exception.getMessage());
            return;
        }
    }
}
