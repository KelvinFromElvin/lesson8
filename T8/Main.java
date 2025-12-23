package T8;

public class Main {
    public static Client cheapestList(Client[] clients) {
        if (clients == null || clients.length <= 0) {
            return null;
        }

        Client minClient = clients[0];
        double minPrice = clients[0].getMyTotalItemsPrice();
        double currentPrice;

        for (int i = 1; i < clients.length; i++) {
            currentPrice = clients[i].getMyTotalItemsPrice();

            if (minPrice > currentPrice) {
                minClient = clients[i];
                minPrice = currentPrice;
            }
        }

        return minClient;
    }

    public static void main(String[] args) {
        Store store = new Store();

        /*
         * items = [{cost, count}]
         * 
         * use case 1: checking for exact
         * budget = 10
         * items = [{5, 2}]
         * 
         * use case 2: checking for i need 2 and i have more
         * budget = 10
         * items = [{5, 3}]
         * 
         * use case 3: checking for i need 5 and i have 3 and 2
         * budget = 5 * 4 + 5 * 2 = 20 + 10 = 30
         * items [{5, 4}, {5, 2}]
         * 
         * use case 4: i have for 3 and 2 deferent prices
         * budget = 3 * 3 + 2 * 2 = 9 + 4 = 13
         * items [{3, 3}, {2, 2}]
         * 
         * use case 5: cannot make a list not enoth items
         * budget = 10
         * items [{1,3}]
         */

        // use case 1
        // Client client = new Client("Avraham", 10);
        // client.addItemToBuy(new Item("PS-4", 5, 2));

        // use case 2:
        // Client client = new Client("Avraham", 10);
        // client.addItemToBuy(new Item("PS-4", 5, 3));

        // use case 3:
        // Client client = new Client("Avraham", 30);
        // client.addItemToBuy(new Item("PS-5", 5, 4));
        // client.addItemToBuy(new Item("PS-4", 5, 2));

        // use case 4:
        // Client client = new Client("Avraham", 13);
        // client.addItemToBuy(new Item("PS-5", 3, 3));
        // client.addItemToBuy(new Item("PS-4", 2, 2));

        // use case 5:
        Client client = new Client("Avraham", 30);
        client.addItemToBuy(new Item("PS-5", 1, 4));
        client.addItemToBuy(new Item("PS-4", 1, 2));

        store.printPurchaseListForClient(client);
    }
}
