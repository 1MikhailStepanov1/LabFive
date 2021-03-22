import java.util.Scanner;

import Utility.*;

public class Main {
    public static void main (String[] args){
        Receiver receiver = new Receiver();
        Scanner scannerToFile = new Scanner("");
        FileScanner fileScanner = new FileScanner(receiver, scannerToFile);
        Scanner scannerToCommands = new Scanner(System.in);
        Console console = new Console(scannerToCommands);
        WorkerFactory workerFactory = new WorkerFactory(Long.valueOf(1), console);
        WorkerToUser workerToUser = new WorkerToUser();
        Invoker invoker = new Invoker(workerFactory, receiver, workerToUser);
        CommandReader commandReader = new CommandReader(scannerToCommands,invoker);
        receiver.load(fileScanner.parse());
        commandReader.ActiveMode();
    }

}
