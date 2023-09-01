import com.example.programacion4proyectofinal.Model.Person.Passenger;
import com.example.programacion4proyectofinal.Model.PRIORITY;
import com.example.programacion4proyectofinal.Model.PriorityQueueManager;
import com.example.programacion4proyectofinal.Utils.PriorityQueueUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class QueueTest {
    @Test
    public void addElementTest(){
        Comparator comparator = PriorityQueueUtils.returnComparator();
        PriorityQueueManager<Passenger> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 257, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 258, 10, PRIORITY.VIP));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 259, 10, PRIORITY.REGULAR));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 251, 10, PRIORITY.REGULAR));
        Assertions.assertEquals(priorityQueue.getList().size(),5);
    }
    @Test
    public void pollElementTest(){
        Comparator comparator = PriorityQueueUtils.returnComparator();
        PriorityQueueManager<Passenger> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.VIP));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.REGULAR));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.poll();
        Assertions.assertEquals(priorityQueue.getList().size(),4);
    }
    @Test
    public void peekElementTest(){
        Comparator comparator = PriorityQueueUtils.returnComparator();
        PriorityQueueManager<Passenger> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.VIP));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.peek();
        Assertions.assertEquals(priorityQueue.getList().size(),5);
    }
    @Test
    public void arriveTest(){
        Comparator comparator = PriorityQueueUtils.returnComparator();
        Passenger expectedClient = new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT);
        PriorityQueueManager<Passenger> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.VIP));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.REGULAR));
        Assertions.assertEquals(priorityQueue.getList().get(0).getId(),expectedClient.getId());
    }
    @Test
    public void flightCanceledTest(){
        Comparator comparator = PriorityQueueUtils.returnComparator();
        Passenger expectedClient = new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT);
        PriorityQueueManager<Passenger> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 259, 10, PRIORITY.VIP));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 253, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 252, 10, PRIORITY.REGULAR));
        priorityQueue.addElement(expectedClient);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 251, 10, PRIORITY.FREQUENT));
        priorityQueue.cancelFlight(expectedClient);
        Assertions.assertEquals(priorityQueue.getList().size(),4);
    }
    @Test
    public void searchCanceledFlightTest(){
        Comparator comparator = PriorityQueueUtils.returnComparator();
        Passenger expectedClient = new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT);
        PriorityQueueManager<Passenger> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(expectedClient);
        priorityQueue.addElement(new Passenger("Carlos", "Rodriguez", 254, 10, PRIORITY.FREQUENT));
        priorityQueue.cancelFlight(expectedClient);
        Assertions.assertEquals(false,priorityQueue.verifyFlight(expectedClient));
    }

}
