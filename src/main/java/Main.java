import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import data.Worker;
import utility.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        CollectionManager collectionManager = new CollectionManager(console);
        FileWorker fileWorker = new FileWorker(collectionManager);
        WorkerFactory workerFactory = new WorkerFactory(1L);
        workerFactory.setBoolean(true);
        WorkerToUser workerToUser = new WorkerToUser();
        Invoker invoker = new Invoker(workerFactory, collectionManager, workerToUser, fileWorker);
        CommandReader commandReader = new CommandReader(scanner, invoker);
        LinkedList<Worker> workers = new LinkedList<>();
        workerFactory.setScanner(scanner);
        boolean temp = true;
        while (temp) {
            try {
                workers = fileWorker.parse(args[0]);
                temp = false;
            } catch (FileNotFoundException exception) {
                System.out.println("File not found.");
                temp = true;
            } catch (FactoryConfigurationError | ParserConfigurationException | IOException | SAXException exception) {
                System.out.println("Something went wrong. Please correct file and try again.");
                temp = true;
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("You should enter file's path as command line argument.");
                break;
            }
        }
        collectionManager.load(workers);
        workerFactory.setStartId(collectionManager.getMaxId());
        commandReader.activeMode();
}
}