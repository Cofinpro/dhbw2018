package persistance;

import models.Customer;
import models.CustomerManager;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void getInstance() {
        //todo this down here belongs to customerManagerTest
        CustomerManager customerManager = CustomerManager.getInstance();
        Customer customer = (Customer)customerManager.getUserByUserName("elias-lammes");
        System.out.println(customer.toString());
    }
}