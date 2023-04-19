package Week7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class practice2 {
    public static void main(String[] args) {
        ArrayList<Integer> cards = new ArrayList<>();
        for(int i = 1; i < 9; i++){
            cards.add(i);
            cards.add(i);
        }
        Collections.shuffle(cards);

        char[][] printArray = {
                {' ','1', '2','3','4'},
                {'1', '*', '*','*','*'},
                {'2', '*', '*','*','*'},
                {'3', '*', '*','*','*'},
                {'4', '*', '*','*','*'},
        };

        Scanner sc = new Scanner(System.in);
        int[] index = new int[4];


        int numCorrect = 0;
        while(true){
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.printf("%-3c ", printArray[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            if(numCorrect == 16){
                break;
            }
            System.out.print("Enter Coordinate: ");
            for(int i = 0; i < 4; i++){
                index[i] = sc.nextInt();
            }
            int left = (index[0]-1)*4 + index[1] - 1;
            int right = (index[2]-1)*4 + index[3] - 1;

            if(cards.get(left) == cards.get(right)) {
                System.out.printf("%d == %d\n",cards.get(left), cards.get(left));
                if(printArray[index[0]][index[1]] != '*') {
                    continue;
                }
                printArray[index[0]][index[1]] = (char)(cards.get(left)+'0');
                printArray[index[2]][index[3]] = (char)(cards.get(left)+'0');
                numCorrect += 2;
            }
            else{
                System.out.printf("%d != %d\n",cards.get(left), cards.get(right));
            }
        }


    }
}
