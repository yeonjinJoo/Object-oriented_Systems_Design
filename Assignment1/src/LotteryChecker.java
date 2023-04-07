public class LotteryChecker {
    private int[] rank;
    private LotteryGenerator lotto;
    private int[] numWins;

    public LotteryChecker(LotteryGenerator lotto){
        rank = new int[lotto.getTickets().length];
        this.lotto = lotto;
        numWins = new int[4];
    }

    public void checkRank(){
        int[] target = lotto.getTarget();
        int[][] tickets = lotto.getTickets();

        int bonusNum = target[6];

        for(int i = 0; i < tickets.length; i++){ //i는 티켓 index
            int sameNum = 0;
            boolean bonusNumExist = false;
            for(int j = 0; j < 6; j++){ //j는 target index
                for(int k = 0; k < 6; k++){ //k는 i번째 티켓의 numIndex
                    if(tickets[i][k] == bonusNum){
                        bonusNumExist = true;
                    }
                    if(target[j] == tickets[i][k]){
                        sameNum++;
                    }
                }
            }

            if(sameNum == 6 || (sameNum == 5 && bonusNumExist)){
                rank[i] = 1;
            }
            else if(sameNum == 5 || (sameNum == 4 && bonusNumExist)){
                rank[i] = 2;
            }
            else if(sameNum == 4){
                rank[i] = 3;
            }
            else if(sameNum == 3){
                rank[i] = 4;
            }
            else{
                rank[i] = 0; // Lose
            }
        }
    }

    public void printTicketRank(){
        System.out.print(">> Round Winning number : ");
        int[] targets = lotto.getTarget();
        int[][] tickets = lotto.getTickets();

        for(int i = 0; i < 7; i++){
            if(i == 6){
                System.out.printf("+ %d\n", targets[i]);
            }
            else{
                System.out.printf("%d ", targets[i]);
            }
        }

        for(int i = 0; i < tickets.length; i++){
            System.out.print(">> Lottery number : ");
            for(int j = 0; j < 6; j++){
                System.out.printf("%d ", tickets[i][j]);
            }
            if(rank[i] == 0){
                System.out.println("Lose");
            }
            else if(rank[i] == 1){
                System.out.println("Winner (1st place)");
                numWins[0]++;
            }
            else if(rank[i] == 2){
                System.out.println("Winner (2nd place)");
                numWins[1]++;
            }
            else if(rank[i] == 3){
                System.out.println("Winner (3rd place)");
                numWins[2]++;
            }
            else if(rank[i] == 4){
                System.out.println("Winner (4th place)");
                numWins[3]++;
            }
        }
    }

    public void printRank(){
        System.out.printf(">> 1st place: %d\n", numWins[0]);
        System.out.printf(">> 2nd place: %d\n", numWins[1]);
        System.out.printf(">> 3rd place: %d\n", numWins[2]);
        System.out.printf(">> 4th place: %d\n", numWins[3]);
    }

}
