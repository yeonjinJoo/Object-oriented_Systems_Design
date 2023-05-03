package Week9;

import java.util.Date;

public class Patient extends Person{
    private String department;

    public Patient(String name, Date born, Date died, String department){
        super(name, born,died);
        this.department = department;
    }

    public String toString(){
        return "[Patient] " + super.toString() + ", Department : " + department;
    }

    public String getDepartment() {
        return department;
    }
}
