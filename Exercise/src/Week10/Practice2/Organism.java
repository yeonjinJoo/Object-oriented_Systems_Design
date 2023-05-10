package Week10.Practice2;

public class Organism {

    public int move(int[][] grid, int x, int y){
        int where = (int)(Math.random()*4);
        if(where == 0){ // UP
            if(y - 1 >= 0 && grid[y-1][x] == 0){ // 자리 있고 비어있으면
                grid[y-1][x] = grid[y][x] + 1;
                grid[y][x] = 0;
            }
            else{
                grid[y][x]++; // 그자리 그대로에서 한 번 살아남은 거 표시
            }
        }
        else if(where == 1){ // DOWN
            if(y+1 <= 9 && grid[y+1][x] == 0){
                grid[y+1][x] = grid[y][x] + 1;
                grid[y][x] = 0;
            }
            else{
                grid[y][x]++;
            }
        }
        else if(where == 2){ // LEFT
            if(x - 1 >= 0 && grid[y][x-1] == 0){
                grid[y][x-1] = grid[y][x] + 1;
                grid[y][x] = 0;
            }
            else{
                grid[y][x]++;
            }
        }
        else{ // RIGHT
            if(x+1 <= 9 && grid[y][x+1] == 0){
                grid[y][x+1] = grid[y][x] + 1;
                grid[y][x] = 0;
            }
            else{
                grid[y][x]++;
            }
        }
        return 1; // 그냥 아무 값 넣은거
    }

    public void printMap(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    System.out.print("ㅡ ");
                }
                else if(grid[i][j] > 10){
                    System.out.print("X "); // Doodlebugs
                }
                else{
                    System.out.print("o "); // Ants
                }
            }
            System.out.println();
        }
    }
}
