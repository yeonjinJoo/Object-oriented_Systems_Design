public class BasicCargo extends Cargo {
    public BasicCargo(int ID, int weight){
        super(ID, weight);
        setFuelConsumption(1.5);
    }

    @Override
    public double consumption() {
        return getWeight() * getFuelConsumption();
    }
}
