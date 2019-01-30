package persistance;

import helper.CSVHelper;
import models.*;

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
            String userType = customerRepresentation[0];
            String username = customerRepresentation[1];
            String password = customerRepresentation[2];
            String firstName = customerRepresentation[3];
            String lastName = customerRepresentation[4];

            switch (userType) {
                case "Admin":
                    Admin admin = new Admin(username, password, firstName, lastName);
                    users.add(admin);
                    break;
                case "Customer":
                    String customerNumber = customerRepresentation[5];
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
            csvToStrings[i] = user.csvString();
            i++;
        }
        helper.writeCSV(csvToStrings);
    }
}
