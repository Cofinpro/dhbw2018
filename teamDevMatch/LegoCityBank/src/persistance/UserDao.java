package persistance;

import helper.CSVHelper;
import models.*;

import java.math.BigInteger;
import java.util.*;

public class UserDao {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null)
            instance = new UserDao();
        return instance;
    }

    private UserDao() {}

    public Set<User> readUsersFromCSV() {
        Set<User> users = new TreeSet<>();
        CSVHelper helper = new CSVHelper("resources\\users.csv");
        Collection<String[]> customerRepresentations = helper.readCSV();

        for (String[] customerRepresentation : customerRepresentations) {
            int userTypeIndex = 0;
            int userNameIndex = 1;
            int passwordIndex = 2;
            int firstNameIndex = 3;
            int lastNameIndex = 4;
            String userType = customerRepresentation[userTypeIndex];
            String username = customerRepresentation[userNameIndex];
            String password = customerRepresentation[passwordIndex];
            String firstName = customerRepresentation[firstNameIndex];
            String lastName = customerRepresentation[lastNameIndex];

            switch (userType) {
                case "Admin":
                    Admin admin = new Admin(username, password, firstName, lastName);
                    users.add(admin);
                    break;
                case "Customer":
                    int customerNumberIndex = 5;
                    BigInteger customerNumber = new BigInteger(customerRepresentation[customerNumberIndex]);
                    Customer customer = new Customer(username, password, firstName, lastName, customerNumber);
                    users.add(customer);
                    break;
            }
        }
        return users;
    }

    public void writeUsersToCSV(Set<User> users) {
        CSVHelper helper = new CSVHelper("resources\\users.csv");
        String[] csvToStrings = new String[users.size()];
        int i = 0;
        for (User user : users) {
            csvToStrings[i] = user.makeCSVString();
            i++;
        }
        helper.writeCSV(csvToStrings);
    }
}
