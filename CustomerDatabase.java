public class CustomerDatabase {
    private Customer[] customers;
    private int customerCount;

    public CustomerDatabase(){
        customers = new Customer[2];
        customers[0] = new Customer(1, "Wichanan", "123456", "0123456789");
        customers[1] = new Customer(2, "Somsak", "111111", "0999999999");
    }

    public boolean authenticate(String phone, String password) {
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

    public Customer getACustomer(String phone, String password){
        Customer nowCustomer = new Customer(0, "null", "null", "null");
        for(int i=0;i<customers.length;i++){
            if(customers[i].getPhone().equals(phone)){
                if(customers[i].getPassword().equals(password)){
                    nowCustomer = customers[i];
                    break;
                }
            }
        }
        return nowCustomer;
    }
}
