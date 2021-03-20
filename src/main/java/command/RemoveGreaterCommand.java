package command;

import Data.Worker;
import Exceptions.IncorrectValueException;
import Exceptions.NullFieldException;
import Utility.Receiver;
import Utility.WorkerFactory;

public class RemoveGreaterCommand extends CommandAbstract{
    private final Receiver receiver;
    private final WorkerFactory workerFactory;
    public RemoveGreaterCommand(Receiver receiver, WorkerFactory workerFactory){
        super("Remove_greater {element}", "Delete all elements from collection which are bigger than indicated one");
        this.receiver = receiver;
        this.workerFactory = workerFactory;
    }
    @Override
    public void exe(String arg){
        try {
            Worker worker = workerFactory.GetWorkerFromConsole();
            for (Worker w : receiver.GetCollection()){
                if (worker.compareTo(w)>0){
                    receiver.remove(w);
                }
            }
            System.out.println("All greater elements have been removed.");
        } catch (NullFieldException | IncorrectValueException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
