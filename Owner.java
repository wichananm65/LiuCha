
public class Owner extends Person{
    
    private Drink[] drinks;
    private SaleHistory[] saleHistory;

    public Owner(int id, String name, String password, Drink[] drinks) {
        super(id, name, password);
        this.drinks = drinks;
    }

    public String getName() {
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String showAllSaleHistory() {
        String text = "";
        for(int i=0;i<saleHistory.length;i++){
            text = text + saleHistory[i].showSaleHistory();
        }
        return text;
    }

    public String showAllDrinks(){
        String text = "";
        for(int i=0;i<drinks.length;i++){
            text = text + i + "." + drinks[i].showDrink() + "\n";
        }
        return text;
    }
}
