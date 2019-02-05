package models;

public class Admin extends User implements csvModel{

    public Admin(String userName, String password, String firstName, String lastName) {
        super(userName, password, firstName, lastName);
    }

    @Override
    public String makeCSVString() {
        return getClass().getSimpleName()+","+super.makeCSVString();
    }
}
