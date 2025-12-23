package T6;

public class RoomType {
    public static final int ROOM_TYPE_REGULAR = 1;
    public static final int ROOM_TYPE_UPGRADED = 2;
    public static final int ROOM_TYPE_SUITE = 3;

    public static boolean isRoomTypeValid(int roomType) {
        switch (roomType) {
            case ROOM_TYPE_REGULAR:
            case ROOM_TYPE_UPGRADED:
            case ROOM_TYPE_SUITE:
                return true;

            default:
                return false;
        }
    }

    public static String convertRoomTypeToString(int roomType) {
        String roomTypeName = "";

        if (!isRoomTypeValid(roomType)) {
            return roomTypeName;
        }

        switch (roomType) {
            case ROOM_TYPE_REGULAR:
                roomTypeName = "regular";
                break;
            case ROOM_TYPE_UPGRADED:
                roomTypeName = "upgraded";
                break;
            case ROOM_TYPE_SUITE:
                roomTypeName = "suite";
                break;
        }

        return roomTypeName;
    }
}
