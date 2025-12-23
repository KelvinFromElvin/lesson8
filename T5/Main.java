package T5;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Broce", "Wain", 30);
        User user2 = new User("Klark", "Kent", 30);
        User user3 = new User("Jack", "Napier1", 30);

        // init user1
        user1.addNewFollower(user2);
        user1.addNewFollower(user3);
        user1.uploadImage();
        user1.uploadImage();
        user1.uploadVideo();

        // init user2
        user2.addNewFollower(user1);
        user2.uploadImage();
        user2.uploadImage();
        user2.uploadVideo();

        System.out.println("Is User fake: ");
        System.out.printf("%s is fake %b%n", user1.getFullName(), user1.amIFakeUser());
        System.out.printf("%s is fake %b%n", user2.getFullName(), user2.amIFakeUser());
        System.out.printf("%s is fake %b%n", user3.getFullName(), user3.amIFakeUser());

        System.out.println();

        System.out.printf("%s's friends:%n", user1.getFullName());
        user1.printAllMyFrientdsName();

        System.out.println();
        user1.removeFakeUsers();

        System.out.printf("%s's friends:%n", user1.getFullName());
        user1.printAllMyFrientdsName();
    }
}
