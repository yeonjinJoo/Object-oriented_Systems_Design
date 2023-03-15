package Week2;

public class Practice2 {
    public static void main(String[] args) {
        String input = "Walt Savitch";
        input = input.toLowerCase();
        String output = null;

        int index = input.indexOf(" ");
        String word = input.substring(0, index);
        word = word.substring(1) + word.substring(0, 1);
        word = word.substring(0,1).toUpperCase() + word.substring(1) + "ay";
        output = word + " ";

        word = input.substring(index+1);
        word = word.substring(1) + word.substring(0, 1);
        word = word.substring(0,1).toUpperCase() + word.substring(1) + "ay";
        output += word;

        System.out.println(output);
    }
}
