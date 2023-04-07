public class LotteryGenerator {
    private int[] target;
    private int[][] tickets;

    public LotteryGenerator(int num){
        target = new int[7];
        tickets = new int[num][6];
    }

    public void numGenerator(int[] save){
        for(int i = 0; i < save.length; i++){
            boolean isExist = false;
            int temp = (int)(Math.random()*20)+1;
            for(int j = 0; j < i; j++){
                if(temp == save[j]){
                    isExist = true;
                    break;
                }
            }
            if(!(isExist)){
                save[i] = temp;
            }
            else{
                i--;
            }
        }
    }

    public void numSetting(){
        numGenerator(target); // target 만들기
        for(int i = 0; i < tickets.length; i++){
            numGenerator(tickets[i]);
        }
    }

    public int[] getTarget() {
        return target;
    }

    public int[][] getTickets() {
        return tickets;
    }
}
