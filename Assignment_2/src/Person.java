import java.util.Collection;

public class Person implements Payable, Comparable<Person> {
    private static int nextId = 1;
    private int id = nextId;
    private String name, surname;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Person(){
        this.id = nextId++;
    }

    public Person(String name, String surname) {
        this.id = nextId++;
        setName(name);
        setSurname(surname);
    }

    public String toString() {
        return Integer.toString(getId()) + ". " + getName() + " " + getSurname();
    }

    public String getPosition() {
        return "Student";
    }

    @Override
    public double getPaymentAmount(){
        return 0;
    }

    @Override
    public int compareTo(Person o) {
        return Double.compare(
                this.getPaymentAmount(),
                o.getPaymentAmount()
        );
    }
}
