package T6;

public class Room {
    // floor limits
    private static final int MIN_FLOORS_IN_HOTEL = 1;
    private static final int MAX_FLOORS_IN_HOTEL = 20;

    // rooms limits
    private static final int MIN_ROOMS_IN_HOTEL = 1;
    private static final int MAX_ROOMS_IN_HOTEL = 99;

    // defaults
    public static final int DEFAULT_ROOM_TYPE = RoomType.ROOM_TYPE_REGULAR;
    public static final boolean DEFAULT_ROOM_AVAILABILITY = false;
    public static final int DEFAULT_FLOOR = MIN_FLOORS_IN_HOTEL;
    public static final int DEFAULT_ROOM = MIN_ROOMS_IN_HOTEL;

    private static int convertFloorAndDistToRoomNumber(int floor, int distanceFrom) {
        // floor
        if (floor > MAX_FLOORS_IN_HOTEL) {
            floor = MAX_FLOORS_IN_HOTEL;
        }
        if (floor < MIN_FLOORS_IN_HOTEL) {
            floor = MIN_FLOORS_IN_HOTEL;
        }

        // distance from elevator
        if (distanceFrom > MAX_ROOMS_IN_HOTEL) {
            distanceFrom = MAX_ROOMS_IN_HOTEL;
        }
        if (distanceFrom < MIN_ROOMS_IN_HOTEL) {
            distanceFrom = MIN_ROOMS_IN_HOTEL;
        }

        return floor * 100 + distanceFrom;
    }

    // state
    private int number; // room number
    private int type; // room type
    /*
     * 1 - regular
     * 2 - upgraded
     * 3 - suite
     */
    private boolean available; // is room available or used by someone

    // ctors
    public Room() {
        this.initRoom(Room.convertFloorAndDistToRoomNumber(Room.DEFAULT_FLOOR, Room.DEFAULT_ROOM),
                Room.DEFAULT_ROOM_TYPE,
                Room.DEFAULT_ROOM_AVAILABILITY);
    }

    public Room(int floor) {
        this.initRoom(Room.convertFloorAndDistToRoomNumber(floor, Room.MAX_ROOMS_IN_HOTEL), Room.DEFAULT_ROOM_TYPE,
                Room.DEFAULT_ROOM_AVAILABILITY);
    }

    public Room(int floor, int distanceFrom) {
        this.initRoom(Room.convertFloorAndDistToRoomNumber(floor, distanceFrom), Room.DEFAULT_ROOM_TYPE,
                Room.DEFAULT_ROOM_AVAILABILITY);
    }

    public Room(int floor, int distanceFrom, int roomType) {
        this.initRoom(Room.convertFloorAndDistToRoomNumber(floor, distanceFrom), roomType,
                Room.DEFAULT_ROOM_AVAILABILITY);
    }

    public Room(int floor, int distanceFrom, int roomType, boolean isRoomAvailable) {
        this.initRoom(Room.convertFloorAndDistToRoomNumber(floor, distanceFrom), roomType,
                isRoomAvailable);
    }

    // init room
    private void initRoom(int number, int type, boolean available) {
        this.number = number;

        if (RoomType.isRoomTypeValid(type)) {
            this.type = type;
        } else {
            this.type = Room.DEFAULT_ROOM_TYPE;
        }

        this.available = available;
    }

    // Getters
    public int getRoomNumber() {
        return this.number;
    }

    public int getRoomType() {
        return this.type;
    }

    public boolean getRoomAvailability() {
        return this.available;
    }

    // Actions
    public int floor() {
        return this.number / 100 % 100;
    }

    public int roomDistanceFromElevator() {
        return this.number % 100;
    }

    public double calculatePrice() {
        final int BASE_PRICE = 2000;
        // floor markup
        final int FLOOR_MARKUP_START_FROM = 11;
        final int FLOOR_MARKUP_PRICE = 100;
        // room markup
        final int ROOM_MARKUP_END = 5;
        final int ROOM_MARKUP = 200;
        // room type multiplayers
        final double ROOM_TYPE_UPGRADED_MULTIPLAYER = 1.5;
        final double ROOM_TYPE_SUITE_MULTIPLAYER = 2.5;

        double price = BASE_PRICE;
        int floor = this.floor();
        int roomDistanceFromElevator = this.roomDistanceFromElevator();

        if (floor >= FLOOR_MARKUP_START_FROM) {
            price += (floor - FLOOR_MARKUP_START_FROM + 1) * FLOOR_MARKUP_PRICE;
        }

        if (roomDistanceFromElevator <= ROOM_MARKUP_END) {
            price += ROOM_MARKUP;
        }

        switch (this.type) {
            case RoomType.ROOM_TYPE_UPGRADED:
                price *= ROOM_TYPE_UPGRADED_MULTIPLAYER;
                break;

            case RoomType.ROOM_TYPE_SUITE:
                price *= ROOM_TYPE_SUITE_MULTIPLAYER;
                break;
        }

        return price;
    }

    @Override
    public String toString() {
        return this.floor() + ", " + this.roomDistanceFromElevator() + ", "
                + RoomType.convertRoomTypeToString(this.getRoomType());
    }
}
