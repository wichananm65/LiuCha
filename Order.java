import java.time.LocalDate;

public class Order {
    //attribute
    private int oId;
    private double totalPrice;
    private LocalDate date;

    //composition
    private Customer customer;
    private DrinksDatabase drinkDb;
    private Drink[] orderedDrinks;
    private Payment payment;
    private Rider rider;

    public Order(int oId, Customer customer, DrinksDatabase drinkDb, Rider rider){
        this.oId = oId;
        this.customer = customer;
        this.payment = payment;
        this.rider = rider;
        this.drinkDb = drinkDb;

        date = LocalDate.now();
        totalPrice = 0;
        this.orderedDrinks = new Drink[0];
    }

    public void run(){
        drinkDb.showAllDrinks();        
        System.out.println(drinkDb.getDrinksCount() + 1 + ". Cancel a drink");
        System.out.println(drinkDb.getDrinksCount() + 2 + ". Enter order");
        System.out.println("---Please select your drink---");
        boolean ordering = true;
        while(ordering==true){
            int num = customer.placeOrder();
            
            if(num>0&&num<=drinkDb.getDrinksCount()){
                addOrderedDrink(num);
                this.totalPrice = this.totalPrice + orderedDrinks[orderedDrinks.length-1].getPrice();
            }
            else if(num==drinkDb.getDrinksCount()+2){
                showOrderedDrinks();
                System.out.println("Total price: " + getTotalPrice());
                ordering = false;
            }
            else if(num==drinkDb.getDrinksCount()+1){
                ordering = false;
            }
            
            else{
                System.out.println("Please select number in menu");
            }
        }
    }

    public int getOId(){
        return oId;
    }

    public String getCustomer(){
        return customer.getName() + " " + customer.getPhone();
    }

    public String getPayment(){
        return payment.getBankAccount();
    }

    public String getRider(){
        return rider.getName() + " " + rider.getPhone();
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public String showOrder(){
        return "Order Number: " + oId;
    }

    public void addOrderedDrink(int num){
        int dId = num-1;
        if(this.orderedDrinks.length == 0){
            this.orderedDrinks = new Drink[1];
            this.orderedDrinks[0] = this.drinkDb.getADrink(dId);
        }
        else{
            Drink newOrderedDrinks[] = new Drink[this.orderedDrinks.length+1];  
            for(int i=0;i<this.orderedDrinks.length;i++){
            newOrderedDrinks[i] = this.orderedDrinks[i];
            }
            newOrderedDrinks[this.orderedDrinks.length] = drinkDb.getADrink(dId);
            this.orderedDrinks = newOrderedDrinks;
        }
    }

    public void showOrderedDrinks(){
        for(int i=0;i<orderedDrinks.length;i++){
            System.out.println(orderedDrinks[i].getDName() + " " + orderedDrinks[i].getTopping() + " " + orderedDrinks[i].getSweetness()
            + " " + orderedDrinks[i].getPrice() + " Baht");
        }
        
    }

}
