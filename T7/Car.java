package T7;

public class Car {
    public static final int DEFAULT_FUEL = 25;
    public static final int DEFAULT_FUEL_CAPACITY = 30;
    public static final float DEFAULT_LITERS_PER_KM = 1.0f;

    // props
    private int fuel; // current fuel
    private int fuelCapacity; // max fuel
    private float litersPerKm; // consamption of liters per km

    // ctors
    public Car() {
        this.initCar(DEFAULT_FUEL, DEFAULT_FUEL_CAPACITY, DEFAULT_LITERS_PER_KM);
    }

    public Car(int fuel) {
        this.initCar(fuel, DEFAULT_FUEL_CAPACITY, DEFAULT_LITERS_PER_KM);
    }

    public Car(int fuel, int fuelCapacity) {
        this.initCar(fuel, fuelCapacity, DEFAULT_LITERS_PER_KM);
    }

    public Car(int fuel, int fuelCapacity, float litersPerKm) {
        this.initCar(fuel, fuelCapacity, litersPerKm);
    }

    private void initCar(int fuel, int fuelCapacity, float litersPerKm) {
        final int MIN_FUEL = 0;

        // init fuelCapacity
        this.fuelCapacity = fuelCapacity;
        if (this.fuelCapacity < MIN_FUEL) {
            this.fuelCapacity = DEFAULT_FUEL_CAPACITY;
        }

        // init fuel
        this.fuel = fuel;
        if (this.fuel <= MIN_FUEL) {
            this.fuel = MIN_FUEL;
        } else if (fuel > this.fuelCapacity) {
            this.fuel = this.fuelCapacity;
        }

        // init litersPerKm
        this.litersPerKm = litersPerKm;
        if (this.litersPerKm <= 0) {
            this.litersPerKm = DEFAULT_LITERS_PER_KM;
        }
    }

    // Getters
    public int getFuel() {
        return this.fuel;
    }

    public int getFuelCapacity() {
        return this.fuelCapacity;
    }

    public float getLitersPerKm() {
        return this.litersPerKm;
    }

    public float percent() {
        return (float) this.fuel / this.fuelCapacity * 100;
    }

    public boolean urgentGasStation() {
        final int MINIMAL_FUEL = 20;
        final float MINIMAL_FUEL_PERCENTAGE = 10.f;

        return this.fuel < MINIMAL_FUEL || this.percent() < MINIMAL_FUEL_PERCENTAGE;
    }

    public boolean drive(int km) {
        if (km > this.fuel) {
            return false;
        }

        this.fuel -= km;
        return true;
    }

    public double getKmThatCanDriveWithCurrentFuel() {
        return (double) this.fuel / this.litersPerKm;
    }
}
