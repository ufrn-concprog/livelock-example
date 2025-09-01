public class LivelockExample {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount("Account-A", 1000);
        BankAccount accountB = new BankAccount("Account-B", 1000);

        Thread t1 = new Thread(() -> transfer(accountA, accountB, 100), "Transaction-1");
        Thread t2 = new Thread(() -> transfer(accountB, accountA, 50), "Transaction-2");

        t1.start();
        t2.start();
    }

    private static void transfer(BankAccount from, BankAccount to, double amount) {
        while (true) {
            try {
                if (from.withdraw(amount)) {
                    System.out.printf("[%s] reserved %.2f from %s\n",
                            Thread.currentThread().getName(), amount, from.getAccountId());
                    if (to.isInUse()) {
                        System.out.printf("[%s] target %s is busy; rolling back and retrying\n",
                                Thread.currentThread().getName(), to.getAccountId());
                        from.deposit(amount);
                    } else {
                        // To avoid partial transfer, release and retry the transaction
                        from.release();
                        System.out.printf("[%s] restart transaction to avoid conflict\n",
                                Thread.currentThread().getName());
                    }

                    Thread.sleep(100);      // slow down for visibility
                } else {
                    Thread.sleep(100);      // wait before retry
                }
            } catch (InterruptedException e) {
                System.err.printf("[%s] is interrupted\n", Thread.currentThread().getName());
            }
        }
    }
}