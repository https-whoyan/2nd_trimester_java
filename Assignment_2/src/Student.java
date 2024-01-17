public class Student extends Person {
    private double gpa;

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public Student() {
    }

    public Student(String name, String surname, double gpa) {
        super.setName(name);
        super.setSurname(surname);
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student: " + super.toString();
    }

    @Override
    public double getPaymentAmount(){
        if (this.gpa > 2.67) return 36660.00;
        else return 0;
    }
}
