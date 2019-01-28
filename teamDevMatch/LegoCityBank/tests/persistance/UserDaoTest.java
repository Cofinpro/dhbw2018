package persistance;

import models.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void getInstance() {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getCustomerByUserName("elias-lammes");
        System.out.println(user);
    }
}