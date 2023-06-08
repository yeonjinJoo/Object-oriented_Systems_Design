import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineInterface {
    private Scanner scanner;
    private int num;

    public CommandLineInterface(){
        scanner = new Scanner(System.in);
        num = 0;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    // numberException인지 아닌지 판단해주는 method
    public void isNumberChoiceException(int num, int min, int max) throws NumberChoiceException {
        if(num < min || num > max){
            throw new NumberChoiceException("Number is out of range");
        }
    }

    // 반복되는거 방지..
    public String selectOne(int num) throws Exception {
        if(num == 1){
            System.out.print("Name: ");
        }
        else if(num == 2){
            System.out.print("Phone Number: ");
        }
        else if(num == 3){
            System.out.print("Relation: ");
        }
        else if(num == 4){
            System.out.print("Club Name: ");
        }
        else if(num == 5){
            System.out.print("Department: ");
        }
        String variable = scanner.next();
        if(variable.length() < 3 || variable.length() > 20){
            throw new Exception("[Exception] Characters must be consist of 3 to 20 characters.");
        }

        if(num == 2){
            if(!(variable.length() == 13 && variable.substring(0, 4).equals("010-") && variable.charAt(8)=='-')){
                throw new InputMismatchException();
            }
            // 숫자가 아닌 무언가가 껴있을수도 있으니까
            Integer.parseInt(variable.substring(4, 8));
            Integer.parseInt(variable.substring(9, 13));
        }
        return variable;
    }

    public int getMainMenu() throws NumberChoiceException {
        System.out.println("\n----<Main Menu>----");
        System.out.println("1. Set size of total contacts");
        System.out.println("2. Save the current contact to a file");
        System.out.println("3. Load the saved contact file");
        System.out.println("4. Register new contact");
        System.out.println("5. Search contact\n6. Delete contact");
        System.out.println("7. Edit contact\n8. View All Contacts\n9. Exit");
        System.out.print("Select: ");

        num = scanner.nextInt();

        isNumberChoiceException(num, 1, 9);
        return num;
    }

    public int getSetSizeMenu() throws NumberChoiceException {
        System.out.print("Enter storage size: ");
        num = scanner.nextInt();
        // 음수 들어올 때 Exception 처리 필요
        if(num < 0){
            throw new NumberChoiceException("Number should be positive");
        }
        return num;
    }

    public ContactInfo getCreateContactMenu() throws Exception {
        System.out.println("Create contact...");
        System.out.println("1. normal contact");
        System.out.println("2. club contact");
        System.out.println("3. department contact");
        System.out.print("Select: ");

        num = scanner.nextInt();
        // num이 valid한지 체크
        isNumberChoiceException(num, 1, 3);

        System.out.println("Enter information...");
        String name = selectOne(1);
        String phoneNum = selectOne(2);
        if(num == 1){
            String relation = selectOne(3);
            return new NormalContact(name, phoneNum, relation);
        }
        else if(num == 2){
            String clubName = selectOne(4);
            return new ClubContact(name, phoneNum, clubName);
        }
        else{
            String department = selectOne(5);
            return new DepartmentContact(name, phoneNum, department);
        }
    }

    public String getSearchContactMenu() throws Exception {
        System.out.println("Search contact...");
        System.out.println("Choose the variable");
        System.out.println("1. name\n2. phone number\n3. relation");
        System.out.println("4. club name\n5. department");
        System.out.print("Select: ");
        // 3, 4, 5 같은 경우에는 contact가 여러개 있을수도 있겠다
        // 여러개일 경우에는 그냥 다 보여주자

        num = scanner.nextInt();
        isNumberChoiceException(num, 1, 5);

        String numAndInfo = num + " ";
        numAndInfo += selectOne(num);
        return numAndInfo;
    }

    public String getDeleteContactMenu() throws Exception {
        System.out.println("Delete contact...");
        System.out.println("Choose the variable");
        System.out.println("1. name\n2. phone number\n3. relation");
        System.out.println("4. club name\n5. department");
        System.out.print("Select: ");

        num = scanner.nextInt();
        isNumberChoiceException(num, 1, 5);

        String numAndInfo = num + " ";
        numAndInfo += selectOne(num);
        return numAndInfo;
    }

    public String getEditContactMenu(int contactType) throws Exception {
        System.out.println("Edit contact...");
        System.out.println("Choose the variable");
        System.out.println("1. name\n2. phone number");

        if(contactType == 3){ // NormalContact
            System.out.println("3. relation");
        }
        else if(contactType == 4){ // ClubContact
            System.out.println("3. club name: ");
        }
        else if(contactType == 5){ //DepartmentContact
            System.out.println("3. department: ");
        }
        System.out.print("Select: ");

        num = scanner.nextInt();
        isNumberChoiceException(num, 1, 3);

        String numAndInfo = num + " ";
        numAndInfo += selectOne(num);
        return numAndInfo;
    }

    public void printContactInfo(ContactManager manager){
        System.out.println("Normal Contacts");
        ContactManager.MyStorage<ContactInfo> storage = manager.getContactStorage();
        for(int i = 0; i < storage.getNormalC().size(); i++){
            NormalContact temp = (NormalContact)storage.getNormalC().get(i);
            System.out.printf("    %d. name: %s / phone number: %s / relation: %s\n",
                    i+1, temp.name, temp.phoneNum, temp.getRelation() );
        }

        System.out.println("Club Contacts");
        for(int i = 0; i < storage.getClubC().size(); i++){
            ClubContact temp = (ClubContact) storage.getClubC().get(i);
            System.out.printf("    %d. name: %s / phone number: %s / club name: %s\n",
                    i+1, temp.name, temp.phoneNum, temp.getClubName() );
        }

        System.out.println("Department Contacts");
        for(int i = 0; i < storage.getDepartmentC().size(); i++){
            DepartmentContact temp = (DepartmentContact)storage.getDepartmentC().get(i);
            System.out.printf("    %d. name: %s / phone number: %s / relation: %s\n",
                    i+1, temp.name, temp.phoneNum, temp.getDepartment() );
        }
    }

    public void printErrorMessage(String message){
        System.out.println(message);
    }
}
