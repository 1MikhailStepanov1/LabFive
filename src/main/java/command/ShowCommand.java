package command;

import Data.Worker;
import Utility.Receiver;
import Utility.WorkerToUser;

import java.util.Collection;

public class ShowCommand extends CommandAbstract{
    private final Receiver receiver;
    private final WorkerToUser workerToUser;
    public ShowCommand(Receiver receiver, WorkerToUser workerToUser){
        super ("Show", "Show all collection`s elements into strings");
        this.receiver = receiver;
        this.workerToUser = workerToUser;
    }
    @Override
    public void exe(String arg){
        Collection<Worker> collection = receiver.GetCollection();
        if (collection.size()==0){
            System.out.println("There is no elements in collection.");
        }
        for (Worker w : collection){
            workerToUser.WorkerToConsole(w);
        }
    }
}
