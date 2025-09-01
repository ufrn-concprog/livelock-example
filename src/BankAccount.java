public class BankAccount {
    private final String accountId;
    private double balance;
    private boolean inUse;

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.inUse = false;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized boolean withdraw(double amount) {
        if (!inUse && balance >= amount) {
            inUse = true;
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        inUse = false;
    }

    public synchronized boolean isInUse() {
        return inUse;
    }

    // release without modifying balance
    public synchronized void release() {
        inUse = false;
    }
}