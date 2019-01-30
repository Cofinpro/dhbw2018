package models;

public class Admin extends User implements csvModel{
    public String userType = "Admin";

    public Admin(String userName, String password, String firstName, String lastName) {
        super(userName, password, firstName, lastName);
    }

    @Override
    public String csvString() {
        return userType+","+super.csvString();
    }
}
