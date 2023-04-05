package Week5;

import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import Week5.Student;

public class Practice {
    public static void main(String[] args) {
        String name1, name2, temp;
        int[] birthday = new int[3];
        Scanner sc = new Scanner(System.in);
        name1 = sc.next();
        temp = sc.next();
        name2 = sc.next();

        Calendar now = Calendar.getInstance();
        int currYear = now.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH)+1;
        int currDay = now.get(Calendar.DAY_OF_MONTH);

        StringTokenizer st = new StringTokenizer(temp, ".");
        int i = 0;
        while(st.hasMoreTokens()){
            birthday[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        Student s1 = new Student(name1, birthday[0], birthday[1], birthday[2]);
        Student s2 = new Student(name2, birthday[0]);

        if(!(s1.checkDate(s1.getYear(), s1.getMonth(), s1.getDay()))){
            System.out.println("invalid input");
            return;
        }

        System.out.printf("%s %d.%d.%d age: %d\n", s1.getName(), s1.getYear(), s1.getMonth(),
                s1.getDay(), s1.calAge(currYear, currMonth, currDay));
        System.out.printf("%s %d.%d.%d age: %d\n", s2.getName(), s2.getYear(), s2.getMonth(),
                s2.getDay(), s2.calAge(currYear, currMonth, currDay));

        if(s1.isOlder(s2)){
            System.out.printf("%s is older than %s\n", s1.getName(), s2.getName());
        }
        else{
            System.out.printf("%s is older than %s\n", s2.getName(), s1.getName());
        }
    }
}
