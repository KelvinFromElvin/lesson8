package T4;

public class Store {
    private Order[] orders;
    private CityDelivery[] cityDeliveries;

    public Store() {
        this.orders = new Order[0];
        this.cityDeliveries = new CityDelivery[0];
    }

    public String mostExpensiveCityDelivery() {
        double maxDistance = 0;
        String nameOfMostExpensiveCityDelivery = "";

        for (int i = 0; i < this.cityDeliveries.length; i++) {
            if (maxDistance < this.cityDeliveries[i].getDistanceFromStore()) {
                nameOfMostExpensiveCityDelivery = this.cityDeliveries[i].getCity();
                maxDistance = this.cityDeliveries[i].getDistanceFromStore();
            }
        }

        return nameOfMostExpensiveCityDelivery;
    }

    public int countOrdersToCity(String city) {
        int count = 0;

        for (int i = 0; i < this.orders.length; i++) {
            if (this.orders[i].getCityName().equals(city)) {
                count++;
            }
        }

        return count;
    }

    public String[] clientsWithExpensiveDelivery(int price) {
        int deliverisPerCurrentCity;
        double currentDistancePrice;

        double currentPriceTotal;
        String initials = "";

        if (price < 0) {
            return null;
        }

        for (int i = 0; i < this.cityDeliveries.length; i++) {
            deliverisPerCurrentCity = this.countOrdersToCity(this.cityDeliveries[i].getCity());
            currentDistancePrice = this.cityDeliveries[i].calcDistancePrice();

            for (int j = 0; j < this.orders.length; j++) {
                if (this.orders[j].getCityName() == this.cityDeliveries[i].getCity()) {
                    currentPriceTotal = (this.orders[j].getPrice() + currentDistancePrice) / deliverisPerCurrentCity;

                    if (currentPriceTotal >= price) {
                        initials += this.orders[j].getClientInitials() + " ";
                    }
                }
            }
        }

        return initials.split(" ");
    }
}
