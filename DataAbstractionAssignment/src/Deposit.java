import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    // Requires: double, date, string
    // Modifies: none
    // Effects: creates a deposit object
    Deposit(double amount, Date date, String account) {
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    // Requires: none
    // Modifies: none
    // Effects: returns a string containing information about a deposit
    public String toString() {
        return "Deposit of: $" + amount + " Date: " + date + " into account: " + account;
    }

    // Getters
    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getAccount() {
        return account;
    }
}