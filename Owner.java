
public class Owner extends Person {
    private double income;


    public Owner(int id, String name, String password) {
        super(id, name, password);
        this.income = 2000;
    }

    public double getIncome(){
        return income;
    }

    public void updateIncome(double money) {
        this.income = this.income + money;
    }
}
