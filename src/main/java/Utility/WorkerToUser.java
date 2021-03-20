package Utility;

import Data.Worker;

import java.lang.reflect.Field;
//TODO Сделать через рефлексию
public class WorkerToUser {
    public void WorkerToConsole(Worker worker){
        System.out.println("");
        System.out.println("Worker");
        System.out.println("Id: " + worker.getId());
        System.out.println("Name: " + worker.getName());
        System.out.println("Coordinates: X-" + worker.getCoordinates().getX() + " Y-" + worker.getCoordinates().getY());
        System.out.println("Creation Date: " + worker.getCreationDate());
        System.out.println("Salary: " + worker.getSalary());
        System.out.println("Start Date: " + worker.getStartDate());
        System.out.println("End Date: " + worker.getEndDate());
        System.out.println("Position: " + worker.getPosition());
        System.out.println("Person: Height-" + worker.getPerson().getHeight() + " Weight-" + worker.getPerson().getWeight());
    }
}
