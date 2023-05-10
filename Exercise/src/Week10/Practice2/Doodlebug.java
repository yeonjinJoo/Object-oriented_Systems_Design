package Week10.Practice2;

public class Doodlebug extends Organism{
    int[][] starving;
    int doodleNum;
    public Doodlebug(){
        super();
        starving = new int[10][10]; // 몇번 굶었는지 세주는 array
        doodleNum = 2;
    }

    @Override
    public int move(int [][] grid, int x, int y) {
        if(y - 1 >= 0 && grid[y][x] >=11 && grid[y-1][x] > 0 && grid[y-1][x] < 5){ // UP에 개미 존재
            grid[y-1][x] = grid[y][x] + 1;
            grid[y][x] = 0;
            return 1; // decreaseAnt 하라는 뜻.
        }
        else if(y+1 <= 9 && grid[y][x] >=11 && grid[y+1][x] > 0 && grid[y+1][x] < 5){ // DOWN
            grid[y+1][x] = grid[y][x]+1;
            grid[y][x] = 0;
            return 1;
        }
        else if(x - 1 >= 0 && grid[y][x] >=11 && grid[y][x-1] > 0 && grid[y][x-1] < 5){ // LEFT
            grid[y][x-1] = grid[y][x] + 1;
            grid[y][x] = 0;
            return 1;
        }
        else if(x+1 <= 9 && grid[y][x] >=11 && grid[y][x+1] > 0 && grid[y][x+1] < 5){ // RIGHT
            grid[y][x+1] = grid[y][x] + 1;
            grid[y][x] = 0;
            return 1;
        }
        else if(grid[y][x] >=11){
            super.move(grid, x, y);
            starving[y][x]++;
            return 0;
        }
        return 0;
    }

    public void breed(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 19){
                    if(i - 1 >= 0 && grid[i-1][j] == 0){ // 자리 있고 비어있으면 애 낳음
                        grid[i-1][j] = 11;
                        doodleNum++; // 애 마리수 추가
                    }
                    else if(i+1 <= 9 && grid[i+1][j] == 0){
                        grid[i+1][j] = 11;
                        doodleNum++;
                    }
                    else if(j - 1 >= 0 && grid[i][j-1] == 0){
                        grid[i][j-1] = 11;
                        doodleNum++;
                    }
                    else if(j+1 <= 9 && grid[i][j+1] == 0){
                        grid[i][j+1] = 11;
                        doodleNum++;
                    }
                    grid[i][j] = 11; // 애 낳은 상태로 초기화
                }
            }
        }
    }

    public void starve(int [][] grid){
        for(int i = 0; i < starving.length; i++){
            for(int j = 0; j < starving[0].length; j++){
                if(starving[i][j] == 3){
                    starving[i][j] = 0;
                    grid[i][j] = 0;
                    doodleNum--;
                }
            }
        }
    }
}
