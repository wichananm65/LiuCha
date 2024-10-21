import java.time.LocalDate;

public class Order {
    //attribute
    private int oId;
    private double totalPrice;
    private LocalDate date;

    //composition
    private Customer customer;
    private Drink[] drink;
    private Payment payment;
    private Rider rider;

    public Order(int oId, Customer customer, Drink[] drink, Payment payment, Rider rider){
        this.oId = oId;
        this.customer = customer;
        this.payment = payment;
        this.rider = rider;
        this.drink = drink;

        date = LocalDate.now();
        totalPrice = 0;
    }

    public int getOId(){
        return oId;
    }

    public String getCustomer(){
        return customer.getName() + " " + customer.getPhone();
    }

    public String showAllDrinks(){
        String text = "";
        for(int i=0;i<drink.length;i++){
            text = text + i + "." + drink[i].showOrderedDrink() + "\n";
        }
        return text;
    }

    public String getPayment(){
        return payment.getBankAccount();
    }

    public String getRider(){
        return rider.getName() + " " + rider.getPhone();
    }

    public String showOrder(){
        return "Order Number: " + oId;
    }

}
