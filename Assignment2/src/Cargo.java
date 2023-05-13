import java.util.ArrayList;

public abstract class Cargo {
    private int ID;
    private int weight;
    private double fuelConsumption;

    //모든 Cargoes 저장.
    static ArrayList<Cargo> allCargoes = new ArrayList<>();

    // Accessors
    public int getID() {
        return ID;
    }

    public int getWeight() {
        return weight;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    // Mutators
    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Cargo(int ID, int weight){
        this.ID = ID;
        this.weight = weight;
        fuelConsumption = 0.0;
        // 생성자 불릴때마다 allCargoes에 해당 Cargo 추가
        allCargoes.add(this);
    }

    public abstract double consumption();

    public static ArrayList<ArrayList<Integer>> sortCargoes(){
        //index로 바꾸자.
        ArrayList<ArrayList<Integer>> categorized = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> basics = new ArrayList<Integer>();
        ArrayList<Integer> heavies = new ArrayList<Integer>();
        ArrayList<Integer> dangerous = new ArrayList<Integer>();
        ArrayList<Integer> liquid = new ArrayList<Integer>();

        for(int i = 0; i < allCargoes.size(); i++){
            Cargo c = allCargoes.get(i);
            if(c instanceof BasicCargo){
                basics.add(c.ID);
            }
            else if(c instanceof DangerousCargo){
                dangerous.add(c.ID);
            }
            else if(c instanceof LiquidCargo){
                liquid.add(c.ID);
            }
            else{
                heavies.add(c.ID);
            }
        }

        // basics list, heavies list, dangerous list, liquid list 가진 ArrayList 반환
        categorized.add(basics);
        categorized.add(heavies);
        categorized.add(dangerous);
        categorized.add(liquid);

        return categorized;
    }
}
