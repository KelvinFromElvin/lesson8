package T8;

public class Store {
    private int reqGuardListOfPrcheses = 0;
    private static final int MAX_REQ_ITERETIONS = 1000000;
    private static final int FUNC_FLAG_FOUND_LIST_VARIETION = 0;
    private static final int FUNC_FLAG_NOT_FOUND_LIST_VARIETION = -1;
    private static final int REQ_GUARD_ALERT = -2;

    private Client[] clients;
    private Item[] items;

    public Store() {
        this.clients = new Client[0];
        this.items = new Item[0];
        this.reqGuardListOfPrcheses = 0;
    }

    // Getters
    public Client[] getClients() {
        return this.clients;
    }

    public Item[] getItems() {
        return this.items;
    }

    private int printPurchaseListForClientReq(Item[] sortedItems, int id, double currentPrice) {

        this.reqGuardListOfPrcheses++;
        if (this.reqGuardListOfPrcheses >= Store.MAX_REQ_ITERETIONS) {
            return Store.REQ_GUARD_ALERT;
        }

        if (id < 0) {
            if (currentPrice <= 0) {
                return FUNC_FLAG_FOUND_LIST_VARIETION;
            } else {
                return Store.FUNC_FLAG_NOT_FOUND_LIST_VARIETION;
            }
        }

        int howManyItemsINeed = (int) (currentPrice / sortedItems[id].cost());
        int result = Store.FUNC_FLAG_NOT_FOUND_LIST_VARIETION;
        double itemPrice;
        boolean loopStopFlag = false;

        if (currentPrice % sortedItems[id].cost() == 0) {
            // can full
            if (howManyItemsINeed <= sortedItems[id].getCount()) {
                // has enoght in stock
                System.out.println(sortedItems[id].getName() + ":# " + (sortedItems[id].cost() * howManyItemsINeed));
                return Store.FUNC_FLAG_FOUND_LIST_VARIETION;
            }
        }

        for (int i = sortedItems[id].getCount(); i > 0; i--) {
            itemPrice = sortedItems[id].cost() * i;

            if (itemPrice > currentPrice) {
                continue;
            }

            result = printPurchaseListForClientReq(sortedItems, id - 1, currentPrice - itemPrice);
            if (result == Store.FUNC_FLAG_FOUND_LIST_VARIETION) {
                // found list variation and need to print this item
                // as it was part of list variation
                System.out.println(sortedItems[id].getName() + ":@ " + (sortedItems[id].cost() * i));
                // return result;
                loopStopFlag = true;
                break;
            } else if (result == Store.REQ_GUARD_ALERT) {
                // return Store.REQ_GUARD_ALERT;
                loopStopFlag = true;
                break;
            }
        }

        if (loopStopFlag) {
            return result;
        } else {
            return printPurchaseListForClientReq(sortedItems, id - 1, currentPrice);
        }

    }

    public void printPurchaseListForClient(Client client) {
        this.reqGuardListOfPrcheses = 0;

        int result = printPurchaseListForClientReq(client.getToBuy(), client.getToBuy().length - 1, client.getBudget());

        switch (result) {
            case FUNC_FLAG_NOT_FOUND_LIST_VARIETION:
                System.out.println("Cannot create list");
                break;
            case REQ_GUARD_ALERT:
                System.out.println("Something went wrong with req");
                break;
            default:
                break;
        }
    }
}
