// Payment.java
public class Payment {
    private String bankAccount;
    private double money;

    public Payment(String bankAccount, double money) {
        this.bankAccount = bankAccount;
        this.money = money;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public double getMoney() {
        return money;
    }

    public boolean paid(double price) {
        if (this.money < price) {
            System.out.println("Insufficient balance");
            System.out.println("----------------------------------------");
            return false;
        } else {
            System.out.println("Successfully paid");
            System.out.println("----------------------------------------");
            this.money = this.money - price;
            return true;
        }
    }
}
