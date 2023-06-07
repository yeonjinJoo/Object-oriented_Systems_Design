package Week14.Practice1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input max number: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Eratos e = new Eratos(n);
        ArrayList<Integer> prime = e.sieve(n);

        Iterator<Integer> itr = prime.iterator();
        while(itr.hasNext()){
            int temp = itr.next();
            System.out.print(temp + " ");
        }
        System.out.println();

    }
}
