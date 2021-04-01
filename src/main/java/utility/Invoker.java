package utility;

import command.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class contains map with commands which can be execute
 */
public class Invoker {
    private final WorkerFactory workerFactory;
    private final CollectionManager collectionManager;
    private final WorkerToUser workerToUser;
    private final FileWorker fileWorker;
    private final HashMap<String, CommandInterface> commands;
    private boolean isStopRequested = false;
    private final Object allowedToStop = ExitCommand.class;
    private final HashSet<String> filePaths = new HashSet<>();

    public Invoker(WorkerFactory workerFactory, CollectionManager collectionManager, WorkerToUser workerToUser, FileWorker fileWorker) {
        commands = new HashMap<>();
        this.workerFactory = workerFactory;
        this.collectionManager = collectionManager;
        this.workerToUser = workerToUser;
        this.fileWorker = fileWorker;
        initMap();
    }

    /**
     * Initialize commands map
     */
    public void initMap() {
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager, workerToUser));
        commands.put("add", new AddCommand(workerFactory, collectionManager));
        commands.put("update", new UpdateIdCommand(collectionManager, workerFactory));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(fileWorker, collectionManager));
        commands.put("exit", new ExitCommand(this));
        commands.put("add_if_max", new AddIfMaxCommand(workerFactory, collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager, workerFactory));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, workerFactory));
        commands.put("group_counting_by_position", new GroupCountingByPositionCommand(collectionManager));
        commands.put("count_less_than_start_date", new CountLessThanStartDateCommand(collectionManager));
        commands.put("filter_greater_than_start_date", new FilterGreaterThanStartDateCommand(workerToUser, collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(this));
    }

    public void exe(String name, String arg) {
        if (commands.containsKey(name)) {
            commands.get(name).exe(arg);
        } else {
            System.out.println("It is not a command. Please try again.");
        }
    }

    public boolean isStopRequested() {
        return isStopRequested;
    }

    /**
     * Request stop of the program
     *
     * @param requester - is true, when program stops
     */
    public void requestExit(Object requester) {
        if (requester.getClass().equals(allowedToStop)) {
            isStopRequested = true;
        }
    }


    /**
     * Add file path, which was used in execute_script to protect program  from recursion
     *
     * @param path - path, which was used in execute_script
     */
    public void addPath(String path) {
        filePaths.add(path);
    }

    /**
     * @return collection with all file paths, which was executed
     */
    public HashSet<String> getFilePaths() {
        return filePaths;
    }
}
