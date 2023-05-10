package Week10.Practice1;

public abstract class Taxi {
    private int carNum;
    private double distance;
    private double income;

    public Taxi(int carNum){
        this.carNum = carNum;
        distance = 0;
        income = 0;
    }

    public String toString(){
        return "Car number: " + carNum + ", Distance: " +  distance+", Income: " + income;
    }

    public abstract double getPaid(double distance);

    public void doDrive(double dis){
        income += getPaid(dis);
        distance += dis;
    }

    public boolean earnMore(Taxi t){
        if(this.income > t.income){
            return true;
        }
        return false;
    }
}
