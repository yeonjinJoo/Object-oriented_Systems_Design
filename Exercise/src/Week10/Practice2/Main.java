package Week10.Practice2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] grid = new int[10][10];
        int[] nums = new int[22]; // 22개의 중복 없는 index 뽑기
        for(int i = 0; i < 22; i++){
            boolean isExist = false;
            int temp = (int)(Math.random()*100);
            for(int j = 0; j < i; j++){
                if(nums[j] == temp){
                    isExist = true;
                    break;
                }
            }
            if(!(isExist)){
                nums[i] = temp;
            }
            else{
                i--;
            }
        }

        for(int i = 0; i < 22; i++){
            int index = nums[i];
            if(i < 20){
                grid[index/10][index%10] = 1; // ants는 1로 설정
            }
            else{
                grid[index/10][index%10] = 11; // doodlebugs는 11로 설정
            }
        }

        Organism org = new Organism();
        Doodlebug db = new Doodlebug();
        Ant ant = new Ant();
        Scanner sc = new Scanner(System.in);

        org.printMap(grid);

        while(ant.antNum != 0 && db.doodleNum != 0){
            System.out.print("Press the enter: ");
            if("".equals(sc.nextLine()) || "\n".equals(sc.nextLine())){
                for(int i = 0; i < grid.length; i++){
                    for(int j = 0; j < grid[0].length; j++){
                        if(db.move(grid, j, i) == 1){
                            ant.antNum--;
                        }
                    }
                }
                db.breed(grid);
                db.starve(grid);
                for(int i = 0; i < grid.length; i++){
                    for(int j = 0; j < grid[0].length; j++){
                        ant.move(grid, j, i);
                    }
                }
                ant.breed(grid);
                org.printMap(grid);
            }
        }
    }
}
