package T8;

import T8.defaults.ClientDefaults;

public class Client {
    private String name; // client name
    private Item[] toBuy; // what client want to buy
    private int budget; // his budget

    public Client() {
        this.initClient(ClientDefaults.DEFAULT_NAME, ClientDefaults.DEFAULT_BUDGET);
    }

    public Client(String name) {
        this.initClient(name, ClientDefaults.DEFAULT_BUDGET);
    }

    public Client(String name, int budget) {
        this.initClient(name, budget);
    }

    private void initClient(String name, int budget) {
        this.name = name;
        if (this.name == null || this.name == "") {
            this.name = ClientDefaults.DEFAULT_NAME;
        }

        this.budget = budget;
        if (this.budget < 0) {
            this.budget = ClientDefaults.DEFAULT_BUDGET;
        }

        this.toBuy = new Item[0];
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getBudget() {
        return this.budget;
    }

    public Item[] getToBuy() {
        return this.toBuy;
    }

    // Actions
    public double getMyTotalItemsPrice() {
        double total = 0;

        for (int i = 0; i < this.toBuy.length; i++) {
            if (this.toBuy[i].existsInInvertory()) {
                total += this.toBuy[i].calcTotalPrice();
            }
        }

        return total;
    }

    public boolean hasEnoughBudget() {
        double total = this.getMyTotalItemsPrice();

        return total <= this.budget;
    }

    public void addItemToBuy(Item toBuy) {
        Item[] newToBuy = new Item[this.toBuy.length + 1];

        for (int i = 0; i < this.toBuy.length; i++) {
            newToBuy[i] = this.toBuy[i];
        }

        newToBuy[newToBuy.length - 1] = toBuy;

        this.toBuy = newToBuy;

        Item.lazySortOfItems(this.toBuy);
    }
}
