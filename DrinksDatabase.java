import java.util.Scanner;

public class DrinksDatabase {
    private Drink[] drinks;
    private int drinksCount;
    private Scanner sc;

    public DrinksDatabase() {
        drinks = new Drink[5];
        drinks[0] = new Drink(1, "Cocoa", 15);
        drinks[1] = new Drink(2, "Cha Yen", 15);
        drinks[2] = new Drink(3, "Green Tea", 15);
        drinks[3] = new Drink(4, "Matcha", 20);
        drinks[4] = new Drink(5, "Milk Tea", 20);
        drinksCount = drinks.length;
        sc = new Scanner(System.in);
    }

    public int getDrinksCount() {
        return this.drinksCount;
    }

    public void addDrink() {
        System.out.println("------------------");
        System.out.println("Enter drink name |");
        String dName = sc.nextLine();
        System.out.println("Enter price      |");
        double price = sc.nextDouble();
        if (drinks.length == 0) {
            drinks = new Drink[1];
            drinks[0] = new Drink(drinks.length, dName, price);
        } else {
            Drink newDrinks[] = new Drink[this.drinks.length + 1];
            for (int i = 0; i < this.drinks.length; i++) {
                newDrinks[i] = this.drinks[i];
            }
            newDrinks[this.drinks.length] = new Drink(newDrinks.length, dName, price);
            this.drinks = newDrinks;
            this.drinksCount = drinks.length;
        }
        System.out.println("------------------");
    }

    public void deleteDrink() {
        showAllDrinks();
        System.out.println("----------------------------------");
        System.out.println("Choose drink to delete by number |");
        int dId = sc.nextInt();
        if (this.drinks == null) {
            System.out.println("Database is empty                |");
            System.out.println("----------------------------------");
        } else if (dId < 0 || dId >= this.drinks.length) {
            System.out.println("Wrong Input. Please try again    |");
            System.out.println("----------------------------------");
        }

        Drink[] newDrinks = new Drink[this.drinks.length - 1];
        for (int i = 0, k = 0; i < this.drinks.length; i++) {
            if (i == dId - 1) {
                continue;
            }

            newDrinks[k++] = this.drinks[i];
        }
        this.drinks = newDrinks;
        drinksCount = drinks.length;
    }

    public void editDrink() {
        showAllDrinks();
        System.out.println("Choose drink to edit by number");
        int dId = sc.nextInt();
        System.out.println("Enter drink name");
        String dName = sc.next();
        System.out.println("Enter drink price");
        double price = sc.nextDouble();
        drinks[dId - 1].setDName(dName);
        drinks[dId - 1].setPrice(price);
        System.out.println("Successfully Edit ");
        this.drinks[dId - 1].showDrink();
        
    }

    public void showAllDrinks() {
        System.out.println("--------Menu--------");
        for (int i = 0; i < this.drinks.length; i++) {
            System.out.print(i+1 + ". ");
            this.drinks[i].showDrink();
        }
        System.out.println("--------------------");
    }

    public Drink getADrink(int dId) {
        return drinks[dId];
    }

}
