
public class Drink {
    private int dId;
    private String dName;
    private double price;
    private String topping;
    private String sweetness;

    public Drink(int dId, String dName, double price) {
        this.dId = dId;
        this.dName = dName;
        this.price = price;
        topping = "None";
        sweetness = "Regular";
    }

    //get
    public int getDId(){
        return dId;
    }

    public String getDName() {
        return dName;
    }

    public double getPrice() {
        return price;
    }

    public String getTopping(){
        return topping;
    }

    public String getSweetness(){
        return sweetness;
    }

    //set
    public void setDid(int dId){
        this.dId = dId;
    }

    public void setDName(String dName){
        this.dName = dName;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setTopping(String topping){
        this.topping = topping;
        if(topping == "Bubble" || topping == "Fruit Salad"){
            this.price = this.price + 5;
        }
        else{
            this.price = this.price + 10;
        }
    }

    public void setSweetness(String sweetness){
        this.sweetness = sweetness;
    }

    public String showDrink(){
        return dId + ". " + dName + " " + " " + price + " Baht\n";
    }
    
}
