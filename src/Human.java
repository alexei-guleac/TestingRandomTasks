public class Human {
    private String name, surname;
    private int friendsAmount;

    public Human(String name, String surname, int friendsAmount) {
        this.name = name;
        this.surname = surname;
        this.friendsAmount = friendsAmount;
    }

    public Human(String surname, int friendsAmount) {
        this.surname = surname;
        this.friendsAmount = friendsAmount;
    }

    public Human(int friendsAmount) {
        this.friendsAmount = friendsAmount;
    }

    public Human() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getFriendsAmount() {
        return friendsAmount;
    }
}
