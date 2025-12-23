package T6;

public class Main {
    public static String mostProfitable(Hotel[] hotels) {
        String hotelName = "";
        double maxIncome = 0;
        double income;

        if (hotels == null || hotels.length <= 0) {
            return hotelName;
        }

        maxIncome = hotels[0].income();
        hotelName = hotels[0].getName();

        for (int i = 1; i < hotels.length; i++) {
            income = hotels[i].income();
            if (income > maxIncome) {
                maxIncome = hotels[i].income();
                hotelName = hotels[i].getName();
            }
        }

        return hotelName;
    }

    public static void main(String[] args) {
        Hotel hotel;
        Hotel[] hotels = new Hotel[2];

        // init hotel 1
        hotel = new Hotel();
        hotel.addARoom(new Room(1, 1));
        hotel.addARoom(new Room(1, 2));
        hotel.addARoom(new Room(1, 3));
        hotel.addARoom(new Room(15, 2, RoomType.ROOM_TYPE_SUITE));
        hotels[0] = hotel;

        // init hotel 2
        hotel = new Hotel("fake");
        hotel.addARoom(new Room(1, 1));
        hotel.addARoom(new Room(1, 2));
        hotel.addARoom(new Room(1, 3));
        hotel.addARoom(new Room(15, 2, RoomType.ROOM_TYPE_SUITE, true));
        hotels[1] = hotel;

        System.out.println(mostProfitable(hotels));
    }
}
