import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    private static void PrintData(Iterable<Person> persons) {
        for (Person p : persons) {
            String message = p.toString() + " earns " + Double.toString(p.getPaymentAmount());
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(
                new Employee("John", "Lennon", "somePosition", 27045.78F)
        );
        persons.add(
                new Employee("George", "Harrison", "somePosition", 50000.00F)
        );
        persons.add(
                new Student("Ringo", "Starr", 2.34F)
        );
        persons.add(
                new Student("Paul", "McCartney", 3.45F)
        );

        Collections.sort(persons);
        PrintData(persons);
    }
}