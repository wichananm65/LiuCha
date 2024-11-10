import java.util.Scanner;

public class DrinksDatabase {
    private Drink[] drinks;
    private int drinksCount;
    private Scanner sc;

    public DrinksDatabase() {
        drinks = new Drink[27];
        drinks[0] = new Drink(1, "Taiwan Milk Tea", 19);
        drinks[1] = new Drink(2, "Green Tea Milk", 19);
        drinks[2] = new Drink(3, "Pink Milk Tea", 24);
        drinks[3] = new Drink(4, "Ovaltine Milk Tea", 24);
        drinks[4] = new Drink(5, "Thai Milk Tea", 24);
        drinks[5] = new Drink(6, "Taro Milk Tea", 24);
        drinks[6] = new Drink(7, "Apple Milk Tea", 24);
        drinks[7] = new Drink(8, "Melon Milk Tea", 24);
        drinks[8] = new Drink(9, "Honey Milk Tea", 24);
        drinks[9] = new Drink(10, "Coffee Milk Tea", 24);
        drinks[10] = new Drink(11, "Cocoa Milk Tea", 24);
        drinks[11] = new Drink(12, "Strawberry Milk Tea", 24);
        drinks[12] = new Drink(13, "White Malt Milk Tea", 24);
        drinks[13] = new Drink(14, "Brown Sugar Tea", 24);
        drinks[14] = new Drink(15, "Brown Sugar Milk Tea", 24);
        drinks[15] = new Drink(16, "Jasmine Green Tea", 19);
        drinks[16] = new Drink(17, "Apple Green Tea", 24);
        drinks[17] = new Drink(18, "Melon Green Tea", 24);
        drinks[18] = new Drink(19, "Lychee Green Tea", 24);
        drinks[19] = new Drink(20, "Yogurt Green Tea", 24);
        drinks[20] = new Drink(21, "Yogurt Apple Green Tea", 29);
        drinks[21] = new Drink(22, "Yogurt Lychee Green Tea", 29);
        drinks[22] = new Drink(23, "Yogurt Strawberry Green Tea", 29);
        drinks[23] = new Drink(24, "Honey Lemon Tea", 29);
        drinks[24] = new Drink(25, "Strawberry Green Tea", 24);
        drinks[25] = new Drink(26, "Passion Fruit Green Tea", 24);
        drinks[26] = new Drink(27, "Plum Green Tea", 24);
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
            System.out.print(i + 1 + ". ");
            this.drinks[i].showDrink();
        }
        System.out.println("--------------------");
    }

    public Drink getADrink(int dId) {
        return drinks[dId];
    }

}
