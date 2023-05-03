package Week9;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Cho", new Date(1894, 2, 8), new Date(1997, 7, 6));
        Person p2 = new Person("p2", new Date(1768, 9, 10), new Date(1876, 10, 8));
        Person p3 = new Person("Cho", new Date(1894, 2, 8), new Date(1997, 7, 6));

        Doctor d1 = new Doctor("Yeonjin", new Date(2002, 5,29), null, "TheGlory");
        Doctor d2 = new Doctor("Yeonjin", new Date(2002, 5,29), null, "TheGlory");
        Doctor d3 = new Doctor("Yong", new Date(2010, 3, 1), new Date(2011, 5,2), "Asan");

        Patient pa1 = new Patient("pa1", new Date(2003, 5, 30), null, "internal");
        Patient pa2 = new Patient("pa2", new Date(2004, 6, 1), null, "surgery");

        Physician ph1 = new Physician("ph1", new Date(1998, 11, 8), null, "internal");

        if(p1.equals(p2)){
            System.out.println("p1 and p2 are same person! Wow");
        }
        else{
            System.out.println("p1 and p2 are different person");
        }

        if(p1.equals(p3)){
            System.out.println("p1 and p3 are same person! Wow");
        }
        else{
            System.out.println("p1 and p3 are different person");
        }

        if(d1.equals(d2)){
            System.out.println("d1 and d2 are same doctor! Wow");
        }
        else{
            System.out.println("d1 and d2 are different doctor");
        }

        if(d2.equals(d3)){
            System.out.println("d2 and d3 are same doctor! Wow");
        }
        else{
            System.out.println("d2 and d3 are different doctor");
        }

        System.out.println();
        ph1.examination(pa1);
        ph1.examination(pa2);
        d1.examination(pa1);
        System.out.println();

        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println();
        System.out.println(d1.toString());
        System.out.println(d2.toString());
        System.out.println(d3.toString());
        System.out.println();
        System.out.println(pa1.toString());
        System.out.println(pa2.toString());
        System.out.println();
        System.out.println(ph1.toString());

    }
}
