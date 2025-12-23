package T6;

public class Hotel {
    private static final String DEFAULT_HOTEL_NAME = "Hotel Transilvania";

    private String name;
    private Room[] rooms;

    // ctors
    public Hotel() {
        this.initHotel(DEFAULT_HOTEL_NAME);
    }

    public Hotel(String name) {
        this.initHotel(name);
    }

    private void initHotel(String name) {
        this.name = name;

        if (this.name == null || this.name == "") {
            this.name = Hotel.DEFAULT_HOTEL_NAME;
        }

        this.rooms = new Room[0];
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public void addARoom(Room room) {
        if (room == null) {
            return;
        }

        Room[] newRooms = new Room[this.rooms.length + 1];

        for (int i = 0; i < this.rooms.length; i++) {
            newRooms[i] = this.rooms[i];
        }

        newRooms[newRooms.length - 1] = room;

        this.rooms = newRooms;
    }

    public Room[] findAvailableRoomsInBudgetInType(int type, int budget) {
        if (!RoomType.isRoomTypeValid(type)) {
            return null;
        }
        if (budget <= 0) {
            return null;
        }

        Room[] matchingRoomsWithPadd = new Room[this.rooms.length];
        int matchingRoomsWithPaddLen = 0;

        for (int i = 0; i < this.rooms.length; i++) {
            if (this.rooms[i].getRoomAvailability() && this.rooms[i].getRoomType() == type
                    && this.rooms[i].calculatePrice() <= budget) {
                matchingRoomsWithPadd[matchingRoomsWithPaddLen] = this.rooms[i];
                matchingRoomsWithPaddLen++;
            }
        }

        Room[] matchingRooms = new Room[matchingRoomsWithPaddLen];

        for (int i = 0; i < matchingRooms.length; i++) {
            matchingRooms[i] = matchingRoomsWithPadd[i];
        }

        return matchingRooms;
    }

    public double income() {
        double price = 0;

        for (int i = 0; i < this.rooms.length; i++) {
            if (!this.rooms[i].getRoomAvailability()) {
                price += this.rooms[i].calculatePrice();
            }
        }

        return price;
    }
}
