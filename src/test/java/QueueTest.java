import com.example.programacion4proyectofinal.Model.Client;
import com.example.programacion4proyectofinal.Model.PRIORITY;
import com.example.programacion4proyectofinal.Model.PriorityQueueManager;
import com.example.programacion4proyectofinal.Model.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class QueueTest {
    @Test
    public void addElementTest(){
        Comparator comparator = Utils.returnComparator();
        PriorityQueueManager<Client> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Client("A", "A1", "252", 500, PRIORITY.VIP));
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B2", "B2", "252", 1500, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("C", "B1", "252", 50, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("D", "B1", "252", 10, PRIORITY.REGULAR));
        Assertions.assertEquals(priorityQueue.getList().size(),5);
    }
    @Test
    public void pollElementTest(){
        Comparator comparator = Utils.returnComparator();
        PriorityQueueManager<Client> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Client("A", "A1", "252", 500, PRIORITY.VIP));
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B2", "B2", "252", 1500, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("C", "B1", "252", 50, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("D", "B1", "252", 10, PRIORITY.REGULAR));
        priorityQueue.poll();
        Assertions.assertEquals(priorityQueue.getList().size(),4);
    }
    @Test
    public void peekElementTest(){
        Comparator comparator = Utils.returnComparator();
        PriorityQueueManager<Client> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Client("A", "A1", "252", 500, PRIORITY.VIP));
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B2", "B2", "252", 1500, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("C", "B1", "252", 50, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("D", "B1", "252", 10, PRIORITY.REGULAR));
        priorityQueue.peek();
        Assertions.assertEquals(priorityQueue.getList().size(),5);
    }
    @Test
    public void arriveTest(){
        Comparator comparator = Utils.returnComparator();
        Client expectedClient = new Client("A", "A1", "252", 500, PRIORITY.VIP);
        PriorityQueueManager<Client> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(expectedClient);
        priorityQueue.addElement(new Client("B2", "B2", "252", 1500, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("C", "B1", "252", 50, PRIORITY.REGULAR));
        priorityQueue.addElement(new Client("D", "B1", "252", 10, PRIORITY.REGULAR));
        Assertions.assertEquals(priorityQueue.getList().get(0).getCI(),expectedClient.getCI());
    }
    @Test
    public void flightCanceledTest(){
        Comparator comparator = Utils.returnComparator();
        Client expectedClient = new Client("A", "A1", "252", 500, PRIORITY.VIP);
        PriorityQueueManager<Client> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B2", "B2", "252", 1500, PRIORITY.REGULAR));
        priorityQueue.addElement(expectedClient);
        priorityQueue.addElement(new Client("D", "B1", "252", 10, PRIORITY.REGULAR));
        priorityQueue.cancelFlight(expectedClient);
        Assertions.assertEquals(priorityQueue.getList().size(),4);
    }
    @Test
    public void searchCanceledFlightTest(){
        Comparator comparator = Utils.returnComparator();
        Client expectedClient = new Client("A", "A1", "252", 500, PRIORITY.VIP);
        PriorityQueueManager<Client> priorityQueue = new PriorityQueueManager<>(comparator);
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B", "B1", "252", 10, PRIORITY.FREQUENT));
        priorityQueue.addElement(new Client("B2", "B2", "252", 1500, PRIORITY.REGULAR));
        priorityQueue.addElement(expectedClient);
        priorityQueue.addElement(new Client("D", "B1", "252", 10, PRIORITY.REGULAR));
        priorityQueue.cancelFlight(expectedClient);
        Assertions.assertEquals(false,priorityQueue.verifyFlight(expectedClient));
    }

}
