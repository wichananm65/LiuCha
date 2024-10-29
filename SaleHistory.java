import java.time.LocalDate;

public class SaleHistory {
    //attribute
    private int saId;
    private double totalPrice;
    private int receiptCount;
    
    //composition
    private Receipt[] receipts;

    public SaleHistory(LocalDate date) {
        totalPrice = 0;
        this.receipts = new Receipt[10];
        this.receiptCount = 0;
    }

    public int getSaId(){
        return saId;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void saveReceipt(Receipt receipt){
        this.receipts[receiptCount] = receipt;
        receiptCount++;
    }
    
    public void showAllReceipt(){
        for(int i=0;i<receipts.length;i++){
            if(receipts[i] != null){
                receipts[i].showReceipt();
            }
        }
    }
}
