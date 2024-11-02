import java.util.Scanner;
import java.time.LocalDate;

public class Cafe {
    private boolean authenticate;
    private int currentCus;
    private Customer[] customers;
    private Rider currentRi;
    private RiderDatabase riDb;
    private Order[] orders;
    private DrinksDatabase drinkDb;
    private Payment[] payments;
    private SaleHistory[] saleHistories;

    private Scanner sc;
    private LocalDate date;

    public Cafe() {
        this.authenticate = false;
        this.currentRi = new Rider(0, null, null, null, null, null);
        this.riDb = new RiderDatabase();
        this.drinkDb = new DrinksDatabase();
        this.orders = new Order[0];
        this.payments = new Payment[3];
        this.saleHistories = new SaleHistory[1];
        this.saleHistories[0] = new SaleHistory(LocalDate.now());

        customers = new Customer[2];
        customers[0] = new Customer(1, "Wichanan", "123456", "0123456789");
        customers[1] = new Customer(2, "Somsak", "1", "1");

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
                    customerApp();
                    break;
                case 2:
                    saleHistories[saleHistories.length-1].showSaleHistory();
                    break;
                case 3:
                    riderApp();
                default:
                    break;
            }
        }

    }

    public void customerApp() {
        boolean loop = true;
        boolean login = false;
        while (loop) {
            while (login == false) {
                login = cusLogin();
            }
            System.out.println("-----------------------");
            System.out.println("Please select number");
            System.out.println("1. Place order");
            System.out.println("2. Canel order");
            System.out.println("3. Check receipt");
            System.out.println("4. Check order status");
            System.out.println("5. Exit");
            System.out.println("-----------------------");
            int num = sc.nextInt();
            sc.nextLine();
            switch (num) {
                case 1:
                    if (orders.length == 0) {
                        this.orders = new Order[1];
                        this.orders[this.orders.length - 1] = new Order(this.orders.length, customers[currentCus], this.drinkDb,
                                null);
                        this.orders[this.orders.length - 1].ordering();
                        System.out.println("Enter your bankaccount");
                        String bankAccount = sc.next();
                        for (int i = 0; i < payments.length; i++) {
                            if (bankAccount.equals(payments[i].getBankAccount())) {
                                if (payments[i].paid(this.orders[this.orders.length - 1].getTotalPrice()) == true) {
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
                        newOrders[newOrders.length - 1] = new Order(newOrders.length, customers[currentCus], this.drinkDb, null);
                        this.orders = newOrders;
                        this.orders[this.orders.length - 1].ordering();
                        System.out.println("Enter your bankaccount");
                        String bankAccount = sc.next();
                        for (int i = 0; i < payments.length; i++) {
                            if (bankAccount.equals(payments[i].getBankAccount())) {
                                if (payments[i].paid(this.orders[this.orders.length - 1].getTotalPrice()) == true) {
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
                    customers[currentCus].showCustomerReceipts();
                    break;
                case 4:
                    System.out.println("Enter order ID");
                    int oIdSelect = sc.nextInt();
                    if (oIdSelect < 1 || oIdSelect > this.orders.length) {
                        System.out.println("Not has that order");
                    } else {
                        System.out.println("Order " + oIdSelect);
                        System.out.println("Status " + this.orders[oIdSelect - 1].getStatus());
                    }
                    break;

                case 5:
                    authenticate = false;
                    loop = false;
                    break;

                default:
                    System.out.println("Wrong input. Please choose number between 1-5");
                    break;

            }

        }
    }

    public boolean cusLogin() {
        System.out.println("Please enter your phone number");
        String phone = sc.nextLine();
        System.out.println("please enter your password");
        String password = sc.nextLine();
        authenticate = authenticateCus(phone, password);
        if (authenticate == true) {
            
            System.out.println("Welcome " + customers[currentCus].getName());
            return true;
        } else {
            System.out.println("Wrong phone number or password. Please try again");
            return false;
        }
    }

    public boolean authenticateCus(String phone, String password) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getPhone().equals(phone)) {
                if (customers[i].getPassword().equals(password)) {
                    currentCus = i;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void riderApp() {
        this.date = LocalDate.now();
        boolean loop = true;
        boolean login = false;
        int select;
        while (loop) {
            while (login == false) {
                login = riLogin();
            }
            int inProgressNum = 0;
            int deliveringNum = 0;
            for (int i = 0; i < orders.length; i++) {
                if (orders[i].getStatus() == "In progress") {
                    inProgressNum++;
                }
                if ((orders[i].getStatus() == "Delivering") && (orders[i].getRider() == currentRi)) {
                    deliveringNum++;
                }
            }
            Order[] inProgressOrders = new Order[inProgressNum];
            Order[] deliveringOrders = new Order[deliveringNum];
            int inCount = 0;
            int deliverCount = 0;
            for (int i = 0; i < orders.length; i++) {
                if (orders[i].getStatus() == "In progress") {
                    inProgressOrders[inCount] = orders[i];
                    inCount++;
                }
                if ((orders[i].getStatus() == "Delivering") && (orders[i].getRider() == currentRi)) {
                    deliveringOrders[deliverCount] = orders[i];
                    deliverCount++;
                }
            }
            System.out.println("------------------");
            System.out.println("Please select number");
            System.out.println("1. Receieve Order");
            System.out.println("2. Deliver Order");
            System.out.println("3. Exit");
            System.out.println("------------------");
            int num = sc.nextInt();
            sc.nextLine();
            switch (num) {
                case 1:

                    for (int i = 0; i < inProgressNum; i++) {
                        inProgressOrders[i].showOrder();
                    }
                    if (inProgressNum == 0) {
                        System.out.println("Not has order");
                        break;
                    }
                    System.out.println("------------------");
                    System.out.println("Select Order ID");
                    select = sc.nextInt();
                    if ((select <= orders.length && (select > 0))) {
                        for (int i = 0; i < orders.length; i++) {
                            if (select == orders[i].getOId()) {
                                orders[i].setRider(currentRi);
                                orders[i].delivering();
                            }
                        }
                    } else {
                        System.out.println("Not has that order.");
                    }
                    break;

                case 2:
                    for (int i = 0; i < deliveringNum; i++) {
                        deliveringOrders[i].showOrder();
                    }
                    if (deliveringNum == 0) {
                        System.out.println("Not has order");
                        break;
                    }
                    System.out.println("------------------");
                    System.out.println("Select Order ID");
                    select = sc.nextInt();
                    if ((select <= orders.length && (select > 0))) {
                        for (int i = 0; i < orders.length; i++) {
                            if (select == orders[i].getOId()) {
                                orders[i].delivered();
                                for(int k=0;i<customers.length;k++){
                                    if(orders[i].getCustomer()==customers[k]){
                                        customers[k].addReceipt(orders[i].getReceipt());
                                        if(saleHistories[saleHistories.length-1].getDate().equals(date)){
                                            saleHistories[saleHistories.length-1].saveReceipt(orders[i].getReceipt());
                                        }
                                        else{
                                            SaleHistory newSaleHistory[] = new SaleHistory[saleHistories.length+1];
                                            for(int j=0;j<saleHistories.length;j++){
                                                newSaleHistory[j] = saleHistories[j];
                                            }
                                            newSaleHistory[saleHistories.length] = new SaleHistory(date);
                                            saleHistories = newSaleHistory;
                                        }
                                        break;
                                    }
                                }
                                currentRi.updateIncome(10);
                                System.out.println(currentRi.getIncome());
                                break;
                            }
                        }
                    } else {
                        System.out.println("Not has that order.");
                    }
                    break;

                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println("Wrong input. Please choose number between 1-3");
                    break;

            }

        }

    }

    public boolean riLogin() {
        System.out.println("Please enter your phone number");
        String phone = sc.nextLine();
        System.out.println("please enter your password");
        String password = sc.nextLine();
        authenticate = riDb.authenticate(phone, password);
        if (authenticate == true) {
            this.currentRi = riDb.getARider(phone, password);
            System.out.println("Welcome " + currentRi.getName());
            return true;
        } else {
            System.out.println("Wrong phone number or password. Please try again");
            return false;
        }
    }

}