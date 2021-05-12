package command;

import utility.Console;
import utility.Invoker;
import utility.CommandReader;
import utility.WorkerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Execute script command
 * Read and execute script from matched file
 */
public class ExecuteScriptCommand extends CommandAbstract {
    private final Invoker invoker;
    private final WorkerFactory workerFactory;

    /**
     * Command constructor
     *
     * @param invoker - invoker
     */
    public ExecuteScriptCommand(Invoker invoker, WorkerFactory workerFactory) {
        super("execute_script file_name", "Read and execute script from entered file.");
        this.invoker = invoker;
        this.workerFactory = workerFactory;
    }

    @Override
    public void exe(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Argument is required.");
            return;
        }
        if (invoker.getFilePaths().contains(arg)) {
            System.out.println("Recursion has been found. Please correct your script.");
            return;
        }
        invoker.addPath(arg);
        try {
            Scanner scannerForFile = new Scanner(new File(arg));
            Scanner oldScanner = workerFactory.getScanner();
            workerFactory.setScanner(scannerForFile);
            workerFactory.setBoolean(false);
            CommandReader commandReader = new CommandReader(scannerForFile, invoker);
            commandReader.activeMode();
            workerFactory.setScanner(oldScanner);
            workerFactory.setBoolean(true);
            scannerForFile.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found.");
        }
        invoker.getFilePaths().remove(arg);
    }
}
