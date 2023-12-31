package Week9;

import java.util.Date;

public class Physician extends Doctor{
    public Physician(String name, Date born, Date died, String hospital){
        super(name, born, died, hospital);
    }

    public void examination(Patient p){
        if(p.getDepartment().equals("internal")){
            System.out.println("I'll take care of you!");
        }
        else{
            System.out.println("Sorry, but it's not my major");
        }
    }

    @Override
    public String toString() {
        return "[Physician] & " + super.toString();
    }
}
