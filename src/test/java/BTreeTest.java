import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BTreeTest {
    private BTree<Integer> bTree;

    @BeforeEach
    public void setUp() {
        bTree = new BTree<>(3);
    }

    @Test
    public void insertTest() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);
        bTree.insert(60);
        bTree.insert(70);
        bTree.insert(80);

        Node<Integer> value = bTree.search(bTree.getRoot(), 10);

        Assertions.assertNotNull(value);

        bTree.printBTree();
    }

    @Test
    public void removeTest() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);
        bTree.insert(60);
        bTree.insert(70);
        bTree.insert(80);
        System.out.println("Before remove");
        bTree.printBTree();

        System.out.println("After remove");
        bTree.remove(10);
        bTree.printBTree();
    }

    @Test
    public void updateTest() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);
        bTree.insert(60);
        bTree.insert(70);
        bTree.insert(80);
        System.out.println("Before update");
        bTree.printBTree();

        System.out.println("After update");
        bTree.update(30, 1000000);
        bTree.printBTree();
    }

    @Test
    public void printBTreeTest() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);
        bTree.insert(60);
        bTree.insert(70);
        bTree.insert(80);
        System.out.println("Print BTree");
        bTree.printBTree();
    }

    @Test
    public void timeExecutionInsertingOneMillionValues() {
        long startTime = System.nanoTime();
        for (int i = 1; i <= 1000000; i++) {
            bTree.insert(i);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;

        System.out.printf("Time execution (Insertion): %.4f seconds%n", durationInSeconds);
    }

    @Test
    public void timeExecutionRemovingOneMillionValues() {
        BTree<Integer> bTree = new BTree<>(3);
        for (int i = 1; i <= 1000000; i++) {
            bTree.insert(i);
        }
        long startTime = System.nanoTime();
        for (int i = 1; i <= 1000000; i++) {
            bTree.remove(i);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;

        System.out.printf("Time execution (Removing): %.4f seconds%n", durationInSeconds);
    }
}
