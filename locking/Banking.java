package locking;

public class Banking {

    private double balance;

    public Banking(double balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(double amt) {
        System.out.println("Request to withdraw amount : " + amt);
        if(this.balance >= amt) {
            this.balance -= amt;
            System.out.println("Successfully withdrawn amount : " + amt);
        } else {
            System.out.println("Not enought balance");
        }
    }

}
