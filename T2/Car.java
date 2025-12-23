package T2;

public class Car {
    public static final int SPEED_VALUE = 10;

    private String company;
    private int year;
    private int speed;

    public Car(String company, int year, int speed) {
        this.company = company;

        this.year = Math.max(1900, year);

        this.speed = Math.max(0, speed);
    }

    public void accelerate() {
        this.speed += Car.SPEED_VALUE;
    }

    public void brake() {
        this.speed = Math.max(0, this.speed - Car.SPEED_VALUE);
    }

    @Override
    public String toString() {
        return this.company + ", " + this.year + ", " + this.speed;
    }

    public void printStatuc() {
        String result = this.toString();

        System.out.println(result);
    }
}
