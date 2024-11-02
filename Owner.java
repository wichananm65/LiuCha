
public class Owner extends Person{
    
    private Drink[] drinks;
    private SaleHistory[] saleHistory;

    public Owner(int id, String name, String password) {
        super(id, name, password);
        this.drinks = drinks;
    }

    public String getName() {
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String showAllDrinks(){
        String text = "";
        for(int i=0;i<drinks.length;i++){
            text = text + i + "." + drinks[i].showDrink() + "\n";
        }
        return text;
    }
}
