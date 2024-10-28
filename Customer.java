
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

    public void showCustomerReceipts(){
        for(int i=0;i<receipts.length;i++){
            receipts[i].showReceipt();
        }
    }

    public void addPayment(Payment latestPayment) {
        Payment newPayment[] = new Payment[this.payments.length+1];  
        for(int i=0;i<this.payments.length;i++){
            newPayment[i] = this.payments[i];
        }
        newPayment[this.payments.length] = latestPayment;
        this.payments = newPayment;
    }

    public void addReceipt(Order order){
        if (this.receipts.length == 0) {
            this.receipts = new Receipt[1];
            this.receipts[0] = new Receipt(order);
        } else {
            Receipt[] newReceipts = new Receipt[this.receipts.length + 1];

            for (int i = 0; i < this.receipts.length; i++) {
                newReceipts[i] = this.receipts[i];
            }

            newReceipts[this.receipts.length] = new Receipt(order);
            this.receipts = newReceipts;
        }
    }
}
