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
        WorkerFactory workerFactory = new WorkerFactory(1L, console);
        WorkerToUser workerToUser = new WorkerToUser();
        Invoker invoker = new Invoker(workerFactory, collectionManager, workerToUser, fileWorker);
        CommandReader commandReader = new CommandReader(scanner, invoker);
        LinkedList<Worker> workers = new LinkedList<>();
        try {
            workers = fileWorker.parse();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found.");
        } catch (FactoryConfigurationError | ParserConfigurationException | IOException | SAXException exception) {
            System.out.println("Something went wrong. Please correct file and try again.");
        }
        collectionManager.load(workers);
        System.out.println("Collection was loaded successfully.");
        workerFactory.setStartId(collectionManager.getMaxId());
        commandReader.ActiveMode();
    }
}