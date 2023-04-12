package Week6.Exercise1.util;
import Week6.Exercise1.Person;

import java.util.Calendar;

public class AgeCalculator {
    static Calendar now = Calendar.getInstance();
    static int currYear = now.get(Calendar.YEAR);
    static int currMonth = now.get(Calendar.MONTH)+1;
    static int currDay = now.get(Calendar.DAY_OF_MONTH);

    public static int calAge(Person p){
        int age = 0;
        if(p.getMonth() < currMonth){
            age = currYear - p.getYear();
        }
        else if(p.getMonth() == currMonth){
            if(p.getDay() <= currDay){
                age = currYear - p.getYear();
            }
            else{
                age = currYear - p.getYear() - 1;
            }
        }
        else{
            age = currYear - p.getYear() - 1;
        }
        return age;
    }

    public static int isOlder(Person p1, Person p2){
        if(calAge(p1) > calAge(p2)){
            return 1;
        }
        else if(calAge(p1) == calAge(p2)){
            return 0;
        }
        else{
            return -1;
        }
    }
}
