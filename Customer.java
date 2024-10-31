
public class Customer extends Person{
    //attribute
    private String phone;
    private int points;

    //composition
    private Receipt receipts[];

    public Customer(int id, String name, String password, String phone) {
        super(id, name, password);
        this.phone = phone;
        receipts = new Receipt[0];
    }

    public  String getPhone() {
        return phone;
    }

    public void showCustomerReceipts(){
        for(int i=0;i<receipts.length;i++){
            receipts[i].showReceipt();
        }
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
