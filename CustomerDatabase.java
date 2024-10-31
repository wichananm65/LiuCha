public class CustomerDatabase {
    private Customer[] customers;
    private int customerCount;

    public CustomerDatabase(){
        customers = new Customer[2];
        customers[0] = new Customer(1, "Wichanan", "123456", "0123456789");
        customers[1] = new Customer(2, "Somsak", "1", "1");
    }

    public boolean authenticateCus(String phone, String password) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getPhone().equals(phone)) {
                if (customers[i].getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    
}
