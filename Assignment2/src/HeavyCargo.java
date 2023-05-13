public class HeavyCargo extends Cargo{
    public HeavyCargo(int ID, int weight){
        super(ID, weight);
        setFuelConsumption(2.5);
    }

    @Override
    public double consumption() {
        return getWeight() * getFuelConsumption();
    }
}
