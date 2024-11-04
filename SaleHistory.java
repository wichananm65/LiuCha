import java.time.LocalDate;

public class SaleHistory {
    // attribute
    private int saId;
    private double sale;
    private LocalDate date;

    // composition
    private Receipt[] receipts;

    public SaleHistory(LocalDate date) {
        sale = 0;
        this.receipts = new Receipt[10];
        this.date = LocalDate.now();
    }

    public int getSaId() {
        return saId;
    }

    public double getSale() {
        return sale;
    }

    public LocalDate getDate() {
        return date;
    }

    public void saveReceipt(Receipt receipt) {
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
        this.sale = this.sale + receipt.getTotalPrice();
    }

    public void showSaleHistory() {
        System.out.println("--------------------");
        System.out.println("Sales of " + date);
        for (int i = 0; i < receipts.length; i++) {
            if (receipts[i] != null) {
                receipts[i].showReceipt();
            }
        }
        System.out.println("Total sale is " + sale);
        System.out.println("--------------------");
    }
}
