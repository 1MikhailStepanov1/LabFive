package command;

import Exceptions.IncorrectValueException;
import Exceptions.NullFieldException;
import Utility.Receiver;
import Utility.WorkerFactory;


public class UpdateIdCommand extends CommandAbstract{
    private final Receiver receiver;
    private final WorkerFactory workerFactory;
    public UpdateIdCommand(Receiver receiver, WorkerFactory workerFactory){
        super("Update id {element}", "Update element with indicated id");
        this.receiver = receiver;
        this.workerFactory =workerFactory;
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
        }catch (NullPointerException exception){
            System.out.println("Worker with detected id wasn't found.");
            return;
        }
        long temp = workerFactory.getId();
        workerFactory.setStartId(id-1);
        try {
            receiver.add(workerFactory.GetWorkerFromConsole());
        } catch (IncorrectValueException | NullFieldException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        workerFactory.setStartId(temp);

    }
}
