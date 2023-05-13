import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Warehouse {
    private int ID;
    private double X, Y;
    private ArrayList<Cargo> cargoes;
    private ArrayList<Truck> history;
    private ArrayList<Truck> current;

    // Accessor
    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public ArrayList<Cargo> getCargoes() {
        return cargoes;
    }

    public ArrayList<Truck> getCurrent() {
        return current;
    }

    // Mutator
    public void addToCargoes(Cargo c) {
        cargoes.add(c);
    }

    public void removeFromCargoes(int index){
        cargoes.remove(index);
    }

    public void addToCurrent(Truck t){
        current.add(t);
    }

    // Constructor
    public Warehouse(int ID, double X, double Y){
        this.ID = ID;
        this.X = X;
        this.Y = Y;
        cargoes = new ArrayList<Cargo>();
        history = new ArrayList<Truck>();
        current = new ArrayList<Truck>();
    }

    public double getDistance(Warehouse other){
        double xDiffSquare = (X - other.X)*(X - other.X);
        double yDiffSquare = (Y - other.Y)*(Y - other.Y);
        return Math.sqrt(xDiffSquare + yDiffSquare);
    }

    public void incomingTruck(Truck t){
        //truck이 history에 존재하면 지우고 true 반환, 없으면 아무것도 안하고 false 반환
        //object 삭제할 땐 이렇게 해야함
        for(int i = 0; i < history.size(); i++){
            if(history.get(i).getID() == t.getID()){
                history.remove(history.get(i));
                break; // ID 같은 경우가 두번 있을 수 없으므로.
            }
        }
        current.add(t);
    }

    public void outgoingTruck(Truck t){
        for(int i = 0; i < current.size(); i++){
            if(current.get(i).getID() == t.getID()){
                current.remove(current.get(i));
                break; // ID 같은 경우가 두번 있을 수 없으므로.
            }
        }
        history.add(t);
    }

    public void sortTrucks(){
        Collections.sort(current, new Comparator<Truck>() {
            @Override
            public int compare(Truck t1, Truck t2) {
                if(t1.getID() < t2.getID()){
                    return -1; // t1 객체가 더 작다고 판단
                }
                else if(t1.getID() > t2. getID()){
                    return 1; // t1 객체가 더 크다고 판단
                }
                return 0;
            }
        });
    }

}
