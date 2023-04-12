package Week6.Exercise1;

import java.util.GregorianCalendar;

public class Person {
    private int year, month, day;

    public Person(){
        year = 1999;
        month = (int)(Math.random()*12)+1;

        int tempDay = 0;
        while(true){
            tempDay = (int)(Math.random()*31)+1;
            if(checkDate(this.year, this.month, tempDay)){
                day = tempDay;
                break;
            }
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean checkDate(int year, int month, int day){
        int[] months = {1,3,5,7,8,10,12, 4,6,9,11, 2};
        GregorianCalendar gc = new GregorianCalendar();
        for(int i = 0; i < months.length; i++){
            if(months[i] == month){
                if(i < 7){
                    if(!(day > 0 && day <32)){
                        return false;
                    }
                }
                else if(i > 6 && i < 11){
                    if(!(day > 0 && day < 31)){
                        return false;
                    }
                }
                else{
                    if(gc.isLeapYear(year)){
                        if(!(day > 0 && day < 30)){
                            return false;
                        }
                    }
                    else{
                        if(!(day > 0 && day < 29)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
