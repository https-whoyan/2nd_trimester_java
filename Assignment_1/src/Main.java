class Person_v1 {
    String name;
    int age;
}

class Person_v2 {
    String name;
    int age;

    public void printDetails() {
        System.out.print("Имя " + this.name +  "; Возвраст " + this.age);
    }
}

class Person_v3 {
    private String name;
    private int age;

    public void printDetails() {
        System.out.print("Имя " + this.name +  "; Возвраст " + this.age);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}

class Person_v4 {
    private String name;
    private int age;

    Person_v4(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printDetails() {
        System.out.print("Имя " + this.name +  "; Возвраст " + this.age);
    }
}

public class Main {
    private static void Task1_1() {
        Person_v1 person = new Person_v1();
        person.name = "Диас";
        person.age = 17;
        System.out.println("Имя: " + person.name + "; Возвраст: " + person.age);
    }

    private static void Task1_2() {
        Person_v2 person = new Person_v2();
        person.name = "Максим";
        person.age = 17;
        person.printDetails();
    }

    private static void Task1_3() {
        Person_v3 person = new Person_v3();
        person.setName("Рахат");
        person.setAge(16);
        System.out.println("Имя: " + person.getName() + "; Возвраст: " + person.getAge());
    }

    private static void Task1_4() {
        Person_v4 person = new Person_v4("Адильбек", 32);
        person.printDetails();
    }

    public static void main(String[] args) {
        //Task1_1();
        //Task1_2();
        //Task1_3();
        Task1_4();
    }
}