package Week4;

import java.util.Scanner;

public class Practice1 {
    public static boolean isPalindrome(String s){
        for(int i = 0; i < s.length()/2; i++){
            if(s.charAt(i) == s.charAt(s.length() - i - 1)){
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Input string S: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int max = 1;
        int maxIndex = 0;
        String temp = "";

        for(int i = 0; i < s.length(); i++){
            for(int j = s.length(); j > i; j--){
                temp = s.substring(i, j);
                if(isPalindrome(temp)){
                    if(temp.length() > max) {
                        max = temp.length();
                        maxIndex = i;
                        continue;
                    }
                }
            }
        }
        System.out.printf("Longest Palindrome: %s\n", s.substring(maxIndex, maxIndex+max));
    }
}
