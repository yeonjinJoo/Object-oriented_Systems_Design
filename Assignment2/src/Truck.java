import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Truck {
    private int ID;
    private double fuel, fuelPerKm;
    private Warehouse currentWarehouse;
    private int totalWeight; // max weight
    private int maxNumOfAllCargoes, maxNumOfHeavyCargoes, maxNumOfDangerousCargoes, maxNumOfLiquidCargoes;

    //전체 heavyCargoes 갯수는 numOfHeavyCargoes + numOfDangerousCargoes + numOfLiquidCargoes이다
    private int numOfBasicCargoes, numOfHeavyCargoes, numOfDangerousCargoes, numOfLiquidCargoes;
    private int currentWeight; // current Weight

    private ArrayList<Cargo> loaded;

    // Accessors
    public int getID() {
        return ID;
    }

    public double getFuel() {
        return fuel;
    }

    public Warehouse getCurrentWarehouse() {
        return currentWarehouse;
    }

    public Truck(int ID, Warehouse w, int totalWeight, int maxNumOfAllCargoes, int maxNumOfHeavyCargoes, int maxNumOfDangerousCargoes, int maxNumOfLiquidCargoes, double fuelPerKm){
        this.ID = ID;
        fuel = 0; //들어있는 fuel 0으로 초기화
        this.fuelPerKm = fuelPerKm;
        loaded = new ArrayList<Cargo>();

        this.currentWarehouse = w;
        this.totalWeight = totalWeight;

        this.maxNumOfAllCargoes = maxNumOfAllCargoes;
        this.maxNumOfHeavyCargoes = maxNumOfHeavyCargoes;
        this.maxNumOfDangerousCargoes = maxNumOfDangerousCargoes;
        this.maxNumOfLiquidCargoes = maxNumOfLiquidCargoes;

        numOfBasicCargoes = 0;
        numOfHeavyCargoes = 0;
        numOfDangerousCargoes = 0;
        numOfLiquidCargoes = 0;
        currentWeight = 0;
    }

    public ArrayList<Cargo> getCurrentCargoes(){
        //ID 기준으로 정렬 후 return. ArrayList 비어있어도 제대로 작동함
        Collections.sort(loaded, new Comparator<Cargo>() {
            @Override
            public int compare(Cargo c1, Cargo c2) {
                if(c1.getID() < c2.getID()){
                    return -1; // c1 객체가 더 작다고 판단
                }
                else if(c1.getID() > c2. getID()){
                    return 1; // c1 객체가 더 크다고 판단
                }
                return 0;
            }
        });
        return loaded;
    }

    public boolean sailTo(Warehouse w){
        //필요한 연료는 Cargoes consumption + 거리 * 1Km 당 연료
        double distance = currentWarehouse.getDistance(w);
        double cargoesConsumption = 0.0;
        for(Cargo c : loaded){
            cargoesConsumption += c.consumption();
        }

        // cargoesConsumption도 per Km라서 곱해줘야한다 !
        double requiredFuel = (cargoesConsumption + fuelPerKm) * distance;

        if(fuel >= requiredFuel){
            // currentWarehouse 위치 w로 바뀐다.
            currentWarehouse = w;
            // 연료 업데이트 해준다.
            fuel = fuel - requiredFuel;
            return true;
        }
        return false;
    }

    public void reFuel(double amount){
        fuel += amount;
    }

    public boolean load(Cargo c){
        ArrayList<Cargo> currentCargoes = currentWarehouse.getCargoes();
        for(int i = 0; i < currentCargoes.size(); i++){
            if(currentCargoes.get(i).getID() == c.getID()){
                int totalNumOfHeavyCargoes = numOfHeavyCargoes + numOfDangerousCargoes + numOfLiquidCargoes;
                int totalNumOfAll = totalNumOfHeavyCargoes + numOfBasicCargoes;

                //전체 자리 남았는지 끊어주기. 무게 되는지 먼저 끊어주기. 안되면 바로 컷
                if(totalNumOfAll < maxNumOfAllCargoes &&(currentWeight + c.getWeight()) <= totalWeight){
                    if(c instanceof BasicCargo){
                        loaded.add(c);
                        currentWarehouse.removeFromCargoes(i);
                        numOfBasicCargoes++;
                        currentWeight += c.getWeight();
                        return true;
                    }
                    else{
                        // Heavy 들어갈 자리 있는지 확인해주기
                        if(totalNumOfHeavyCargoes < maxNumOfHeavyCargoes){
                            if(c instanceof DangerousCargo){
                                if(numOfDangerousCargoes < maxNumOfDangerousCargoes){
                                    loaded.add(c);
                                    currentWarehouse.removeFromCargoes(i);
                                    numOfDangerousCargoes++;
                                    currentWeight += c.getWeight();
                                    return true;
                                }
                            }
                            else if(c instanceof LiquidCargo){
                                if(numOfLiquidCargoes < maxNumOfLiquidCargoes){
                                    loaded.add(c);
                                    currentWarehouse.removeFromCargoes(i);
                                    numOfLiquidCargoes++;
                                    currentWeight += c.getWeight();
                                    return true;
                                }
                            }
                            else{
                                loaded.add(c);
                                currentWarehouse.removeFromCargoes(i);
                                numOfHeavyCargoes++;
                                currentWeight += c.getWeight();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean unload(Cargo c){
        for(int i = 0; i < loaded.size(); i++){
            if(loaded.get(i).getID() == c.getID()){
                currentWeight -= c.getWeight(); // cargo만큼 무게 빼주기
                //남아있는 cargo 갯수 변경하기 위해서 어떤 객체인지 체크
                if(c instanceof BasicCargo){
                    numOfBasicCargoes--;
                }
                else if(c instanceof DangerousCargo){
                    numOfDangerousCargoes--;
                }
                else if(c instanceof LiquidCargo){
                    numOfLiquidCargoes--;
                }
                else{
                    numOfHeavyCargoes--;
                }
                loaded.remove(loaded.get(i)); // Truck에 cargo 제거
                currentWarehouse.addToCargoes(c); // Warehouse에 cargo 추가
                return true;
            }
        }
        return false;
    }
}
