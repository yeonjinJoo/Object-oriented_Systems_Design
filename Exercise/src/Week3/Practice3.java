package Week3;
import java.util.Scanner;

public class Practice3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] items = new String[3];
        int[] quantities = new int[3];
        double[] price = new double[3];

        for(int i = 0; i < 3; i++){
            System.out.printf("Enter name of item %d: \n", i+1);
            items[i] = sc.nextLine();
            System.out.printf("Enter quantity of item %d:\n", i+1);
            quantities[i] = sc.nextInt();
            System.out.printf("Enter price of item %d:\n", i+1);
            price[i] = sc.nextDouble();
            sc.nextLine();
        }
        System.out.println();

        System.out.printf("Your bill:\n");
        System.out.println();
        System.out.printf("%-30s%-10s%-10s%-10s\n", "ITEM", "QUANTITY", "PRICE", "TOTAL");
        double subtotal = 0, total, tax;
        for(int i = 0; i < 3; i++){
            total = quantities[i]*price[i];
            subtotal += total;
            System.out.printf("%-30s%-10d%-10.2f%-10.2f\n", items[i], quantities[i], price[i], total);
        }

        tax = subtotal*0.0625;
        System.out.println();
        System.out.printf("%-50s%-10.2f\n", "SUBTOTAL", subtotal);
        System.out.printf("%-50s%-10.2f\n", "6.25% SALES TAX", tax);
        System.out.printf("%-50s%-10.2f\n", "TOTAL", subtotal+tax);
    }
}
