import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    // Constructor for a withdraw object
    Withdraw(double amount, Date date, String account) {
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    // Method to return the record of a transaction
    public String toString() {
        return "Withdrawl of: $" + amount + " Date: " + date + " from account: " + account;
    }

    // Getter
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
