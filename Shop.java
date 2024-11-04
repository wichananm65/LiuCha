import java.util.Scanner;
import java.time.LocalDate;

public class Shop {
    private boolean authenticate;
    private int currentCusId;
    private Customer[] customers;
    private int currentRiId;
    private Rider[] riders;
    private Order[] orders;
    private DrinksDatabase drinkDb;
    private Payment[] payments;
    private SaleHistory[] saleHistories;
    private Owner owner;

    private Scanner sc;

    public Shop() {
        this.authenticate = false;
        this.drinkDb = new DrinksDatabase();
        this.orders = new Order[0];
        this.payments = new Payment[3];
        this.saleHistories = new SaleHistory[1];
        this.saleHistories[0] = new SaleHistory(LocalDate.now());
        this.owner = new Owner(100000, "Liu", "123456");
        riders = new Rider[2];
        riders[0] = new Rider(1, "Sonchai", "123", "1", "Honda Icon", "AB123");
        riders[1] = new Rider(2, "Somying", "123", "2", "Honda Wave", "BD222");

        customers = new Customer[2];
        customers[0] = new Customer(1, "Wichanan", "123456", "0123456789");
        customers[1] = new Customer(2, "Somsak", "1", "1");
        customers[1].addPoint(10);

        payments[0] = new Payment("1234567890", 1000);
        payments[1] = new Payment("1111111111", 20);
        payments[2] = new Payment("2222222222", 0);

        this.sc = new Scanner(System.in);
    }

