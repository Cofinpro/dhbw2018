package daos;

import models.Customer;
import models.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class UserDao {
    private static UserDao ourInstance = new UserDao();
    public static UserDao getInstance() {
        return ourInstance;
    }

    private Set<User> users;

    private UserDao() {
        readUsersFromCSV();
    }

    public User getUserByUserName(String userName) {
        for (User user : users) {
            if (user.getFirstName().equals(userName)) {
                return user;
            }
        }
        throw new
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
            //the first line just contains the headings so we don't need it
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                String password = parts[3];
                User user = new Customer();
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
