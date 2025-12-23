package T4;

public class CityDelivery {
    private static final double DEFAULT_DISTANCE_FROM_STORE = 0;
    public static final double DISTANCE_MARKUP_PRICE = 4.5;

    private String city;
    private double distanceFromStore;

    public CityDelivery() {
        this.city = Address.DEFAULT_CITY;
        this.distanceFromStore = DEFAULT_DISTANCE_FROM_STORE;
    }

    public CityDelivery(String city, double distanceFromStore) {
        this.city = Utils.initDefaultStringValue(city, Address.DEFAULT_CITY);
        this.distanceFromStore = Math.max(DEFAULT_DISTANCE_FROM_STORE, distanceFromStore);
    }

    // Getters
    public String getCity() {
        return this.city;
    }

    public double getDistanceFromStore() {
        return this.distanceFromStore;
    }

    public double calcDistancePrice() {
        return this.distanceFromStore * DISTANCE_MARKUP_PRICE;
    }
}
