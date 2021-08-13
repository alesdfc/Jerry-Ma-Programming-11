import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    // Constructor for a deposit object
    Deposit(double amount, Date date, String account) {
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    // Method to return the record of a transaction
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