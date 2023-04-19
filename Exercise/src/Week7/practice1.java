package Week7;

import java.util.Scanner;

public class practice1 {
    public static void moving(int[][] a, int[] index, int[] num){
        int halfLen = a.length/2;
        if(index[1] < halfLen){
            if(index[0] <= halfLen){ // 오른쪽
                index[1]++;
                while(index[1] < a.length && a[index[0]][index[1]] == 0){ // j가 boundary 안일 때까지
                    num[0]++;
                    a[index[0]][index[1]] = num[0];
                    index[1]++;
                }
                index[1]--;
            }
            else{ // 위
                index[0]--;
                while(index[0] >= 0 && a[index[0]][index[1]] == 0){
                    num[0]++;
                    a[index[0]][index[1]] = num[0];
                    index[0]--;
                }
                index[0]++;
            }
        }
        else{
            if(index[0] < halfLen){ // 아래
                index[0]++;
                while(index[0] < a.length && a[index[0]][index[1]] == 0){
                    num[0]++;
                    a[index[0]][index[1]] = num[0];
                    index[0]++;
                }
                index[0]--;
            }
            else { // 왼쪽
                index[1]--;
                while(index[1] >= 0 && a[index[0]][index[1]] == 0){
                    num[0]++;
                    a[index[0]][index[1]] = num[0];
                    index[1]--;
                }
                index[1]++;
            }
        }
    }

    public static int[][] drawSnail(int n){
        int[][] a = new int[n][n];
        a[0][0] = 1;

        int[] num = new int[1]; // 값 변경 reference로 받으려고 array로 만든거
        num[0] = 1;

        int[] index = new int[2]; // i, j 담을 것.

        while(num[0] < n*n){
            //오른쪽, 아래, 왼쪽, 위쪽 반복
            moving(a, index, num);
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.print("Enter a size: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] a = drawSnail(n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.printf("%-3d ", a[i][j]);
            }
            System.out.println();
        }
    }
}
