package command;

import Data.Position;
import Data.Worker;
import Utility.Receiver;

import java.util.EnumMap;
import java.util.Map;

public class GroupCountingByPositionCommand extends CommandAbstract{
    private final Receiver receiver;
    public GroupCountingByPositionCommand(Receiver receiver){
        super("Group_counting_by_position", "Group elements by field \"Position\" and show amount of elements in each group");
        this.receiver = receiver;
    }
    @Override
    public void exe(String arg){
        EnumMap<Position, Integer> enumMap = new EnumMap<Position, Integer>(Position.class);
        int s = 0;
        enumMap.put(Position.MANAGER, 0);
        enumMap.put(Position.LABORER, 0);
        enumMap.put(Position.LEAD_DEVELOPER, 0);
        enumMap.put(Position.BAKER, 0);
        enumMap.put(Position.MANAGER_OF_CLEANING, 0);
        for (Worker worker : receiver.GetCollection()){
            s = enumMap.get(worker.getPosition());
            s++;
            enumMap.put(worker.getPosition(), Integer.valueOf(s));
        }
        for (Map.Entry<Position,Integer> entry:enumMap.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
