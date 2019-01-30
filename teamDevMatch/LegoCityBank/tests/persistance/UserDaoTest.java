package persistance;

import models.Customer;
import models.CustomerManager;
import models.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void getInstance() {
        //todo this down here belongs to customerManagerTest
        CustomerManager customerManager = CustomerManager.getInstance();
        Customer customer = customerManager.getCustomerByUserName("elias-lammes");
        System.out.println(customer.toString());
    }
}