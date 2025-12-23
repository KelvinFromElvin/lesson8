package T4;

import java.util.Date;

public class Order {
    public static final String DEFAULT_RECEPIENT_NAME = "unknown";

    private double price;
    private Address addressOfReceiver;
    private String recepientName;
    private Date orderedOn;
    private Date deliverOn;

    public Order(double price, Address addressOfReceiver, String recepientName, Date orderedOn, Date deliverOn) {
        this.initOrder(price, addressOfReceiver, recepientName, orderedOn, deliverOn);
    }

    public Order(double price, String addressOfReceiver, String recepientName, Date orderedOn, Date deliverOn) {
        Address address = Address.extructAddress(addressOfReceiver);

        this.initOrder(price, address, recepientName, orderedOn, deliverOn);
    }

    private void initOrder(double price, Address addressOfReceiver, String recepientName, Date orderedOn,
            Date deliverOn) {
        this.price = Math.max(0, price);

        if (this.addressOfReceiver == null) {
            this.addressOfReceiver = new Address();
        }

        this.recepientName = Utils.initDefaultStringValue(recepientName, DEFAULT_RECEPIENT_NAME);

        this.orderedOn = orderedOn;
        this.deliverOn = deliverOn;

        if (this.orderedOn == null) {
            this.orderedOn = new Date();
        }
        if (this.deliverOn == null) {
            this.deliverOn = new Date();
        }
    }

    // Getters
    public double getPrice() {
        return this.price;
    }

    public Address getAddressOfRecevier() {
        return this.addressOfReceiver;
    }

    public String getRecepientName() {
        return this.recepientName;
    }

    public Date getOrderedOn() {
        return this.orderedOn;
    }

    public Date getDeliverOn() {
        return this.deliverOn;
    }

    public String getCityName() {
        return this.getAddressOfRecevier().getCity();
    }

    public String getClientInitials() {
        String[] splittedName = this.recepientName.split(" ");
        String initials = "";

        for (int i = 0; i < splittedName.length; i++) {
            initials += splittedName[i].charAt(0);
        }

        return initials;
    }
}
