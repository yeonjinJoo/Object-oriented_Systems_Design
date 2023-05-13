import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void printFormat(ArrayList<Integer> list, String type, PrintStream out, int space){
        for(int k = 0; k < list.size(); k++){
            if(k == 0){
                for(int i = 0; i < space; i++){
                    out.print(" ");
                }
                out.print(type + ":");
            }
            out.print(" " + list.get(k));
        }
        if(list.size() != 0){
            out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        int warehouseID = 0;
        int truckID = 0;
        int cargoID = 0;

        ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
        ArrayList<Truck> trucks = new ArrayList<Truck>();

        // Generate a new Scanner object to access data from the input file in the directory indicated by "args[0]".
        Scanner in = new Scanner(new File(args[0]));

        int totalEvents = in.nextInt();
        int numEvent = 0;

        for(int i = 0; i < totalEvents; i++){
            //File 다음 줄로 넘겨주기. 1은 nextLine 사용해서 자동으로 넘어감
            if(numEvent != 1){
                in.nextLine();
            }
            numEvent = in.nextInt();

            // Create Cargo
            if(numEvent == 1){
                // 공백 잘라주기
                String s = in.nextLine().substring(1);

                int weight;

                int index1 = s.indexOf(" ");
                int inWhere = Integer.parseInt(s.substring(0, index1));
                s = s.substring(index1 + 1);

                //Basic or Heavy
                if(!s.contains(" ")){
                    weight = Integer.parseInt(s);
                    if(weight <= 1000){
                        // inWhere = index of warehouse. 왜? warehouse에 순서대로 만들어지니까
                        // cargoID 증가시켜줌
                        warehouses.get(inWhere).addToCargoes(new BasicCargo(cargoID++, weight));
                    }
                    else{
                        warehouses.get(inWhere).addToCargoes(new HeavyCargo(cargoID++, weight));
                    }
                }
                //Dangerous or Liquid
                else{
                    int index2 = s.indexOf(" ");
                    weight = Integer.parseInt(s.substring(0, index2));
                    if(s.substring(index2+1).equals("D")){ // Dangerous
                        warehouses.get(inWhere).addToCargoes(new DangerousCargo(cargoID++, weight));
                    }
                    else{ // Liquid
                        warehouses.get(inWhere).addToCargoes(new LiquidCargo(cargoID++, weight));
                    }
                }
            }
            //Create Truck
            else if(numEvent == 2){
                int inWhere = in.nextInt();
                int maxWeight = in.nextInt();
                int maxNumOfAllCargoes = in.nextInt();
                int maxNumOfHeavyCargoes = in.nextInt();
                int maxNumOfDangerousCargoes = in.nextInt();
                int maxNumOfLiquidCargoes = in.nextInt();
                double fuelPerKm = in.nextDouble();

                //Object 하나 공유 중
                Truck t = new Truck(truckID++, warehouses.get(inWhere), maxWeight, maxNumOfAllCargoes, maxNumOfHeavyCargoes, maxNumOfDangerousCargoes, maxNumOfLiquidCargoes, fuelPerKm);
                warehouses.get(inWhere).addToCurrent(t);
                trucks.add(t);
            }
            //Create Warehouse
            else if(numEvent == 3){
                double X = in.nextDouble();
                double Y = in.nextDouble();

                warehouses.add(new Warehouse(warehouseID++, X, Y));
            }
            //Load a Cargo to a Truck
            else if(numEvent == 4){
                int truckNum = in.nextInt();
                int cargoNum = in.nextInt();

                trucks.get(truckNum).load(Cargo.allCargoes.get(cargoNum));
            }
            //Unload a Cargo from a Truck
            else if(numEvent == 5){
                int truckNum = in.nextInt();
                int cargoNum = in.nextInt();

                trucks.get(truckNum).unload(Cargo.allCargoes.get(cargoNum));
            }
            //Truck moves to another Warehouse
            else if(numEvent == 6){
                int truckNum = in.nextInt();
                int warehouseNum = in.nextInt();

                Warehouse past = trucks.get(truckNum).getCurrentWarehouse();

                // 항해하는데 성공하면 과거 warehouse에 outgoing 해주고, 들어가는 데에 incoming 해주기
                if(trucks.get(truckNum).sailTo(warehouses.get(warehouseNum))){
                    past.outgoingTruck(trucks.get(truckNum));
                    warehouses.get(warehouseNum).incomingTruck(trucks.get(truckNum));
                }

            }
            //Refuel a truck
            else if(numEvent == 7){
                int truckNum = in.nextInt();
                double fuelAmount = in.nextDouble();

                trucks.get(truckNum).reFuel(fuelAmount);
            }
        }
        in.close();

        // 0번째에 basic, 1번째에 heavy, 2번째에 dangerous, 3번째에 liquid list
        ArrayList<ArrayList<Integer>> sortedCargoes = Cargo.sortCargoes();

        // Generate a new PrintStream object to output data to the file in the directory indicated by "args[1]"
        PrintStream out = new PrintStream(new File(args[1]));

        for(int i = 0; i < warehouses.size(); i++){
            Warehouse w = warehouses.get(i);

            ArrayList<Integer> basics = new ArrayList<Integer>();
            ArrayList<Integer> heavies = new ArrayList<Integer>();
            ArrayList<Integer> dangerous = new ArrayList<Integer>();
            ArrayList<Integer> liquid = new ArrayList<Integer>();

            out.println("Warehouse " + i + ": (" + w.getX() +", "+ w.getY() + ")");
            for(int j = 0; j < w.getCargoes().size(); j++){
                // 0번째는 basics
                int tempID = w.getCargoes().get(j).getID();
                if(sortedCargoes.get(0).contains(tempID)){
                    basics.add(tempID);
                }
                // 1번째 heavy
                else if(sortedCargoes.get(1).contains(tempID)){
                    heavies.add(tempID);
                }
                // 2번째 dangerous
                else if(sortedCargoes.get(2).contains(tempID)){
                    dangerous.add(tempID);
                }
                // 3번째 liquid
                else if(sortedCargoes.get(3).contains(tempID)){
                    liquid.add(tempID);
                }
            }

            //Warehouse의 BasicCargo print
            printFormat(basics, "BasicCargo", out, 2);
            //Warehouse의 HeavyCargo print
            printFormat(heavies, "HeavyCargo", out, 2);
            printFormat(dangerous, "DangerousCargo", out, 2);
            printFormat(liquid, "LiquidCargo", out, 2);

            basics.clear(); heavies.clear(); dangerous.clear(); liquid.clear();

            // Trucks 순서대로 정렬해주기
            w.sortTrucks();
            ArrayList<Truck> current = w.getCurrent();
            for(int j = 0; j < current.size(); j++){
                Truck t = current.get(j);
                out.printf("  Truck %d: %.1f\n", t.getID(), t.getFuel());

                ArrayList<Cargo> loaded = t. getCurrentCargoes();
                for(int k = 0; k < loaded.size(); k++){
                    // 0번째는 basics
                    int tempID = loaded.get(k).getID();
                    if(sortedCargoes.get(0).contains(tempID)){
                        basics.add(tempID);
                    }
                    // 1번째 heavy
                    else if(sortedCargoes.get(1).contains(tempID)){
                        heavies.add(tempID);
                    }
                    // 2번째 dangerous
                    else if(sortedCargoes.get(2).contains(tempID)){
                        dangerous.add(tempID);
                    }
                    // 3번째 liquid
                    else if(sortedCargoes.get(3).contains(tempID)){
                        liquid.add(tempID);
                    }
                }
                printFormat(basics, "BasicCargo", out, 4);
                printFormat(heavies, "HeavyCargo", out, 4);
                printFormat(dangerous, "DangerousCargo", out, 4);
                printFormat(liquid, "LiquidCargo", out, 4);

                //truck마다 Cargo 다르므로 비워주기
                basics.clear(); heavies.clear(); dangerous.clear(); liquid.clear();
            }
        }
        out.close();
    }
}

