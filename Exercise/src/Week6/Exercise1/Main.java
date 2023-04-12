package Week6.Exercise1;

import Week6.Exercise1.util.AgeCalculator;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();

        int compare = AgeCalculator.isOlder(p1, p2);
        System.out.printf("p1 %d.%d.%d age: %d\n", p1.getYear(), p1.getMonth(), p1.getDay(), AgeCalculator.calAge(p1));
        System.out.printf("p2 %d.%d.%d age: %d\n", p2.getYear(), p2.getMonth(), p2.getDay(), AgeCalculator.calAge(p2));

        if(compare == 1){
            System.out.println("p1 is older than p2");
        }
        else if(compare == 0){
            System.out.println("p1 and p2 are the same age");
        }
        else{
            System.out.println("p2 is older than p1");
        }
    }
}
