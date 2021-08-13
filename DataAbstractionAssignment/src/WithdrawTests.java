import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WithdrawTests {
    Withdraw withdraw;

    // Tests the returning string of the toString withdraw method and makes sure it
    // matches up with what we expect
    @Test
    public void testToString() {

        // Test 1 that includes a simple date
        withdraw = new Withdraw(400.0, new Date(1541289600000L), Customer.CHECKING);
        assertEquals(withdraw.toString(),
                "Withdrawl of: $400.0 Date: Sat Nov 03 17:00:00 PDT 2018 from account: Checking");

        // Test 2 that includes a more complicated date
        withdraw = new Withdraw(500.0, new Date(1534416737000L), Customer.SAVING);
        assertEquals(withdraw.toString(),
                "Withdrawl of: $500.0 Date: Thu Aug 16 03:52:17 PDT 2018 from account: Saving");
    }
}
