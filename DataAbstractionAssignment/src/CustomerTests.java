import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CustomerTests {
    Customer customer;

    @Before
    public void setup() {
        customer = new Customer();
    }

    // Tests for some deposits
    @Test
    public void testDeposit() {

        // Tests the date for a checking account (Primarily)
        Date date = new Date();
        customer.deposit(400, date, "Checking");
        assertEquals(customer.getDeposits().size(), 1);
        assertEquals(customer.getDeposits().get(0).getDate(), date);

        // Tests the addition of a deposit and a previous deposit for a checking account
        customer.deposit(500, new Date(), "Checking");
        assertEquals(customer.getDeposits().size(), 2);
        assertEquals(customer.getCheckBalance(), 900, 0.01);

        // Tests the date for a saving account (Primarily)
        date = new Date();
        customer.deposit(420, date, "Saving");
        assertEquals(customer.getDeposits().size(), 3);
        assertEquals(customer.getDeposits().get(2).getDate(), date);

        // Tests the addition of a deposit and a previous deposit for a saving account
        customer.deposit(69, new Date(), "Saving");
        assertEquals(customer.getDeposits().size(), 4);
        assertEquals(customer.getSavingBalance(), 489, 0.01);
    }

    // Tests for some withdraws
    @Test
    public void testWithdraw() {

        // Makes sure that we have some money to begin with using deposit
        customer.deposit(500, new Date(), "Checking");
        assertEquals(customer.getDeposits().size(), 1);

        // Checks the date and tests the withdrawal of money being correct
        Date date = new Date();
        customer.withdraw(500, date, "Checking");
        assertEquals(customer.getWithdraws().size(), 1);
        assertEquals(customer.getCheckBalance(), 0, 0.01);
        assertEquals(customer.getWithdraws().get(0).getDate(), date);

        // Tests for a situation where you go over the allowed overdraft
        customer.withdraw(74, new Date(), "Checking");
        assertEquals(customer.getCheckBalance(), -74, 0.01);
        customer.withdraw(74, new Date(), "Checking");
        assertEquals(customer.getCheckBalance(), -74, 0.01);

        // Tests withdrawal for a saving account
        customer.deposit(340, new Date(), "Saving");
        customer.withdraw(104, new Date(), "Saving");
        assertEquals(customer.getSavingBalance(), 236, 0.01);

        // Tests a situation for over the allowed overdraft in a saving account
        customer.withdraw(336, new Date(), "Saving");
        assertEquals(-100, customer.getSavingBalance(), 0.01);
        customer.withdraw(13400104, new Date(), "Saving");
        assertEquals(customer.getSavingBalance(), -100, 0.01);
    }
}
