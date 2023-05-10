package Week10.Practice2;

public class Ant extends Organism{
    int antNum;
    public Ant(){
        super();
        antNum = 20;
    }

    @Override
    public int move(int[][]grid, int x, int y) {
        super.move(grid, x, y);
        return 1;
    }

    public void decreaseAnt(){
        antNum--;
    }

    public void breed(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 4){ // 애 낳아야지
                    if(i - 1 >= 0 && grid[i-1][j] == 0){ // 자리 있고 비어있으면 애 낳음
                        grid[i-1][j] = 1;
                        antNum++; // 애 마리수 추가
                    }
                    else if(i+1 <= 9 && grid[i+1][j] == 0){
                        grid[i+1][j] = 1;
                        antNum++;
                    }
                    else if(j - 1 >= 0 && grid[i][j-1] == 0){
                        grid[i][j-1] = 1;
                        antNum++;
                    }
                    else if(j+1 <= 9 && grid[i][j+1] == 0){
                        grid[i][j+1] = 1;
                        antNum++;
                    }
                    grid[i][j] = 1; // 애 낳은 상태로 초기화
                }
            }
        }
    }
}
