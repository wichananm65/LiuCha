public class RiderDatabase {
    private Rider[] riders;
    private int riderCount;

    public RiderDatabase(){
        riders = new Rider[2];
        riders[0] = new Rider(1, "Sonchai", "123", "1", "Honda Icon", "AB123");
        riders[1] = new Rider(2, "Somying", "123", "2", "Honda Wave", "BD222");
    }

    public boolean authenticate(String phone, String password) {
        for (int i = 0; i < riders.length; i++) {
            if (riders[i].getPhone().equals(phone)) {
                if (riders[i].getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public Rider getARider(String phone, String password){
        Rider nowRider = new Rider(0, "null", "null", "null", "null", "null");
        for(int i=0;i<riders.length;i++){
            if(riders[i].getPhone().equals(phone)){
                if(riders[i].getPassword().equals(password)){
                    nowRider = riders[i];
                    break;
                }
            }
        }
        return nowRider;
    }
}

