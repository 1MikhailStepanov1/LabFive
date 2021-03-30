package utility;

import data.Worker;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class is used to do all operations with collection
 */
public class CollectionManager {
    private final LinkedList<Worker> collection;
    private boolean ExeDone;
    private final ZonedDateTime InitTime;
    private final Console console;

    public CollectionManager(Console console) {
        collection = new LinkedList<>();
        InitTime = ZonedDateTime.now();
        this.console = console;
    }

    /**
     * @return returns maximal worker
     */
    public Worker getMax() {
        Worker max;
        try {
            max = collection.getFirst();
        } catch (NoSuchElementException exception) {
            return null;
        }
        for (Worker worker : collection) {
            if (max.compareTo(worker) > 0) {
                max = worker;
            }
        }
        return max;
    }

    /**
     * Adds new worker to the collection
     * @param worker worker instance to be add
     */
    public void add(Worker worker) {
        ExeDone = true;
        collection.add(worker);
    }

    /**
     * @param id id of required worker
     * @return worker with required id
     */
    public Worker getById(long id) {
        for (Worker worker : collection) {
            if (worker.getId() == id) {
                return worker;
            }
        }
        return null;
    }

    /**
     * @param worker worker class instance to be removed
     */
    public void remove(Worker worker) {
        ExeDone = true;
        collection.remove(worker);
    }

    /**
     * Remove all elements from collection
     */
    public void clear() {
        ExeDone = true;
        collection.clear();
    }

    /**
     * @return true if collection have unsaved changes
     */
    public boolean exeDone() {
        return ExeDone;
    }

    /**
     * @return string array with information about collection
     */
    public String[] getInfo() {
        String Type = "Type: Collection of worker's type objects";
        String Init = "Initialization time: " + InitTime.toString();
        String Size = "Number of elements: " + collection.size();
        String State;
        if (exeDone()) {
            State = "Collection has been modified.";
        } else {
            State = "Collection hasn't been modified yet.";
        }
        return new String[]{Type, Init, Size, State};
    }

    /**
     * @return copy collection with workers
     */
    public Collection<Worker> getCollection() {
        return new LinkedList(collection);
    }

    /**
     * Load collection from indicated file
     * @param collectionFromFile external collection of worker instances
     */
    public void load(Collection<Worker> collectionFromFile) {
        collection.addAll(collectionFromFile);
        ExeDone = true;
    }

    /**
     * @return maximal id of worker in collection
     */
    public long getMaxId() {
        long maxId = -1;
        for (Worker worker : collection) {
            if (worker.getId() > maxId) {
                maxId = worker.getId();
            }
        }
        return maxId;
    }

    /**
     * @return string which contains file path
     */
    public String getFilePath() {
        System.out.println("Enter path: ");
        return console.readln();
    }
}