    public void run() {
        boolean loop = true;
        while (loop) {
            System.out.println("-------------------");
            System.out.println("Welcome to Liu'Cha|");
            System.out.println("--------------------------------");
            System.out.println("Press select your app by number|");
            System.out.println("1. Customer                    |");
            System.out.println("2. Owner                       |");
            System.out.println("3. Rider                       |");
            System.out.println("--------------------------------");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    customerApp();
                    break;
                case 2:
                    ownerApp();
                    break;
                case 3:
                    riderApp();
                default:
                    break;
            }
        }

    }

    public void customerApp() {

        System.out.println("---------------------");
        System.out.println("Please enter number |");
        System.out.println("1.Login             |");
        System.out.println("2.Register          |");
        System.out.println("---------------------");
        int selectNum = sc.nextInt();
        sc.nextLine();
        switch (selectNum) {
            case 1:
                boolean loop = true;
                boolean login = false;
                while (loop) {
                    while (login == false) {
                        login = cusLogin();
                        if (login == false) {
                            return;
                        }
                    }
                    System.out.println("----------------------");
                    System.out.println("Please select number |");
                    System.out.println("1. Place order       |");
                    System.out.println("2. Canel order       |");
                    System.out.println("3. Check receipt     |");
                    System.out.println("4. Check order status|");
                    System.out.println("5. Use points        |");
                    System.out.println("6. Exit              |");
                    System.out.println("----------------------");
                    int num = sc.nextInt();
                    sc.nextLine();
                    switch (num) {
                        case 1:
                            if (orders.length == 0) {
                                this.orders = new Order[1];
                                this.orders[this.orders.length - 1] = new Order(this.orders.length,
                                        customers[currentCusId],
                                        this.drinkDb,
                                        null);
                                this.orders[this.orders.length - 1].ordering();
                                System.out.println("----------------------------------------");
                                System.out.println("Enter your bankaccount");
                                String bankAccount = sc.next();
                                for (int i = 0; i < payments.length; i++) {
                                    if (bankAccount.equals(payments[i].getBankAccount())) {
                                        if (payments[i]
                                                .paid(this.orders[this.orders.length - 1].getTotalPrice()) == true) {
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
                                newOrders[newOrders.length - 1] = new Order(newOrders.length, customers[currentCusId],
                                        this.drinkDb, null);
                                this.orders = newOrders;
                                this.orders[this.orders.length - 1].ordering();
                                System.out.println("Enter your bankaccount");
                                String bankAccount = sc.next();
                                for (int i = 0; i < payments.length; i++) {
                                    if (bankAccount.equals(payments[i].getBankAccount())) {
                                        if (payments[i]
                                                .paid(this.orders[this.orders.length - 1].getTotalPrice()) == true) {
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
                            customers[currentCusId].showCustomerReceipts();
                            break;
                        case 4:
                            System.out.println("Enter order ID");
                            int oIdSelect = sc.nextInt();
                            if (oIdSelect < 1 || oIdSelect > this.orders.length) {
                                System.out.println("Not has that order");
                            } else {
                                System.out.println("Order " + oIdSelect);
                                this.orders[oIdSelect - 1].showOrder();
                                System.out.println("Status: " + this.orders[oIdSelect - 1].getStatus());
                            }
                            break;

                        case 5:
                            System.out.println("You have " + customers[currentCusId].getPoints() + " points");
                            if (customers[currentCusId].getPoints() >= 10) {
                                customers[currentCusId].usePoint();
                                if (orders.length == 0) {
                                    this.orders = new Order[1];
                                    this.orders[this.orders.length - 1] = new Order(this.orders.length,
                                            customers[currentCusId],
                                            this.drinkDb,
                                            null);
                                    this.orders[this.orders.length - 1].ordering(true);
                                } else {
                                    Order[] newOrders = new Order[this.orders.length + 1];
                                    for (int i = 0; i < this.orders.length; i++) {
                                        newOrders[i] = this.orders[i];
                                    }
                                    newOrders[newOrders.length - 1] = new Order(newOrders.length,
                                            customers[currentCusId],
                                            this.drinkDb, null);
                                    this.orders = newOrders;
                                    this.orders[this.orders.length - 1].ordering(true);
                                }
                            } else {
                                System.out.println("You have not enough points");
                            }
                            break;

                        case 6:
                            authenticate = false;
                            loop = false;
                            break;

                        default:
                            System.out.println("Wrong input. Please choose number between 1-6");
                            break;

                    }

                }

                break;
            case 2:
                registerCus();
                break;

            default:
                System.out.println("Please enter 1 or 2");
                break;
        }

    }

    public boolean cusLogin() {
        System.out.println("----------------------------");
        String phone = fillPhoneNum();
        System.out.println("please enter your password |");
        String password = sc.nextLine();
        authenticate = authenticateCus(phone, password);
        if (authenticate == true) {
            System.out.println("----------------------------");
            System.out.println("Welcome " + customers[currentCusId].getName());
            return true;
        } else {
            System.out.println("--------------------------------------------------");
            System.out.println("Wrong phone number or password. Please try again |");
            System.out.println("--------------------------------------------------");
            return false;
        }
    }

    public boolean authenticateCus(String phone, String password) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getPhone().equals(phone)) {
                if (customers[i].getPassword().equals(password)) {
                    currentCusId = i;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void riderApp() {
        boolean loop = true;
        boolean login = false;
        int select;
        while (loop) {
            while (login == false) {
                login = riLogin();
                if (login == false) {
                    return;
                }
            }
            int inProgressNum = 0;
            int deliveringNum = 0;
            for (int i = 0; i < orders.length; i++) {
                if (orders[i].getStatus() == "In progress") {
                    inProgressNum++;
                }
                if ((orders[i].getStatus() == "Delivering") && (orders[i].getRider() == riders[currentRiId])) {
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
                if ((orders[i].getStatus() == "Delivering") && (orders[i].getRider() == riders[currentRiId])) {
                    deliveringOrders[deliverCount] = orders[i];
                    deliverCount++;
                }
            }
            System.out.println("----------------------");
            System.out.println("Please select number |");
            System.out.println("1. Receieve Order    |");
            System.out.println("2. Deliver Order     |");
            System.out.println("3. Exit              |");
            System.out.println("----------------------");
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
                    System.out.println("----------------------------------------");
                    System.out.println("Select Order ID");
                    select = sc.nextInt();
                    if ((select <= orders.length && (select > 0))) {
                        for (int i = 0; i < orders.length; i++) {
                            if (select == orders[i].getOId()) {
                                orders[i].setRider(riders[currentRiId]);
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
                    System.out.println("----------------------------------------");
                    System.out.println("Select Order ID");
                    select = sc.nextInt();
                    riders[currentRiId].updateIncome(10);
                    if ((select <= orders.length && (select > 0))) {
                        for (int i = 0; i < orders.length; i++) {
                            if (select == orders[i].getOId()) {
                                orders[i].delivered();
                                owner.updateIncome(orders[i].getReceipt().getTotalPrice() - 10);
                                for (int k = 0; i < customers.length; k++) {
                                    if (orders[i].getCustomer() == customers[k]) {
                                        customers[k].addReceipt(orders[i].getReceipt());
                                        customers[k].addPoint(orders[i].getOrderedDrinks().length);
                                        if (saleHistories[saleHistories.length - 1].getDate().equals(LocalDate.now())) {
                                            saleHistories[saleHistories.length - 1].saveReceipt(orders[i].getReceipt());
                                        } else {
                                            SaleHistory newSaleHistory[] = new SaleHistory[saleHistories.length + 1];
                                            for (int j = 0; j < saleHistories.length; j++) {
                                                newSaleHistory[j] = saleHistories[j];
                                            }
                                            newSaleHistory[saleHistories.length] = new SaleHistory(LocalDate.now());
                                            saleHistories = newSaleHistory;
                                            saleHistories[saleHistories.length - 1]
                                                    .saveReceipt(this.orders[this.orders.length - 1].getReceipt());
                                        }
                                        break;
                                    }
                                }
                                riders[currentRiId].updateIncome(10);
                                System.out.println(riders[currentRiId].getIncome());
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
        sc.nextLine();
        String phone = fillPhoneNum();
        System.out.println("please enter your password");
        String password = sc.nextLine();
        authenticate = authenticateRi(phone, password);
        if (authenticate == true) {

            System.out.println("Welcome " + riders[currentRiId].getName());
            return true;
        } else {
            System.out.println("Wrong phone number or password. Please try again");
            return false;
        }
    }

    public boolean authenticateRi(String phone, String password) {
        for (int i = 0; i < riders.length; i++) {
            if (riders[i].getPhone().equals(phone)) {
                if (riders[i].getPassword().equals(password)) {
                    currentRiId = i;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void ownerApp() {
        boolean loop = true;
        boolean login = false;
        while (loop) {
            while (login == false) {
                login = owLogin();
                if (login == false) {
                    return;
                }
            }

            System.out.println("----------------------");
            System.out.println("Please select number |");
            System.out.println("1. Sell              |");
            System.out.println("2. Add drink         |");
            System.out.println("3. Edit drink        |");
            System.out.println("4. Delete drink      |");
            System.out.println("5. Check sale history|");
            System.out.println("6. Check income      |");
            System.out.println("7. Exit              |");
            System.out.println("----------------------");
            int num = sc.nextInt();
            sc.nextLine();
            switch (num) {
                case 1:
                    if (orders.length == 0) {
                        this.orders = new Order[1];
                        this.orders[this.orders.length - 1] = new Order(this.orders.length, null,
                                this.drinkDb,
                                null);
                        this.orders[this.orders.length - 1].ordering();

                    } else {
                        Order[] newOrders = new Order[this.orders.length + 1];
                        for (int i = 0; i < this.orders.length; i++) {
                            newOrders[i] = this.orders[i];
                        }
                        newOrders[newOrders.length - 1] = new Order(newOrders.length, null,
                                this.drinkDb, null);
                        this.orders = newOrders;
                        this.orders[this.orders.length - 1].ordering();
                    }
                    this.orders[this.orders.length - 1].delivered();
                    owner.updateIncome(this.orders[this.orders.length - 1].getReceipt().getTotalPrice());
                    if (saleHistories[saleHistories.length - 1].getDate().equals(LocalDate.now())) {
                        saleHistories[saleHistories.length - 1]
                                .saveReceipt(this.orders[this.orders.length - 1].getReceipt());
                    } else {
                        SaleHistory newSaleHistory[] = new SaleHistory[saleHistories.length + 1];
                        for (int j = 0; j < saleHistories.length; j++) {
                            newSaleHistory[j] = saleHistories[j];
                        }
                        newSaleHistory[saleHistories.length] = new SaleHistory(LocalDate.now());
                        saleHistories = newSaleHistory;
                        saleHistories[saleHistories.length - 1]
                                .saveReceipt(this.orders[this.orders.length - 1].getReceipt());
                    }
                    String cusPhone = fillPhoneNum();
                    for (int i = 0; i < customers.length; i++) {
                        if (customers[i].getPhone().equals(cusPhone)) {
                            customers[i].addPoint(this.orders[this.orders.length - 1].getOrderedDrinks().length);
                            customers[i].addReceipt(this.orders[this.orders.length - 1].getReceipt());

                            break;
                        }

                    }

                    break;

                case 2:
                    drinkDb.addDrink();
                    break;

                case 3:
                    drinkDb.editDrink();
                    break;

                case 4:
                    drinkDb.deleteDrink();
                    break;

                case 5:
                    checkSaleHistory();
                    break;

                case 6:
                    System.out.println("Your income is " + owner.getIncome() + " Baht");
                    break;

                case 7:
                    loop = false;
                    break;

                default:
                    System.out.println("Wrong input. Please choose number between 1-3");
                    break;
            }
        }
    }

    public boolean owLogin() {
        System.out.println("Please enter your id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("please enter your password");
        String password = sc.nextLine();
        boolean authenticate = authenticateOw(id, password);
        if (authenticate == true) {
            System.out.println("Welcome " + owner.getName());
            return true;
        } else {
            System.out.println("Wrong id or password. Please try again");
            return false;
        }
    }

    public boolean authenticateOw(int id, String password) {
        if ((id == owner.getId()) && (password.equals(owner.getPassword()))) {
            return true;
        } else {
            return false;
        }
    }

    public String fillPhoneNum() {
        System.out.println("Enter phone number");
        String phone = sc.nextLine();
        return phone;
    }

    public void checkSaleHistory() {
        System.out.println("---------------------------------------");
        System.out.println("Enter date (YYYY-MM-DD)");
        String selectDate = sc.next();
        for (int i = 0; i < saleHistories.length; i++) {
            if (saleHistories[i].getDate().toString().equals(selectDate)) {
                saleHistories[i].showSaleHistory();
                break;
            } else if ((i == saleHistories.length - 1) && (!saleHistories[i].getDate().toString().equals(selectDate))) {
                System.out.println("Not has sale that day");
                System.out.println("---------------------------------------");
            }
        }
    }

    public void registerCus() {
        System.out.println("Enter your name");
        String name = sc.nextLine();
        String phone = fillPhoneNum();
        System.out.println("Enter password");
        String password = sc.nextLine();

        boolean canRegister = false;
        for (int i = 0; i < customers.length; i++) {
            if (phone.equals(customers[i].getPhone())) {
                System.out.println("Cannot Register. This phone number is already use");
                break;
            }

            if ((i == customers.length - 1) && (!phone.equals(customers[i].getPhone()))) {
                canRegister = true;
            }
        }

        if (canRegister == true) {
            Customer[] newCustomers = new Customer[customers.length + 1];

            for (int i = 0; i < customers.length; i++) {
                newCustomers[i] = customers[i];
            }

            newCustomers[newCustomers.length - 1] = new Customer(customers[customers.length - 1].getId() + 1, name,
                    password, phone);
            customers = newCustomers;
            System.out.println("Sucessfully register");
        }

    }

}