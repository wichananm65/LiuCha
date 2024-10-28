
public class Receipt {
    //attribute
    private int rId;
    private Order order;

    public Receipt(Order order) {
        this.rId = order.getOId();
        this.order = order;
    }

    public int getRId(){
        return rId;
    }

    public Order getOrder(){
        return order;
    }

    public void showReceipt(){
        System.out.println("Receipt ID: " + rId);
        order.showOrder();
    }

}
