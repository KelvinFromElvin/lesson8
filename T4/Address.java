package T4;

public class Address {
    public static final int DEFAULT_FLOOR_NUMBER = 1;
    public static final String DEFAULT_CITY = "Jerusalem";
    public static final String DEFAULT_STREET = "Eli cohen";
    public static final String DEFAULT_HOUSE_NUMBER = "1";

    public static Address extructAddress(String fullAddress) {
        // valid full address should be street houseNumber, city
        final int VALID_ADDRESS_PARCIALS_LEN = 3;
        final Address INVALID_FULL_ADDRESS = null;

        if (fullAddress == null || fullAddress == "") {
            return INVALID_FULL_ADDRESS;
        }

        String[] addressParticals = fullAddress.split(" ");

        if (addressParticals.length != VALID_ADDRESS_PARCIALS_LEN) {
            return INVALID_FULL_ADDRESS;
        }

        if (addressParticals[1].charAt(addressParticals[1].length() - 1) != ',') {
            return INVALID_FULL_ADDRESS;
        }

        return new Address(addressParticals[2], addressParticals[0], addressParticals[1]);
    }

    private String city;
    private String street;
    private String houseNumber;
    private int floor;

    public Address() {
        this.initAddress(DEFAULT_CITY, DEFAULT_STREET, DEFAULT_HOUSE_NUMBER, DEFAULT_FLOOR_NUMBER);
    }

    public Address(String city, String street, String houseNumber) {
        this.initAddress(city, street, houseNumber, DEFAULT_FLOOR_NUMBER);
    }

    public Address(String city, String street, String houseNumber, int floor) {
        this.initAddress(city, street, houseNumber, floor);
    }

    private void initAddress(String city, String street, String houseNumber, int floor) {
        this.city = Utils.initDefaultStringValue(city, DEFAULT_CITY);
        this.street = Utils.initDefaultStringValue(street, DEFAULT_STREET);
        this.houseNumber = Utils.initDefaultStringValue(houseNumber, DEFAULT_HOUSE_NUMBER);
        this.floor = floor;
    }

    // Getters
    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public int getFloor() {
        return this.floor;
    }

    // Setters
    public void setCity(String city) {
        this.city = Utils.initDefaultStringValue(city, DEFAULT_CITY);
    }

    public void setStreet(String street) {
        this.street = Utils.initDefaultStringValue(street, DEFAULT_STREET);
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = Utils.initDefaultStringValue(houseNumber, DEFAULT_HOUSE_NUMBER);
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return this.street + " " + this.houseNumber + ", " + this.city;
    }
}
