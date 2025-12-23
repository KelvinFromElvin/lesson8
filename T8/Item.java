package T8;

import T8.defaults.ItemDefaults;

public class Item {
    public static void lazySortOfItems(Item[] array) {
        Item temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].calcTotalPrice() > array[j].calcTotalPrice()) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    private String name; // product name
    private double cost; // product price
    private int count; // how many in stock

    // ctors
    public Item(String name, double cost) {
        this.initItem(name, cost, ItemDefaults.DEFAULT_COUNT);
    }

    public Item(String name, double cost, int count) {
        this.initItem(name, cost, count);
    }

    private void initItem(String name, double cost, int count) {
        this.name = name;
        if (this.name == null || this.name == "") {
            this.name = ItemDefaults.DEFAULT_NAME;
        }

        this.cost = cost;
        if (this.cost <= 0) {
            this.cost = ItemDefaults.DEFAULT_COST;
        }

        this.count = count;
        if (this.count < 0) {
            this.count = ItemDefaults.DEFAULT_COUNT;
        }
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public double cost() {
        return this.cost;
    }

    public int getCount() {
        return this.count;
    }

    // Actions
    public boolean existsInInvertory() {
        return this.count > 0;
    }

    public int decrementCount(int amount) {
        int howManyCannotSupply = 0;

        if (this.count >= amount) {
            this.count -= amount;
            howManyCannotSupply = 0;
        } else {
            howManyCannotSupply = amount - this.count;
            this.count = 0;
        }

        return howManyCannotSupply;
    }

    public double calcTotalPrice() {
        return this.count * this.cost;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.calcTotalPrice();
    }
}
