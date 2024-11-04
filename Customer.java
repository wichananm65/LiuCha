
public class Customer extends Person {
    // attribute
    private String phone;
    private int points;

    // composition
    private Receipt receipts[];

    public Customer(int id, String name, String password, String phone) {
        super(id, name, password);
        this.phone = phone;
        receipts = new Receipt[0];
    }

    public String getPhone() {
        return phone;
    }

    public int getPoints() {
        return points;
    }

    public void showCustomerReceipts() {
        if (receipts.length > 0) {
            for (int i = 0; i < receipts.length; i++) {
                receipts[i].showReceipt();
            }
        } else {
            System.out.println("Not has receipt");
        }

    }

    public void addReceipt(Receipt receipt) {
        if (this.receipts.length == 0) {
            this.receipts = new Receipt[1];
            this.receipts[0] = receipt;
        } else {
            Receipt[] newReceipts = new Receipt[this.receipts.length + 1];

            for (int i = 0; i < this.receipts.length; i++) {
                newReceipts[i] = this.receipts[i];
            }

            newReceipts[this.receipts.length] = receipt;
            this.receipts = newReceipts;
        }
    }

    public void addPoint(int num) {
        this.points = this.points + num;
    }

    public void usePoint() {
        this.points = this.points - 10;
    }
}
