package Week14.Practice1;

import java.util.ArrayList;

public class Eratos {
    ArrayList<Integer> numbers;

    public Eratos(int n){
        numbers = new ArrayList<Integer>();
        for(int i = 2; i <= n; i++){
            numbers.add(i);
        }
    }

    public ArrayList<Integer> sieve(int n){
        ArrayList<Integer> primes = new ArrayList<Integer>();

        int temp = -1;

        while(numbers.size() != 0){
            temp = numbers.remove(0);
            primes.add(temp); // 자동 Integer -> int 변환

            int i = 0;
            while(i < numbers.size()){
                if(numbers.get(i) % temp == 0){
                    numbers.remove(Integer.valueOf(numbers.get(i)));
                    i--;
                }
                i++;
            }
        }
        return primes;
    }
}
