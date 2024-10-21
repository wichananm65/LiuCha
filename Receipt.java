
public class Receipt {
    //attribute
    private int rId;
    private String detail;

    public Receipt(int rId, String detail) {
        this.rId = rId;
        this.detail = detail;
    }

    public String showReceipt(){
        return "Receipt Number: " + rId + "\n" + detail;
    }

}
