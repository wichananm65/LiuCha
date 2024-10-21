import java.time.LocalDate;

public class SaleHistory {
    //attribute
    private int saId;
    private LocalDate date;
    private double totalPrice;
    
    //composition
    private Receipt[] receipts;

    public SaleHistory(LocalDate date) {
        this.date = date;
        totalPrice = 0;
    }

    public int getSaId(){
        return saId;
    }
    public String getDate(){
        return date.toString();
    }

    public double getTotalPrice(){
        return totalPrice;
    }
    
    public String showAllReceipt(){
        String text = "";
        for(int i=0;i<receipts.length;i++){
            text = text + receipts[i].showReceipt() + "\n";
        }
        return text;
    }

    public String showSaleHistory(){
        return "Date " + date + " Total Sale: " + totalPrice + " Baht\n" + showAllReceipt();
    }
}
