package Week11.Practice1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static boolean isCoprime(int a, int b) throws MyException{
        if(a == b){
            throw new MyException("Integers are same number; Change the numbers to different numbers");
        }
        else if(a <= 1 || b <= 1){
            throw new MyException("One of integers is less or equal to 1; Change the number larger than 1");
        }
        else if(a > 10000 && b > 10000){
            throw new MyException("Both integers are larger than 10000; Change the numbers smaller than 10000");
        }

        int num = 0;
        if(a <= b){
            for(int i = a; i > 0; i--){
                if(a % i == 0 && b % i == 0){
                    num = i;
                    break;
                }
            }
        }
        else{
            for(int i = b; i > 0; i--){
                if(a % i == 0 && b % i == 0){
                    num = i;
                    break;
                }
            }
        }
        if(num == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean done = false;

        while(!done){
            try{
                System.out.print("Enter two integers: ");
                int a = sc.nextInt();
                int b = sc.nextInt();

                if(isCoprime(a, b)){
                    System.out.println("Two numbers are coprime!");
                }
                else{
                    System.out.println("Two numbers are not coprime!");
                }
                done = true;
            }
            catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter integers!!!");
                System.out.println("Try again.\n");
            }
            catch (MyException e){
                System.out.println(e.getMesssage());
                System.out.println("Try again.\n");
            }

        }

    }
}
