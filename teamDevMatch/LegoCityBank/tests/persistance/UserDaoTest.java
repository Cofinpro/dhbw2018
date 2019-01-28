package persistance;

import models.CustomerManager;
import models.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void getInstance() {
        UserDao userDao = UserDao.getInstance();
        CustomerManager customerManager = CustomerManager.getInstance();
        User user = customerManager.getCustomerByUserName("elias-lammes");
        System.out.println(user);
    }
}