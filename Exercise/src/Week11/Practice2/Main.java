package Week11.Practice2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean done = false;
        while(!done){
            try{
                String ID;
                int account_num, balance;
                System.out.print("Enter ID, account number, and balance: ");

                Scanner sc = new Scanner(System.in);
                ID = sc.next();
                account_num = sc.nextInt();
                balance = sc.nextInt();

                Account a = new Account(ID, account_num, balance);
                System.out.println(a.toString());
                done = true;
            }
            catch (InputMismatchException e){
                System.out.println("[InputMismatchException] ID should be String, Account number and balance should be integer");
                System.out.println("Try again.\n");
            }
            catch (NumberFormatException e){
                System.out.println("[NumberFormatException] ID should be followed by three digits");
                System.out.println("Try again.\n");
            }
            catch (Exception e){
                System.out.println("[Exception] " + e.getMessage());
                System.out.println("Try again.\n");
            }
        }
    }
}
