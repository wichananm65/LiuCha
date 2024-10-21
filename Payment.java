// Payment.java
public class Payment {
    private int pId;
    private String bankAccount;
    private double money;

    public Payment(int pId, String bankAccount, double amount) {
        this.pId = pId;
        this.bankAccount = bankAccount;
        this.money = money;
    }

    public int getPId(){
        return pId;
    }

    public String getBankAccount(){
        return bankAccount;
    }

    public double getMoney() {
        return money;
    }
}
