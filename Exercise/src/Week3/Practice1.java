package Week3;

import java.util.Scanner;

public class Practice1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(",|\\n");
        int dividend = sc.nextInt();
        int divisor = sc.nextInt();

        System.out.printf("dividend: %d\n", dividend);
        System.out.printf("divisor: %d\n", divisor);
        System.out.printf("quotient: %d\n", dividend/divisor);
        System.out.printf("remainder: %d\n", dividend % divisor);
    }
}
