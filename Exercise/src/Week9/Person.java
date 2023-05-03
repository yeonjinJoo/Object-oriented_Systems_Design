package Week9;

import java.util.Date;

public class Person {
    private String name;
    private Date born;
    private Date died;

    public String getName() {
        return name;
    }

    public Date getBorn() {
        return born;
    }

    public Date getDied() {
        return died;
    }

    public Person(String name, Date born, Date died){
        if(born == null){
            System.out.println("Fatal error: Person's born date is null");
            System.exit(0);
        }
        else{
            this.name = name;
            this.born = new Date(born.getYear(), born.getMonth(), born.getDate());
            if(died == null){
                this.died = null;
            }
            else{
                this.died = new Date(died.getYear(), died.getMonth(), died.getDate());
            }
        }
    }

    public String toString(){
        String result = "Name : " + this.name + ", Born in "+ born.getMonth()+ "/"+born.getDate()+"/"+born.getYear();
        if(this.died != null){
            result += ", died in " + died.getMonth()+ "/"+died.getDate()+"/"+died.getYear();
        }
        return result;
    }

    public boolean equals(Person p){
        if(p == null){
            return false;
        }
        if(name.equals(p.getName()) && born.getYear()== p.getBorn().getYear()&&born.getMonth()==p.getBorn().getMonth()&&born.getDate()==p.getBorn().getDate()){
            if(died == null && p.getDied() == null){
                return true;
            }
            else if(died != null && p.getDied() != null){
                if(died.getYear()== p.getDied().getYear()&&died.getMonth()==p.getDied().getMonth()&&died.getDate()==p.getDied().getDate()){
                    return true;
                }
            }
        }
        return false;
    }

}
