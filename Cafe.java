import java.util.Scanner;

public class Cafe {
    private boolean authenticate;
    private Customer currentCus;
    private CustomerDatabase cusDb;
    private Order[] orders;
    private DrinksDatabase drinkDb;
    private Payment[] payments;

    private Scanner sc;

    public Cafe() {
        this.authenticate = false;
        this.currentCus = new Customer(0, null, null, null);
        this.cusDb = new CustomerDatabase();
        this.drinkDb = new DrinksDatabase();
        this.orders = new Order[0];
        this.payments = new Payment[3];
        payments[0] = new Payment("1234567890", 1000);
        payments[1] = new Payment("1111111111", 20);
        payments[2] = new Payment("2222222222", 0);

        this.sc = new Scanner(System.in);
    }

    public void run() {
        boolean loop = true;
        while (loop) {
            System.out.println("Welcome to Liu'Cha");
            Scanner sc = new Scanner(System.in);
            System.out.println("Press select your app by number");
            System.out.println("1. Customer");
            System.out.println("2. Owner");
            System.out.println("3. Rider");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    customerLogin();
                    break;

                default:
                    break;
            }
        }

    }

    public void customerLogin() {
        boolean loop = true;
        while (loop) {
            while (!authenticate) {
                authenticateCus();
            }

            System.out.println("Please select number");
            System.out.println("1. Place order");
            System.out.println("2. Canel order");
            System.out.println("3. Check receipt");
            System.out.println("4. Check order status");
            System.out.println("5. Exit");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    if (orders.length == 0) {
                        this.orders = new Order[1];
                        this.orders[this.orders.length - 1] = new Order(this.orders.length, currentCus, this.drinkDb,
                                null);
                        this.orders[this.orders.length - 1].ordering();
                        System.out.println("Enter your bankaccount");
                        String bankAccount = sc.next();
                        for (int i = 0; i < payments.length; i++) {
                            if (bankAccount.equals(payments[i].getBankAccount())) {
                                if (payments[i].paid(this.orders[this.orders.length - 1].getTotalPrice()) == true) {
                                    currentCus.addReceipt(orders[this.orders.length - 1]);
                                    break;
                                } else {
                                    this.orders[this.orders.length - 1].cancelOrder();
                                    break;
                                }
                            }
                            if ((i == payments.length - 1)
                                    && (bankAccount.equals(payments[i].getBankAccount()) == false)) {
                                System.out.println("Invalid account number");
                                this.orders[this.orders.length - 1].cancelOrder();
                            }
                        }
                    } else {
                        Order[] newOrders = new Order[this.orders.length + 1];
                        for (int i = 0; i < this.orders.length; i++) {
                            newOrders[i] = this.orders[i];
                        }
                        newOrders[newOrders.length - 1] = new Order(newOrders.length, currentCus, this.drinkDb, null);
                        this.orders = newOrders;
                        this.orders[this.orders.length - 1].ordering();
                        System.out.println("Enter your bankaccount");
                        String bankAccount = sc.next();
                        for (int i = 0; i < payments.length; i++) {
                            if (bankAccount.equals(payments[i].getBankAccount())) {
                                if (payments[i].paid(this.orders[this.orders.length - 1].getTotalPrice()) == true) {
                                    currentCus.addReceipt(orders[this.orders.length - 1]);
                                    break;
                                } else {
                                    this.orders[this.orders.length - 1].cancelOrder();
                                    break;
                                }
                            }
                            if ((i == payments.length - 1)
                                    && (bankAccount.equals(payments[i].getBankAccount()) == false)) {
                                System.out.println("Invalid account number");
                                this.orders[this.orders.length - 1].cancelOrder();
                            }
                        }
                    }

                    break;

                case 2:
                    System.out.println("Enter order ID");
                    int oId = sc.nextInt();
                    if (oId < 1 || oId > this.orders.length) {
                        System.out.println("Not has that order");
                    } else {
                        this.orders[oId - 1].cancelOrder();
                    }
                    break;

                case 3:
                    currentCus.showCustomerReceipts();

                case 4:
                    System.out.println("Enter order ID");
                    int oIdSelect = sc.nextInt();
                    if (oIdSelect < 1 || oIdSelect > this.orders.length) {
                        System.out.println("Not has that order");
                    } else {
                        System.out.println("Order " + oIdSelect);
                        System.out.println("Status " + this.orders[oIdSelect - 1].getStatus());
                    }

                case 5:
                    loop = false;
                    break;

                default:
                    System.out.println("Wrong input. Please choose number between 1-5");
                    break;

            }

        }
    }

    public boolean authenticateCus() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your phone number");
        String phone = sc.nextLine();
        System.out.println("please enter your password");
        String password = sc.nextLine();
        authenticate = cusDb.authenticate(phone, password);
        if (authenticate == true) {
            this.currentCus = cusDb.getACustomer(phone, password);
            System.out.println("Welcome " + currentCus.getName());
        } else {
            System.out.println("Wrong phone number or password. Please try again");
        }
        return false;
    }

}