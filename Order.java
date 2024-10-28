import java.time.LocalDate;
import java.util.Scanner;

public class Order {
    // attribute
    private int oId;
    private double totalPrice;
    private LocalDate date;
    private String status;

    // composition
    private Customer customer;
    private DrinksDatabase drinkDb;
    private Drink[] orderedDrinks;
    private Payment payment;
    private Rider rider;

    private Scanner sc;

    public Order(int oId, Customer customer, DrinksDatabase drinkDb, Rider rider) {
        this.oId = oId;
        this.customer = customer;
        this.payment = payment;
        this.rider = rider;
        this.drinkDb = drinkDb;

        date = LocalDate.now();
        totalPrice = 0;
        this.orderedDrinks = new Drink[0];
        this.status = "Brewing";

        this.sc = new Scanner(System.in);
    }

    public void ordering() {
        boolean ordering = true;
        while (ordering == true) {
            drinkDb.showAllDrinks();
            System.out.println(drinkDb.getDrinksCount() + 1 + ". Cancel a drink");
            System.out.println(drinkDb.getDrinksCount() + 2 + ". Enter order");
            System.out.println("Select your drink by number");
            int num = sc.nextInt();

            if (num > 0 && num <= drinkDb.getDrinksCount()) {
                addOrderedDrink(num);
                this.totalPrice = this.totalPrice + orderedDrinks[orderedDrinks.length - 1].getPrice();
            } else if (num == drinkDb.getDrinksCount() + 2) {
                showOrder();
                System.out.println("Total price: " + getTotalPrice());
                ordering = false;
            } else if (num == drinkDb.getDrinksCount() + 1) {
                System.out.println("Select a drink to cancel by number");
                showOrder();
                num = sc.nextInt();
                this.totalPrice = this.totalPrice - orderedDrinks[num - 1].getPrice();
                deleteOrderedDrink(num);
            }

            else {
                System.out.println("Please select number in menu");
            }
        }
    }

    public int getOId() {
        return oId;
    }

    public String getCustomer() {
        return customer.getName() + " " + customer.getPhone();
    }

    public String getPayment() {
        return payment.getBankAccount();
    }

    public String getRider() {
        return rider.getName() + " " + rider.getPhone();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void addOrderedDrink(int num) {
        int dId = num - 1;
        Drink selectedDrink = drinkDb.getADrink(dId);

        if (this.orderedDrinks.length == 0) {
            this.orderedDrinks = new Drink[1];
            this.orderedDrinks[0] = new Drink(orderedDrinks.length, selectedDrink.getDName(), selectedDrink.getPrice());
        } else {
            Drink[] newOrderedDrinks = new Drink[this.orderedDrinks.length + 1];

            for (int i = 0; i < this.orderedDrinks.length; i++) {
                newOrderedDrinks[i] = this.orderedDrinks[i];
            }

            newOrderedDrinks[this.orderedDrinks.length] = new Drink(orderedDrinks.length, selectedDrink.getDName(),
                    selectedDrink.getPrice());
            this.orderedDrinks = newOrderedDrinks;
        }
        
        this.orderedDrinks[this.orderedDrinks.length-1].setTopping();
        this.orderedDrinks[this.orderedDrinks.length-1].setSweetness();
        
    }

    public void deleteOrderedDrink(int num) {
        if (this.orderedDrinks.length == 0) {
            System.out.println("Order is already empty");
        } else if (num < 1 || num > this.orderedDrinks.length) {
            System.out.println("Invalid Input");
        } else {
            Drink[] newOrderedDrinks = new Drink[this.orderedDrinks.length - 1];
            for (int i = 0, k = 0; i < this.orderedDrinks.length; i++) {
                if (i == num - 1) {
                    continue;
                }
                newOrderedDrinks[k++] = this.orderedDrinks[i];
            }
            this.orderedDrinks = newOrderedDrinks;
            System.out.println("Delete Success");
        }
    }

    public void showOrder() {
        System.out.println("Your Order");
        System.out.println("Order ID: " + getOId());
        for (int i = 0; i < orderedDrinks.length; i++) {
            System.out
                    .println(i + 1 + ". " + orderedDrinks[i].getDName() + " Topping: " + orderedDrinks[i].getTopping() +
                            " Sweetness: " + orderedDrinks[i].getSweetness()
                            + " " + orderedDrinks[i].getPrice() + " Baht" + "\n" + getStatus());
        }

    }

}
