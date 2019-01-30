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

    public Set<Customer> readCustomersFromCSV() {
        Set<Customer> customers = new TreeSet<>();
        CSVHelper helper = new CSVHelper("resources\\customers.csv");
        Collection<String[]> customerRepresentations = helper.readCSV();

        for (String[] customerRepresentation : customerRepresentations) {
            String username = customerRepresentation[0];
            String password = customerRepresentation[1];
            String firstName = customerRepresentation[2];
            String lastName = customerRepresentation[3];
            String customerNumber = customerRepresentation[4];
            Customer customer = new Customer(username, password, firstName, lastName, customerNumber);
            customers.add(customer);
        }
        return customers;
    }

    public void writeCustomersToCSV(Set<Customer> customers) {
        CSVHelper helper = new CSVHelper("resources\\customers.csv");
        String[] csvToStrings = new String[customers.size()];
        int i = 0;
        for (Customer customer : customers) {
            csvToStrings[i] = customer.csvString();
            i++;
        }
        helper.writeCSV(csvToStrings);
    }
}
