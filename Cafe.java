import java.util.Scanner;

public class Cafe{
    private boolean authenticate;
    private Customer currentCus;
    private CustomerDatabase cusDb;

    public Cafe(){
        this.authenticate = false;
        this.currentCus = new Customer(0, null, null, null);
        this.cusDb = new CustomerDatabase();
    }

    public void run() {
        System.out.println("Welcome to Liu'Cha");
        /*DrinksDatabase a = new DrinksDatabase();
        System.out.println(a.showAllDrinks());      
        a.deleteDrink(3);
        System.out.println(a.showAllDrinks());
        a.editDrink(1, null, 90);
        System.out.println(a.showAllDrinks());*/
        Scanner sc = new Scanner(System.in);
        while(true){
            while(!authenticate){
                
                authenticateCus();
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
        if(authenticate==true){
            this.currentCus = cusDb.getACustomer(phone, password);
            System.out.println("Welcome " + currentCus.getName());
        }
        else{
            System.out.println("Wrong phone number or password. Please try again");
        }
        return false;
    }

}