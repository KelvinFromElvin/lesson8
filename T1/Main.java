package T1;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("harry", 90, 90);

        student.printDetails();

        System.out.printf("Is excellent: %s%n", student.isExellent());

        student.birthday();

        student.printDetails();
    }
}
