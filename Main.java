import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create 5 Customers
        Customer[] customers = new Customer[5];
        for (int i = 0; i < 5; i++) {
            customers[i] = new Customer(i + 1, "Customer" + (i + 1), "pass" + (i + 1), "123-456-789" + i);
        }

        // Create 5 Payments and add to each Customer
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Payment payment = new Payment(j + 1, "BankAccount" + (j + 1), 100 + j * 10);
                customers[i].addPayment(payment);
            }
        }

        // Create 5 Receipts and add to each Customer
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Receipt receipt = new Receipt(j + 1, "Receipt detail for Customer" + (i + 1));
                customers[i].addReceipt(receipt);
            }
        }

        // Show customer details
        for (Customer customer : customers) {
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("Payments:");
            System.out.println(customer.showAllPayment());
            System.out.println("Receipts:");
            System.out.println(customer.showAllReceipt());
            System.out.println("------------------------------");
        }

        // Create 5 Drinks
        Drink[] drinks = new Drink[5];
        for (int i = 0; i < 5; i++) {
            drinks[i] = new Drink(i + 1, "Drink" + (i + 1), 50 + i * 5);
        }

        // Create 5 Riders
        Rider[] riders = new Rider[5];
        for (int i = 0; i < 5; i++) {
            riders[i] = new Rider(i + 1, "Rider" + (i + 1), "riderpass" + (i + 1), "123-456-78" + i, "Bike", "ABC" + i);
        }

        // Create 5 Orders
        Order[] orders = new Order[5];
        for (int i = 0; i < 5; i++) {
            orders[i] = new Order(i + 1, customers[i], drinks, new Payment(i + 1, "BankAccount" + (i + 1), 100 + i * 10), riders[i]);
        }

        // Show orders details
        for (Order order : orders) {
            System.out.println(order.showOrder());
            System.out.println("Customer: " + order.getCustomer());
            System.out.println("Rider: " + order.getRider());
            System.out.println("Drinks:");
            System.out.println(order.showAllDrinks());
            System.out.println("------------------------------");
        }

        // Create 5 Owners
        Owner[] owners = new Owner[5];
        for (int i = 0; i < 5; i++) {
            owners[i] = new Owner(i + 1, "Owner" + (i + 1), "ownerpass", drinks);
        }

        // Show owner details
        for (Owner owner : owners) {
            System.out.println("Owner Name: " + owner.getName());
            System.out.println("Drinks Managed:");
            System.out.println(owner.showAllDrinks());
            System.out.println("------------------------------");
        }

        // Create 5 SaleHistories
        SaleHistory[] saleHistories = new SaleHistory[5];
        for (int i = 0; i < 5; i++) {
            saleHistories[i] = new SaleHistory(LocalDate.now());
        }

        // Show SaleHistory details
        for (SaleHistory saleHistory : saleHistories) {
            System.out.println("Sale History ID: ");
            System.out.println("Date: " + saleHistory.getDate());
            System.out.println("------------------------------");
        }
    }
}
