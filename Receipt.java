
public class Receipt {
    private int rId;
    private String detail;
    private double totalPrice;

    public Receipt(int rId, String detail, double totalPrice) {
        this.rId = rId;
        this.detail = detail;
        this.totalPrice = totalPrice;
    }

    public int getRId() {
        return rId;
    }

    public String getDetail() {
        return detail;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void showReceipt() {
        System.out.println("---------------------------------------");
        System.out.println("Receipt ID: " + rId);
        System.out.println(detail);
        System.out.println("---------------------------------------");
    }

}
