package daos;

import exceptions.UserNotFoundException;
import models.Customer;
import models.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UserDao {
    private static UserDao ourInstance = new UserDao();
    public static UserDao getInstance() {
        return ourInstance;
    }

    private List<User> users;

    private UserDao() {
        users = new ArrayList<>();
        readUsersFromCSV();
    }

    public User getUserByUserName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        throw new UserNotFoundException(userName);
    }

    private void readUsersFromCSV() {
        readCustomersFromCSV();
    }

    private void readCustomersFromCSV() {
        String path = "resources\\customers.csv";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                String password = parts[3];
                String customerNumber = parts[4];
                User user = new Customer(username, password, firstName, lastName, customerNumber);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
