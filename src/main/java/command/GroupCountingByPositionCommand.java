package command;

import data.Position;
import data.Worker;
import utility.CollectionManager;

import java.util.EnumMap;
import java.util.Map;

/** Group counting by position command
 * Group elements of collection by field position and show amount of elements in each group
 */
public class GroupCountingByPositionCommand extends CommandAbstract {
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     */
    public GroupCountingByPositionCommand(CollectionManager collectionManager) {
        super("Group_counting_by_position", "Group elements by field \"Position\" and show amount of elements in each group");
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        EnumMap<Position, Integer> enumMap = new EnumMap<>(Position.class);
        int s;
        enumMap.put(Position.MANAGER, 0);
        enumMap.put(Position.LABORER, 0);
        enumMap.put(Position.LEAD_DEVELOPER, 0);
        enumMap.put(Position.BAKER, 0);
        enumMap.put(Position.MANAGER_OF_CLEANING, 0);
        for (Worker worker : collectionManager.getCollection()) {
            s = enumMap.get(worker.getPosition());
            s++;
            enumMap.put(worker.getPosition(), s);
        }
        for (Map.Entry<Position, Integer> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
