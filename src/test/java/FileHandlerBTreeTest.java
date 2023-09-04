import com.example.programacion4proyectofinal.Model.DataStructure.BTree;
import com.example.programacion4proyectofinal.Model.DataStructure.Node;
import com.example.programacion4proyectofinal.Model.FileHandler.FileHandlerBTree;
import com.example.programacion4proyectofinal.Model.Person.Category;
import com.example.programacion4proyectofinal.Model.Person.Passenger;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class FileHandlerBTreeTest {
    @Test
    public void insertValuesBTreeValuesTest() {
        BTree<Passenger> bTree = new BTree<>(10, new FileHandlerBTree());

        long startTime = System.nanoTime();
        for (int i = 1; i <= 10000; i++) {
            bTree.insert(createRandomPassengers());
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;
        System.out.printf("Time execution (Insertion): %.4f seconds%n", durationInSeconds);
    }

    @Test
    public void searchValuesBTreeTest() {
        BTree<Passenger> bTree = new BTree<>(10, new FileHandlerBTree());

        long startTime = System.nanoTime();

        Node<Passenger> s =  bTree.search(bTree.getRoot(), new Passenger(214, "Denis", "Gandarillas", "Brasil", Category.REGULAR_PASSENGER));
        System.out.println(s);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;
        System.out.printf("Time execution (Search): %.4f seconds%n", durationInSeconds);
    }

    @Test
    public void deleteValuesBTreeTest() {
        BTree<Passenger> bTree = new BTree<>(10, new FileHandlerBTree());

        long startTime = System.nanoTime();

        for (int i = 0; i < 50; i++) {
            bTree.remove(createRandomPassengers());
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;
        System.out.printf("Time execution (Search): %.4f seconds%n", durationInSeconds);
    }

    @Test
    public void updateValuesBTreeTest() {
        BTree<Passenger> bTree = new BTree<>(10, new FileHandlerBTree());

        long startTime = System.nanoTime();

        for (int i = 0; i < 50; i++) {
            bTree.update(createRandomPassengers(), createRandomPassengers());
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        double durationInSeconds = (double)duration / 1_000_000_000.0;
        System.out.printf("Time execution (Search): %.4f seconds%n", durationInSeconds);
    }

    public Passenger createRandomPassengers() {
        Random random = new Random();

        String[] names = {"Axel", "Diego", "Camila", "Jhael", "Denis"};
        String[] lastNames = {"Ayala", "Figueroa", "Bustos", "Arce", "Gandarillas"};
        String[] countries = {"Bolivia", "Chile", "Argentina", "Peru", "Brasil"};
        Category[] categories = {Category.VIP, Category.FREQUENT_PASSENGER, Category.REGULAR_PASSENGER};

        return new Passenger(
                random.nextInt(1000),
                names[random.nextInt(names.length)],
                lastNames[random.nextInt(lastNames.length)],
                countries[random.nextInt(countries.length)],
                categories[random.nextInt(categories.length)]
        );
    }
}
