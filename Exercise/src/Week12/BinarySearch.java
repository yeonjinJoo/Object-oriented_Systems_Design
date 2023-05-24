package Week12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearch {
    public static int binarySearch(int[] array, int start, int end, int x){
        int middle;
        if(start <= end){
            middle = (start + end) / 2;
            if(array[middle] == x){
                return middle;
            }
            if(array[middle] > x){
                return binarySearch(array, start, middle - 1, x);
            }
            if(array[middle] < x){
                return binarySearch(array, middle + 1, end, x);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] targets = new int[100];
        int[] array = new int[100000];

        Scanner sc = null;
        try{
            sc = new Scanner(new FileInputStream("input.txt"));
        }
        catch (FileNotFoundException e){
            System.out.println("Problem opening files.");
            System.exit(0);
        }

        for(int i = 0; i < 100; i++){
            targets[i] = sc.nextInt();
        }
        for(int i = 0; i < 100000; i++){
            array[i] = sc.nextInt();
        }

        for(int i = 0; i < 100; i++){
            System.out.print("target: " + targets[i] + "  ");
            int index  = binarySearch(array, 0, 99999, targets[i]);
            if(index != -1){
                System.out.println("index: " + index);
            }
            else{
                System.out.println("index: Not Found");
            }
        }

    }
}
