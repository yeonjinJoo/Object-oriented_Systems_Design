package Week2;

import java.math.BigDecimal;

public class Practice1 {
    public static void main(String[] args) {
        String name = "Yeonjin Joo";
        double studentID = 2022011012;
        BigDecimal big = new BigDecimal(studentID);
        name = name.toUpperCase();

        System.out.println("My name is \"" + name + "\"\nand my studentID is "+ big);
    }
}
