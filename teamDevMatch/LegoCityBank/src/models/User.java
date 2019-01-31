package models;

public abstract class User implements Comparable<User>, csvModel {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    public User(String userName, String password, String firstName, String lastName) {
        String error;
        if (!(error = isUserNameValid(userName)).equals(""))
            throw new IllegalArgumentException(error);
        if (!(error = isPasswordValid(password)).equals(""))
            throw new IllegalArgumentException(error);
        if (!(error = isFirstNameValid(firstName)).equals(""))
            throw new IllegalArgumentException(error);
        if (!(error = isLastNameValid(lastName)).equals(""))
            throw new IllegalArgumentException(error);

        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static String isUserNameValid (String userNameToCheck) {
        if (userNameToCheck == null || userNameToCheck.length() < 4 || userNameToCheck.length() > 16)
            return "Nutzername sollte zwischen 4 und 16 Zeichen haben";
        if (CustomerManager.getInstance().isUserNameTaken(userNameToCheck))
            return "Nutzername bereits vergeben";
        return "";
    }

    public static String isPasswordValid (String passwordToCheck) {
        if (passwordToCheck == null || passwordToCheck.length() < 5 || passwordToCheck.length() > 32)
            return "Passwort sollte zwischen 5 und 32 Zeichen haben";
        return "";
    }

    public static String isFirstNameValid(String firstNameToCheck) {
        if (firstNameToCheck == null || firstNameToCheck.length() < 2 || firstNameToCheck.length() > 16)
            return "Vorname sollte zwischen 2 und 16 Zeichen haben";
        return "";
    }

    public static String isLastNameValid (String lastNameToCheck) {
        if (lastNameToCheck == null || lastNameToCheck.length() < 4 || lastNameToCheck.length() > 16)
            return "Nachname sollte zwischen 2 und 16 Zeichen haben";
        return "";
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

    public String getFullName() { return firstName + " " + lastName; }

    public boolean tryLogIn(String inputPassword) {
        return inputPassword.equals(this.password);
    }

    @Override
    public int compareTo(User otherUser) {
        return this.userName.compareTo(otherUser.userName);
    }

    @Override
    public String csvString() {
        return userName+","+password+","+firstName+","+lastName;
    }

    public String toString() {
        return "Account "+userName;
    }
}
