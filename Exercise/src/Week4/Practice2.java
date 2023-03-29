package Week4;

import java.util.Scanner;

public class Practice2 {
    public static void main(String[] args) {
        int cscore = 0, yscore = 0;
        String whoseTurn = "You";
        Scanner sc = new Scanner(System.in);

        while(cscore < 100 && yscore < 100){
            int toAdd = 0,  temp = 0;
            if(whoseTurn.equals("You")){
                System.out.printf("(You) insert action r(roll): ");
                String action = sc.next();
                while(action.equals("r")){
                    temp = (int)(Math.random()*6+1);
                    System.out.printf("(You) dice rolled : %d\n", temp);
                    if(temp == 1){
                        System.out.println("(You) No score added");
                        break;
                    }
                    toAdd += temp;
                    System.out.printf("(You) insert action r(roll), h(hold) : ");
                    action = sc.next();
                }
                if(action.equals("h")){
                    System.out.printf("(You) %d added to You\n", toAdd);
                    yscore += toAdd;
                }
                System.out.printf("(You) Your score %d, Computer score %d\n", yscore, cscore);
                System.out.println();
                whoseTurn = "Computer";
            }
            else{
                System.out.println("(Computer) insert action r(roll): r");
                while(true){
                    temp = (int)(Math.random()*6+1);
                    System.out.printf("(Computer) dice rolled : %d\n", temp);
                    if(temp == 1){
                        System.out.println("(Computer) No score added");
                        break;
                    }
                    toAdd += temp;
                    if(!(toAdd < 20)){
                        break;
                    }
                    System.out.printf("(Computer) insert action r(roll), h(hold) : r\n");
                }
                if(temp != 1){
                    cscore += toAdd;
                    System.out.printf("(Computer) %d added to Computer\n", toAdd);
                }
                System.out.printf("(Computer) Your score %d, Computer score %d\n", yscore, cscore);
                System.out.println();
                whoseTurn = "You";
            }
        }
        if(cscore >= 100){
            System.out.printf("(Game) Your score %d, Computer score %d Sorry, the computer wins.\n", yscore, cscore);
        }
        else {
            System.out.printf("(Game) Your score %d, Computer score %d Congratulations! You win!\n", yscore, cscore);
        }

    }
}
