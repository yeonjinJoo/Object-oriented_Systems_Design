package Week11.Practice2;

public class Account {
    private String ID;
    private int account_num;
    private int balance;

    public Account(String ID, int account_num, int balance) throws Exception, NumberFormatException {
        int asciiNUm = (int)ID.charAt(0);
        if(ID.length() != 4){
            throw new Exception("ID must start with letter and followed by three digits.");
        }
        else if(!((asciiNUm > 64 && asciiNUm < 91) || (asciiNUm > 96 && asciiNUm < 123))){
           throw new Exception("ID must start with letter");
        }

        Integer.parseInt(ID.substring(1, 4));

        if(!(account_num > 9999 && account_num < 100000)){
            throw new Exception("Account number must be of five digits.");
        }
        else if(balance <= 1000){
            throw new Exception("Initial balance must be above $1000");
        }

        this.ID = ID;
        this.account_num = account_num;
        this.balance = balance;
    }

    public String toString(){
        return "ID: " + ID + ", Account_num: " + account_num + ", Balance: " + balance;
    }

}
