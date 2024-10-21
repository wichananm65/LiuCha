
public class Rider extends Person{
    private String phone;
    private double income;
    private String vehicle;
    private String vehicleNumber;

    public Rider(int id, String name, String password, String phone, String vehicle, String vehicleNumber) {
        super(id, name, password);
        this.phone = phone;
        this.income = 0;
        this.vehicle = vehicle;
        this.vehicleNumber = vehicleNumber;
    }

    public String getPhone() {
        return phone;
    }

    public double getIncome(){
        return income;
    }

    public String showRider(){
        return  "Rider----------\nName: " + name + "\nPhone: " + phone + "\nVehicle: " + vehicle + " " + vehicleNumber;
    }
}
