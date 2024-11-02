
public class Rider extends Person {
    private String phone;
    private double income;
    private String vehicle;
    private String registration;

    public Rider(int id, String name, String password, String phone, String vehicle, String registration) {
        super(id, name, password);
        this.phone = phone;
        this.income = 0;
        this.vehicle = vehicle;
        this.registration = registration;
    }

    public String getPhone() {
        return phone;
    }

    public double getIncome() {
        return income;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getRegistration() {
        return registration;
    }

    public void updateIncome(int money) {
        this.income = this.income + money;
    }

    public void showRider() {
        System.out.println("Rider");
        System.out.println("Rider name: " + getName());
        System.out.println("Rider phone: " + getPhone());
        System.out.println("Vehicle: " + getVehicle());
        System.out.println("Registration: " + getRegistration());
    }
}
