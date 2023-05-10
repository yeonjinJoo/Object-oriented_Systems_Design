package Week10.Practice1;

public class DeluxeTaxi extends Taxi{
    private double farePerKilometer;
    private double baseDistance;
    private double baseFee;

    public DeluxeTaxi(int carNum, double farePerKilometer){
        super(carNum);
        baseDistance = 3;
        baseFee = 5;
        if(farePerKilometer > baseFee / baseDistance){
            this.farePerKilometer = farePerKilometer;
        }
        else{
            System.out.println("Fatal Error: FarePerKilometer is too small!");
            System.exit(0);
        }
    }

    public String toString(){
        return "[DeluxeTaxi] | "+super.toString() + ", FarePerKilometer: " + farePerKilometer + ", BaseDistance: " + baseDistance + ", BaseFee: " + baseFee;
    }

    public double getPaid(double distance){
        if(distance <= baseDistance){
            return baseFee;
        }
        else{
            return baseFee + (distance - baseDistance) * farePerKilometer;
        }
    }
}
