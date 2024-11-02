import java.util.Scanner;

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

    public void setTopping(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your Topping by number");
        System.out.println("1. Bubble");
        System.out.println("2. Fruit Salad");
        System.out.println("3. Konjac");
        System.out.println("4. Brown Sugar Konjac");
        System.out.println("5. Diamond oKonjac");
        System.out.println("6. None");

        boolean loop = true;
        int toppingNum;
        while (loop) {
            toppingNum = sc.nextInt();
            switch (toppingNum) {
                case 1:
                    this.topping = "Bubble";
                    loop = false;
                    break;
                case 2:
                    this.topping = "Fruit Salad";
                    loop = false;
                    break;
                case 3:
                    this.topping = "Konjac";
                    loop = false;
                    break;
                case 4:
                    this.topping = "Brown Sugar Konjac";
                    loop = false;
                    break;
                case 5:
                    this.topping = "Diamond oKonjac";
                    loop = false;
                    break;
                case 6:
                    loop = false;
                    break;
                default:
                    System.out.println("Wrong command. Please select Number between 1-6");
                    System.out.println("Select your Topping by number");
                    break;
            }
        }
        if(topping == "Bubble" || topping == "Fruit Salad"){
            this.price = this.price + 5;
        }
        else if(topping == "None"){

        }
        else{
            this.price = this.price + 10;
        }
    }

    public void setSweetness(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your Sweetness by number");
        System.out.println("1. None");
        System.out.println("2. Little Sweet");
        System.out.println("3. Regular");
        System.out.println("4. Very Sweet");

        boolean loop = true;
        int sweetnessNum;
        while (loop) {
            sweetnessNum = sc.nextInt();
            switch (sweetnessNum) {
                case 1:
                    this.sweetness= "None";
                    loop = false;
                    break;
                case 2:
                    this.sweetness = "Little Sweet";
                    loop = false;
                    break;
                case 3:
                    this.sweetness = "Regular";
                    loop = false;
                    break;
                case 4:
                    this.sweetness = "Very Sweet";
                    loop = false;
                    break;
                default:
                    System.out.println("Wrong input. Please select Number between 1-4");
                    System.out.println("Select your sweetness by number");
                    break;
            }
        }
    }

    public String showDrink(){
        return dId + ". " + dName + " " + " " + price + " Baht\n";
    }
    
}
