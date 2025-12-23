package T5;

public class User {
    private static final String DEFAULT_FIRST_NAME = "John";
    private static final String DEFAULT_LAST_NAME = "Smith";
    private static final int INVALID_PARAMETERS = -1;
    private static final int NOT_FOUND = -2;

    private static boolean usersAndUUIDValidation(User[] users, String uuid) {
        if (users == null || users.length <= 0 || uuid == null || uuid.equals("")) {
            return false;
        }
        return true;
    }

    private static int indexOfUserByUUID(User[] users, String uuid) {
        if (!usersAndUUIDValidation(users, uuid)) {
            return INVALID_PARAMETERS;
        }

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || users[i].uuid == null || users[i].uuid.equals("")) {
                continue;
            }

            if (users[i].uuid.equals(uuid)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    private static User[] removeUserFromArrByUUID(User[] users, String uuid) {
        if (!usersAndUUIDValidation(users, uuid)) {
            // invalid args
            return null;
        }

        if (users.length - 1 < 0) {
            // tring to remove from empty array
            return null;
        }

        User[] newUsers = new User[users.length - 1];

        int indexOfUserToRemove = indexOfUserByUUID(users, uuid);

        if (indexOfUserToRemove < 0) {
            // user not found
            return null;
        }

        for (int usersIdx = 0, newUsersIdx = 0; usersIdx < users.length
                && newUsersIdx < newUsers.length; usersIdx++, newUsersIdx++) {
            if (usersIdx == indexOfUserToRemove) {
                continue;
            }

            newUsers[newUsersIdx] = users[usersIdx];
        }

        return newUsers;
    }

    private static User[] addUserToArr(User[] users, User user) {
        if (users == null || users.length < 0 || user == null) {
            return users;
        }

        User[] newUsers = new User[users.length + 1];

        for (int i = 0; i < users.length; i++) {
            newUsers[i] = users[i];
        }

        newUsers[newUsers.length - 1] = user;

        return newUsers;
    }

    private static boolean isUserInArrayByUUID(User[] users, String uuid) {
        if (uuid == null || uuid == "" || users == null || users.length <= 0) {
            return false;
        }

        for (int i = 0; i < users.length; i++) {
            if (users[i].getUUID().equals(uuid)) {
                return true;
            }
        }

        return false;
    }

    private String uuid;
    private String firstName;
    private String lastName;
    private int age;
    private int imagesCount;
    private int videosCount;
    private User[] following; // following now
    private User[] followers; // general followers

    public User(String firstName, String lastName, int age) {
        this.uuid = T4.Utils.generateUUID();
        this.firstName = T4.Utils.initDefaultStringValue(firstName, DEFAULT_FIRST_NAME);
        this.lastName = T4.Utils.initDefaultStringValue(lastName, DEFAULT_LAST_NAME);

        this.age = Math.max(age, 18);

        this.imagesCount = this.videosCount = 0;

        this.followers = new User[0];
        this.following = new User[0];
    }

    // Getters
    public String getUUID() {
        return this.uuid;
    }

    public int getAge() {
        // just to remove this anoing warning that i do not use age...
        return this.age;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    private boolean isUserFollowerByUUID(String uuid) {
        return User.isUserInArrayByUUID(this.followers, uuid);
    }

    private boolean isUserFollowingByUUID(String uuid) {
        return User.isUserInArrayByUUID(this.following, uuid);
    }

    // actions
    public void addNewFollower(User user) {
        if (user == null || user.getUUID().equals(this.getUUID())) {
            return;
        }

        if (this.isUserFollowerByUUID(user.getUUID())) {
            // if user alreay following no need to add him again
            return;
        }

        User[] newFollowers = User.addUserToArr(this.followers, user);

        if (newFollowers == null) {
            return;
        }

        this.followers = newFollowers;
    }

    public void addNewFollowing(User user) {
        if (user == null || user.getUUID().equals(this.getUUID())) {
            return;
        }

        if (this.isUserFollowingByUUID(user.getUUID())) {
            // if user alreay following no need to add him again
            return;
        }

        User[] newFollowing = User.addUserToArr(this.following, user);

        if (newFollowing == null) {
            return;
        }

        this.following = newFollowing;
    }

    public void uploadImage() {
        this.imagesCount++;
    }

    public void uploadVideo() {
        this.videosCount++;
    }

    public void printAllMyFrientdsName() {
        for (int i = 0; i < this.followers.length; i++) {
            System.out.println(this.followers[i].getFullName());
        }
    }

    // other
    public boolean isValidName() {
        return this.isStrValidName(this.firstName) && this.isStrValidName(this.lastName);
    }

    private boolean isStrValidName(String name) {
        if (name == null || name == "") {
            return false;
        }

        String[] numbers = new String[] {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

        for (int i = 0; i < numbers.length; i++) {
            if (name.contains(numbers[i])) {
                return false;
            }
        }

        return true;
    }

    private int countFollowersWithValidName() {
        int countFollowersWithValidName = 0;

        for (int i = 0; i < this.followers.length; i++) {
            if (this.followers[i].isValidName()) {
                countFollowersWithValidName++;
            }
        }

        return countFollowersWithValidName;
    }

    public boolean isVip() {
        final int MIN_UPLOADED_IMAGES = 10;
        final int MIN_UPLOADED_VIDEOS = 10;
        final int MIN_FOLLOWERS = 10;

        if (!this.isValidName()) {
            return false;
        }
        if (this.imagesCount < MIN_UPLOADED_IMAGES) {
            return false;
        }
        if (this.videosCount < MIN_UPLOADED_VIDEOS) {
            return false;
        }

        if (this.countFollowersWithValidName() < MIN_FOLLOWERS) {
            return false;
        }

        return true;
    }

    public boolean isUserFollowingMe(String uuidToCheck) {
        if (uuidToCheck == null || uuidToCheck == "") {
            return false;
        }

        for (int i = 0; i < followers.length; i++) {
            if (followers[i].getUUID().equals(uuidToCheck)) {
                return true;
            }
        }

        return false;
    }

    public int countMutualConnections() {
        int count = 0;

        for (int i = 0; i < this.followers.length; i++) {
            if (this.followers[i].isUserFollowingMe(this.uuid)) {
                count++;
            }
        }

        return count;
    }

    public boolean amIFakeUser() {
        final int FAILDED_CONDITION_LIMIT = 3;

        int failedConditionsCount = 0;

        if (!this.isValidName()) {
            failedConditionsCount++;
        }

        if (this.imagesCount <= 0) {
            failedConditionsCount++;
        }

        if (this.videosCount <= 0) {
            failedConditionsCount++;
        }

        if (this.countMutualConnections() <= 0) {
            failedConditionsCount++;
        }

        return failedConditionsCount >= FAILDED_CONDITION_LIMIT;
    }

    private String[] findAllMyFakeUsersUUIDs() {

        if (this.followers == null || this.followers.length <= 0) {
            return null;
        }

        String[] fakeFollowersUuidFull = new String[this.followers.length];
        int fakeFollowersLen = 0;

        for (int i = 0; i < this.followers.length; i++) {
            if (this.followers[i].amIFakeUser()) {
                fakeFollowersUuidFull[fakeFollowersLen] = this.followers[i].getUUID();
                fakeFollowersLen++;
            }
        }

        String[] fakeFollowersUuid = new String[fakeFollowersLen];

        for (int i = 0; i < fakeFollowersUuid.length; i++) {
            fakeFollowersUuid[i] = fakeFollowersUuidFull[i];
        }

        return fakeFollowersUuid;
    }

    private void removeFollowerFromFollowers(String uuid) {
        User[] newFollowers = User.removeUserFromArrByUUID(this.followers, uuid);

        if (newFollowers == null) {
            return;
        }

        this.followers = newFollowers;
    }

    private void removeFollowerFromFollowing(String uuid) {
        User[] newFollowing = User.removeUserFromArrByUUID(this.following, uuid);

        if (newFollowing == null) {
            return;
        }

        this.following = newFollowing;
    }

    public void removeFakeUsers() {
        String[] fakeFollowersUuid = this.findAllMyFakeUsersUUIDs();

        if (fakeFollowersUuid == null) {
            return;
        }

        for (int i = 0; i < fakeFollowersUuid.length; i++) {
            this.removeFollowerFromFollowers(fakeFollowersUuid[i]);
            this.removeFollowerFromFollowing(fakeFollowersUuid[i]);
        }
    }
}
