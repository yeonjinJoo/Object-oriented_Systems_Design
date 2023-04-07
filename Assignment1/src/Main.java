import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int remainAmount = 0;
        int amount, ticketNum;
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.print("Input your money and number of lottery tickets:\n<< ");
            amount = sc.nextInt();
            ticketNum = sc.nextInt();
            if(amount == 0 || ticketNum == 0){
                System.out.println(">> End of program");
                break;
            }
            else if(10 * ticketNum > (remainAmount + amount)){
                continue;
            }

            remainAmount += amount - 10*ticketNum;
            LotteryGenerator lg = new LotteryGenerator(ticketNum);
            lg.numSetting(); // target & ticketNums 생성 완료

            LotteryChecker lc = new LotteryChecker(lg);
            lc.checkRank(); // rank check해서 lc 안에 저장

            lc.printTicketRank(); // 각각 티켓 몇등인지 출력

            System.out.printf(">> Remaining money : %d\n", remainAmount);

            lc.printRank(); // 각 등수 몇번 나왔는지 출력
            System.out.println();
        }
    }
}
