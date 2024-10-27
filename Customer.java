import java.util.Scanner;
public class Customer extends Person{
    //attribute
    private String phone;
    private int points;

    //composition
    private Payment payments[];
    private Receipt receipts[];

    public Customer(int id, String name, String password, String phone) {
        super(id, name, password);
        this.phone = phone;
        payments = new Payment[0];
        receipts = new Receipt[0];
    }

    public  String getPhone() {
        return phone;
    }

    public String showAllPayment(){
        String text = "";
        for(int i=0;i<payments.length;i++){
            text = text + i + "." + payments[i].getBankAccount() + "\n";
        }
        return text;
    }

    public String showAllReceipt(){
        String text = "";
        for(int i=0;i<receipts.length;i++){
            text = text + receipts[i].showReceipt() + "\n";
        }
        return text;
    }

    public void addPayment(Payment latestPayment) {
        Payment newPayment[] = new Payment[this.payments.length+1];  
        for(int i=0;i<this.payments.length;i++){
            newPayment[i] = this.payments[i];
        }
        newPayment[this.payments.length] = latestPayment;
        this.payments = newPayment;
    }

    public void addReceipt(Receipt latestReceipt) {
        Receipt newReceipt[] = new Receipt[this.receipts.length+1];  
        for(int i=0;i<this.receipts.length;i++){
            newReceipt[i] = this.receipts[i];
        }
        newReceipt[this.receipts.length] = latestReceipt;
        this.receipts = newReceipt;
    }
}
