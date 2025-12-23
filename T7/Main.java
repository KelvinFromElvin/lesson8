package T7;

public class Main {
    public static double getMaxKmThatAllCarsCanDrive(Car[] cars) {
        final int FUEL_TO_SAVE = 1;

        if (cars == null || cars.length <= 0) {
            return 0;
        }

        double minDistance = cars[0].getKmThatCanDriveWithCurrentFuel();
        double curDistance;

        for (int i = 1; i < cars.length; i++) {
            curDistance = cars[i].getKmThatCanDriveWithCurrentFuel();

            if (curDistance < minDistance) {
                minDistance = curDistance;
            }
        }

        return Math.max(minDistance - FUEL_TO_SAVE, 0);
    }

    public static void main(String[] args) {
        Car car = new Car(10, 15);

        System.out.println(car.urgentGasStation());
    }
}
