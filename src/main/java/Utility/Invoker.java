package Utility;

import command.*;

import java.util.HashMap;

public class Invoker {
    private WorkerFactory workerFactory;
    private Receiver receiver;
    private WorkerToUser wtu;
    private HashMap<String, CommandInterface> commands = new HashMap<>();
    private boolean isStopRequested = false;
    private final Class[] allowedToStop = {ExitCommand.class};
    public Invoker(WorkerFactory wF, Receiver rec, WorkerToUser WTU) {
        commands = new HashMap<>();
        workerFactory = wF;
        receiver = rec;
        wtu = WTU;
        initMap();
    }
    public void initMap(){
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(receiver));
        commands.put("show", new ShowCommand(receiver, wtu));
        commands.put("add", new AddCommand(workerFactory, receiver));
        commands.put("update_id", new UpdateIdCommand(receiver, workerFactory));
        commands.put("remove_by_id", new RemoveByIdCommand(receiver));
        commands.put("clear", new ClearCommand(receiver));
        commands.put("save", new SaveCommand());
        commands.put("exit", new ExitCommand(this));
        commands.put("add_if_max", new AddIfMaxCommand(workerFactory, receiver));
        commands.put("remove_greater", new RemoveGreaterCommand(receiver, workerFactory));
        commands.put("remove_lower", new RemoveLowerCommand(receiver, workerFactory));
        commands.put("group_counting_by_position", new GroupCountingByPositionCommand(receiver));
        commands.put("count_less_than_start_date", new CountLessThanStartDateCommand(receiver));
        commands.put("filter_greater_than_start_date", new FilterGreaterThanStartDateCommand(wtu, receiver));
    }
    public void exe(String name, String arg) throws NullPointerException{
        commands.get(name).exe(arg);
    }
    public boolean isStopRequested(){
        return isStopRequested;
    }
    public void RequestExit(Object requester){
        for (Class c : allowedToStop){
            if (c.equals(requester.getClass())){
                isStopRequested=true;
                break;
            }
        }
    }
}
