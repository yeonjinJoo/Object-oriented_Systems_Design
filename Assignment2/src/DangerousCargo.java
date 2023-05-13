public class DangerousCargo extends HeavyCargo{
    public DangerousCargo(int ID, int weight){
        super(ID, weight);
        setFuelConsumption(4.0);
    }
}
