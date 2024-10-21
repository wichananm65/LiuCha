public class DrinksDatabase {
    private Drink[] drinks;
    private int drinksCount;

    public DrinksDatabase(){
        drinks = new Drink[5];
        drinks[0] = new Drink(1, "Cocoa", 15);
        drinks[1] = new Drink(2, "Cha Yen", 15);
        drinks[2] = new Drink(3, "Green Tea", 15);
        drinks[3] = new Drink(4, "Matcha", 20);
        drinks[4] = new Drink(5, "Milk Tea", 20);
        drinksCount = 5;
    }

    public void addDrink(String dName, double price){
        Drink newDrinks[] = new Drink[this.drinks.length+1];  
        for(int i=0;i<this.drinks.length;i++){
            newDrinks[i] = this.drinks[i];
        }
        newDrinks[this.drinks.length] = new Drink(drinksCount, dName, price);
        this.drinks = newDrinks;
        this.drinksCount++;
    }

    public void deleteDrink(int dId){
        if (this.drinks == null){
            System.out.println("Database is empty");
        } else if(dId < 0 || dId >= this.drinks.length){
            System.out.println("Wrong Input. Please try again");
        }

        Drink[] newDrinks = new Drink[this.drinks.length-1];
        for(int i=0, k=0;i<this.drinks.length;i++){
            if(i==dId-1){
                continue;
            }

            newDrinks[k++] = this.drinks[i];
        }
        this.drinks = newDrinks;
    }

    public void editDrink(int dId, String dName, double price){
        if(dName != null){
            drinks[dId-1].setDName(dName);
        }
        if(price != 0){
            drinks[dId-1].setPrice(price);
        }
        System.out.println("Successfully Edit " + this.drinks[dId-1].showDrink());
    } 

    public String showAllDrinks(){
        String text = "";
        for(int i=0; i<this.drinks.length;i++){
            text = text + this.drinks[i].showDrink();
        }
        return text;
    }

}
