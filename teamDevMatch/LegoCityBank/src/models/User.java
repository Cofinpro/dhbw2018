package models;

public class User implements Comparable<User> {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    public User(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName(){
        return userName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean tryLogIn(String inputPassword) {

        return false;
    }

    @Override
    public int compareTo(User otherUser) {
        return this.userName.compareTo(otherUser.userName);
    }
}
