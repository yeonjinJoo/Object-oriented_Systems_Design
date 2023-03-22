package Week3;

import java.util.Scanner;

public class Practice2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int percent = sc.nextInt();
        int GPA = (int)(((double)percent/100) * 4);
        System.out.printf("(%d/100)*4=%d\n", percent, GPA);
    }
}
