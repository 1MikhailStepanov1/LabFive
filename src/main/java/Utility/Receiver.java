package Utility;

import Data.Worker;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Receiver{
    private LinkedList<Worker> collection;
    private boolean ExeDone;
    private ZonedDateTime InitTime;

    public Receiver(){
        collection = new LinkedList<>();
        InitTime = ZonedDateTime.now();
    }
    public Worker GetMax(){
        Worker max;
        try {
            max = collection.getFirst();
        }catch (NoSuchElementException exception){
            return null;
        }
        for (Worker worker : collection){
            if (max.compareTo(worker)>0){
                max=worker;
            }
        }
        return max;
    }
    public boolean add(Worker worker){
        ExeDone=true;
        return collection.add(worker);
    }
    public Worker getById(long id){
        for (Worker worker : collection){
            if (worker.getId()==id){
                return worker;
            }
        }
        return null;
    }
    public boolean remove(Worker worker){
        ExeDone=true;
        return collection.remove(worker);
    }
    public void clear(){
        ExeDone=true;
        collection.clear();
    }
    public boolean ExeDone(){
        return ExeDone;
    }
    public String[] getInfo(){
        String Type = "Type: Collection of worker's type objects";
        String Init = "Initialization time: " + InitTime.toString();
        String Size = "Number of elements: " + collection.size();
        String State;
        if (ExeDone()){
            State = "Collection has been modified.";
        }
        else {
            State = "Collection hasn't been modified yet.";
        }
        return new String[]{Type, Init,Size,State};
    }
    public Collection<Worker> GetCollection(){
        return new LinkedList(collection);
    }
    public void load(Collection<Worker> collectionFromFile){
        collection.addAll(collectionFromFile);
        ExeDone=true;
    }
    public long getMaxId(){
        long maxId = -1;
        for (Worker worker : collection){
            if (worker.getId()>maxId){
                maxId = worker.getId();
            }
        }
        return maxId;
    }
}