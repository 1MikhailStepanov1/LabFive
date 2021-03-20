import java.util.Scanner;

import Utility.*;

public class Main {
    public static void main (String[] args){
        Receiver receiver = new Receiver();
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        WorkerFactory workerFactory = new WorkerFactory(Long.valueOf(1), console);
        WorkerToUser workerToUser = new WorkerToUser();
        Invoker invoker = new Invoker(workerFactory, receiver, workerToUser);
        CommandReader commandReader = new CommandReader(scanner,invoker);
        commandReader.ActiveMode();
    }

}
