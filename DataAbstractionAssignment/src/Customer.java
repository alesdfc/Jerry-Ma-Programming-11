import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits = new ArrayList<>();
    private ArrayList<Withdraw> withdraws = new ArrayList<>();
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    // Requires: none
    // Modifies: none
    // Effects: creates a default customer object
    Customer() {
        this.checkBalance = 0;
        this.savingBalance = 0;
        this.accountNumber = 0;
        this.name = "John Doe";
    }

    // Requires: string, int, double, double
    // Modifies: none
    // Effects: creates a customer object with given information
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit) {

        this.accountNumber = accountNumber;
        this.name = name;

        // Checks if the checkdeposit or the saving deposit is negative, if it is then
        // set it equal to 0
        if (checkDeposit < 0) {
            this.checkBalance = 0;
        } else { // the checkdeposit isn't negative
            this.checkBalance = checkDeposit;
        }

        if (savingDeposit < 0) {
            this.savingBalance = 0;
        } else { // the savingdeposit isn't negative
            this.savingBalance = savingDeposit;
        }
    }

    // Requires: double, date, string
    // Modifies: deposits, checkbalanace/savingbalance
    // Effects: records a deposit made and adds deposited money to chosen account
    public double deposit(double amt, Date date, String account) {
        // Adds a deposit object into the deposits arraylist
        deposits.add(new Deposit(amt, date, account));

        // If the account is checking, put the amount of money into the checking account
        if (account == CHECKING) {
            checkBalance += amt;
            return checkBalance;
        } // If the account is saving, put the amount of money into the saving account
        else if (account == SAVING) {
            savingBalance += amt;
            return savingBalance;
        } else {// There is an invalid input
            return 0;
        }
    }

    // Requires: double, date, string
    // Modifies: withdraws, checkbalance/savingbalance
    // Effects: records a withdraw made and withdraws money from a chosen account
    public double withdraw(double amt, Date date, String account) {

        // Adds a new withdraw object into the withdraws arraylist
        withdraws.add(new Withdraw(amt, date, account));

        // If the account is checking, withdraw the money from the checking account
        if (account.equals(CHECKING)) {

            // If withdrawing the money doesn't put you under overdraft
            if (checkOverdraft(amt, account)) {
                checkBalance -= amt;
                return checkBalance;
            } else {// withdrawing from balance will be less than overdraft
                return 0;
            }
        } // If the account is saving, withdraw the money from the saving account
        else if (account.equals(SAVING)) {

            // Checks overdraft
            if (checkOverdraft(amt, account)) {
                savingBalance -= amt;
                return savingBalance;
            } else {// if you are going over allowed overdraft
                return 0;
            }
        }
        return 0;
    }

    // Requires: double, string
    // Modifies: none
    // Effects: returns true if you aren't overdrafting, returns false otherwise
    private boolean checkOverdraft(double amt, String account) {

        // If the account is checking and won't put you less than overdraft, return true
        if (account == CHECKING && ((checkBalance - amt) >= OVERDRAFT)) {
            return true;
        } // If the account is saving and won't place you less than overdraft, return true
        else if (account == SAVING && (savingBalance - amt) >= OVERDRAFT) {
            return true;
        } else {// Will be less than overdraft
            return false;
        }
    }

    // do not modify
    public void displayDeposits() {
        for (Deposit d : deposits) {
            System.out.println(d);
        }
    }

    // do not modify
    public void displayWithdraws() {
        for (Withdraw w : withdraws) {
            System.out.println(w);
        }
    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public ArrayList<Withdraw> getWithdraws() {
        return withdraws;
    }

    public double getCheckBalance() {
        return checkBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getSavingRate() {
        return savingRate;
    }

    public String getName() {
        return name;
    }
}
