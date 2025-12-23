package T3;

public class BankAccount {
    private String owner;
    private int balance;

    public BankAccount(String owner, int balance) {
        this.owner = owner;

        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            return;
        }

        this.balance += amount;
    }

    public boolean withdraw(int amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }

        return false;
    }

    public void printBalance() {
        System.out.println(owner + "'s balance is " + this.balance);
    }
}
