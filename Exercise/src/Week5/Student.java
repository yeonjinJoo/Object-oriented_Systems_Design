package Week5;

import java.util.GregorianCalendar;

public class Student {
    private String name;
    private int year, month, day;
    public Student(String name, int year, int month, int day){
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Student(String name, int year){
        this.name = name;
        this.year = year;
        this.month = (int)(Math.random()*12)+1;

        int tempDay = 0;
        while(true){
            tempDay = (int)(Math.random()*31)+1;
            if(checkDate(this.year, this.month, tempDay)){
                this.day = tempDay;
                break;
            }
        }
    }
    public String getName(){
        return name;
    }
    public int getDay(){
        return day;
    }
    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
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

    public int calAge(int year, int month, int day){
        int age = 0;
        if(this.month < month){
            age = year - this.year;
        }
        else if(this.month == month){
            if(this.day <= day){
                age = year - this.year;
            }
            else{
                age = year - this.year - 1;
            }
        }
        else{
            age = year - this.year - 1;
        }
        return age;
    }
    public boolean isOlder(Student stu){
        if(this.year > stu.getYear()){
            return false;
        }
        if(this.year == stu.getYear()){
            if(this.month > stu.getMonth()){
                return false;
            }
        }
        if(this.year == stu.getYear()){
            if(this.month == stu.getMonth()){
                if(this.day >= stu.getDay()){
                    return false;
                }
            }
        }
        return true;
    }
}
