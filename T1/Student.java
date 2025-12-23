package T1;

public class Student {
    public static final int MIN_EXELLENT_GRAGE = 90;

    private String name;
    private int age;
    private double avg;

    public Student(String name, int age, double avg) {
        this.name = name;
        this.age = Math.max(0, age);
        this.avg = Math.max(0, avg);
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.avg;
    }

    public void printDetails() {
        String result = this.toString();

        System.out.println(result);
    }

    public boolean isExellent() {
        return this.age >= Student.MIN_EXELLENT_GRAGE;
    }

    public void birthday() {
        this.age++;
    }
}