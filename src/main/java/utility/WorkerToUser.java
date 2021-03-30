package utility;

import data.Worker;

import java.time.format.DateTimeFormatter;

/**
 * This class is used for describing the Worker class instance
 */
public class WorkerToUser {
    /**
     * Describe worker in console
     * @param worker - worker class instance to be described
     */
    public void WorkerToConsole(Worker worker) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");
        System.out.println();
        System.out.println("Worker");
        System.out.println("Id: " + worker.getId());
        System.out.println("Name: " + worker.getName());
        System.out.println("Coordinates: X-" + worker.getCoordinates().getX() + " Y-" + worker.getCoordinates().getY());
        System.out.println("Creation Date: " + worker.getCreationDate().format(formatter));
        System.out.println("Salary: " + worker.getSalary());
        System.out.println("Start Date: " + worker.getStartDate().format(formatter));
        System.out.println("End Date: " + worker.getEndDate().format(formatter));
        System.out.println("Position: " + worker.getPosition());
        System.out.println("Person: Height-" + worker.getPerson().getHeight() + " Weight-" + worker.getPerson().getWeight());
    }
}
