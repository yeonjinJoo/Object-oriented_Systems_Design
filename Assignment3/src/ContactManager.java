
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager{
    private MyStorage<ContactInfo> contactStorage;
    private CommandLineInterface cli;

    public class MyStorage<T>{
        private int size;
        // ArrayList에 담을까??
        // 각 계약들을 각자 여기에 담을 것
        private int current;
        private ArrayList<T> normalC, clubC, departmentC;

        public MyStorage() {
            // size -1로 초기화. 무한이면 0 된다
            size = -1;
            current = 0;
            normalC = new ArrayList<T>();
            clubC = new ArrayList<T>();
            departmentC = new ArrayList<T>();
        }

        public ArrayList<T> getNormalC() {
            return normalC;
        }

        public ArrayList<T> getClubC() {
            return clubC;
        }

        public ArrayList<T> getDepartmentC() {
            return departmentC;
        }
    }

    public MyStorage<ContactInfo> getContactStorage() {
        return contactStorage;
    }

    public CommandLineInterface getCli() {
        return cli;
    }

    public ContactManager(CommandLineInterface cli){
        this.cli = cli;
        contactStorage = new MyStorage<ContactInfo>();
    }

    public void setStorageSize() throws NumberChoiceException {
        int size = cli.getSetSizeMenu();
        if(size != 0 && size < contactStorage.current){
            throw new NumberChoiceException("Number is smaller than total number of currently stored contacts.");
        }
        contactStorage.size = size;
    }

    public boolean find(ContactInfo temp, ArrayList<ContactInfo> array){
        for(ContactInfo c: array){
            if(c.getClass() == temp.getClass() && temp.equals(c)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<ContactInfo> isEixstInFile(Scanner in, String toWhere){
        String line;
        String name;
        String phoneNum;
        int index;
        ArrayList<ContactInfo> infoInFile = new ArrayList<>();

        while(in.hasNextLine()){
            line = in.nextLine();
            name = line.substring(line.indexOf(":")+2, line.indexOf("/")-1);
            phoneNum = line.substring(line.indexOf("/") + 16, line.lastIndexOf("/") - 1);


            index = line.lastIndexOf("/") + 2;
            ContactInfo temp = null;
            if(line.charAt(index) == 'r'){
                String relation = line.substring(index + 10);
                temp = new NormalContact(name, phoneNum, relation);
            }
            else if(line.charAt(index) == 'c'){
                String clubName = line.substring(index + 11);
                temp = new ClubContact(name, phoneNum, clubName);
            }
            else if(line.charAt(index) == 'd'){
                String department = line.substring(index + 12);
                temp = new DepartmentContact(name, phoneNum, department);
            }

            if(toWhere.equals("File") && temp != null){
                // 그냥 file에 있는 모든 info 저장해서 반환 용도, saveToFile 에 사용
                infoInFile.add(temp);
            }
            else if(toWhere.equals("Storage") && temp!=null && (!find(temp, contactStorage.normalC)) && (!find(temp, contactStorage.clubC)) && (!find(temp, contactStorage.departmentC))){
                // 존재하지 않으면 추가, loadFromFile에 사용
                infoInFile.add(temp);
            }
        }
        return infoInFile;
    }

    public void saveToFile(Scanner in, PrintWriter out){

        ArrayList<ContactInfo> infoInFile = isEixstInFile(in, "File");

        for(int i = 0; i < contactStorage.normalC.size(); i++){
            NormalContact temp = (NormalContact) contactStorage.normalC.get(i);
            if(infoInFile.size() == 0 || !(find(temp, infoInFile))){
                out.println("name: " + temp.name +" / phone number: " + temp.phoneNum+" / relation: " + temp.getRelation());
            }
        }

        for(int i = 0; i < contactStorage.clubC.size(); i++){
            ClubContact temp = (ClubContact) contactStorage.clubC.get(i);
            if(infoInFile.size() == 0 || !(find(temp, infoInFile))){
                out.println("name: " + temp.name +" / phone number: " + temp.phoneNum+" / club name: " + temp.getClubName());
            }
        }

        for(int i = 0; i < contactStorage.departmentC.size(); i++){
            DepartmentContact temp = (DepartmentContact) contactStorage.departmentC.get(i);
            if(infoInFile.size() == 0 || !(find(temp, infoInFile))){
                out.println("name: " + temp.name +" / phone number: " + temp.phoneNum+" / club name: " + temp.getDepartment());
            }
        }
    }

    public void loadFromFile(Scanner in) throws StorageException {
        ArrayList<ContactInfo> infoInfile = isEixstInFile(in, "Storage");
        for(int i = 0; i < infoInfile.size(); i++){
            createContact(infoInfile.get(i));
        }
    }

    public void createContact(ContactInfo c) throws StorageException {
        if(contactStorage.size == -1){
            // size 설정 안하고 createContact 하려 했다는 Exception 띄우기
            throw new StorageException("Storage size is not initialized.");
        }
        // 0인경우는 무한이라서 무적이다
        else if(contactStorage.size == 0 || contactStorage.current < contactStorage.size){
            // 안가지고 있을 때만 추가
            if(c instanceof NormalContact && !find(c, contactStorage.normalC)){
                contactStorage.normalC.add(c);
            }
            else if(c instanceof ClubContact && !find(c, contactStorage.clubC)){
                contactStorage.clubC.add(c);
            }
            else if(c instanceof DepartmentContact && !find(c, contactStorage.departmentC)){
                contactStorage.departmentC.add(c);
            }

            // 이미 존재할 때
            else{
                throw new StorageException("Storage already has same contact.");
            }

            contactStorage.current++;
        }
        else{
            // size 넘어갔다는 Exception 띄우기
            throw new StorageException("Storage is full.");
        }
    }

    // 변경된 ArrayList return.
    // Main에서 ArrayList 선언해주고, size 1 될때까지 이거 돌린다
    // !!!!!!!!!!!!! 고치자. 이거 걍 이 안에서 하나 찾을때까지 돌려도 되겠는데
    public ContactInfo searchContact(String numAndInfo) throws Exception {
        ArrayList<ContactInfo> searched = new ArrayList<>();

        // 처음은 0. 못찾았다는 뜻이 아님
        int time = 0;

        do{
            if(time != 0){
                if(searched.size() == 0){
                    throw new ContactNotFoundException();
                }
                System.out.println("Enter other number to single out your contact");
                numAndInfo = cli.getSearchContactMenu();
            }
            int num = Integer.parseInt(numAndInfo.substring(0, 1));
            String variable = numAndInfo.substring(2);

            if(num == 1){ // name
                // 추린게 2개 이상이라 다시 입력 받은 경우 size != 0
                if(searched.size() != 0){
                    // 추린거 전체에서 name 다른거 전부 삭제
                    searched.removeIf(item-> !(item.name.equals(variable)));
                }
                else{
                    for(ContactInfo c : contactStorage.normalC){
                        if(c.name.equals(variable)){
                            searched.add(c);
                        }
                    }
                    for(ContactInfo c : contactStorage.clubC){
                        if(c.name.equals(variable)){
                            searched.add(c);
                        }
                    }
                    for(ContactInfo c : contactStorage.departmentC){
                        if(c.name.equals(variable)){
                            searched.add(c);
                        }
                    }
                }
            }
            else if(num == 2){ // phone number
                if(searched.size() != 0){
                    searched.removeIf(item-> !(item.phoneNum.equals(variable)));
                }
                else{
                    for(ContactInfo c : contactStorage.normalC){
                        if(c.phoneNum.equals(variable)){
                            searched.add(c);
                        }
                    }
                    for(ContactInfo c : contactStorage.clubC){
                        if(c.phoneNum.equals(variable)){
                            searched.add(c);
                        }
                    }
                    for(ContactInfo c : contactStorage.departmentC){
                        if(c.phoneNum.equals(variable)){
                            searched.add(c);
                        }
                    }
                }
            }
            else if(num == 3){ // relation
                if(searched.size() != 0){
                    searched.removeIf(item-> !(item instanceof NormalContact));
                    searched.removeIf(item-> !(((NormalContact)item).getRelation().equals(variable)));
                }
                else{
                    NormalContact n;
                    for(ContactInfo c : contactStorage.normalC){
                        // normalC에는 normalcontact만 저장해뒀으므로 다운캐스팅 문제 X
                        n = (NormalContact)c;
                        if(n.getRelation().equals(variable)){
                            searched.add(c);
                        }
                    }
                }
            }
            else if(num == 4){ // club num
                if(searched.size() != 0){
                    searched.removeIf(item-> !(item instanceof ClubContact));
                    searched.removeIf(item-> !(((ClubContact)item).getClubName().equals(variable)));
                }
                else{
                    ClubContact n;
                    for(ContactInfo c : contactStorage.clubC){
                        n = (ClubContact) c;
                        if(n.getClubName().equals(variable)){
                            searched.add(c);
                        }
                    }
                }
            }
            else if(num == 5){ // department
                if(searched.size() != 0){
                    searched.removeIf(item-> !(item instanceof DepartmentContact));
                    searched.removeIf(item-> !(((DepartmentContact)item).getDepartment().equals(variable)));
                }
                else{
                    DepartmentContact n;
                    for(ContactInfo c : contactStorage.departmentC){
                        n = (DepartmentContact) c;
                        if(n.getDepartment().equals(variable)){
                            searched.add(c);
                        }
                    }
                }
            }
            time++;
        }while(searched.size() != 1);

        System.out.printf("Searched | name: %s / phone number: %s / ", searched.get(0).name, searched.get(0).phoneNum);
        if(searched.get(0) instanceof NormalContact){
            System.out.println("relation: "+ ((NormalContact)searched.get(0)).getRelation());
        }
        else if(searched.get(0) instanceof ClubContact){
            System.out.println("club name: "+ ((ClubContact)searched.get(0)).getClubName());
        }
        else if(searched.get(0) instanceof DepartmentContact){
            System.out.println("department: "+ ((DepartmentContact)searched.get(0)).getDepartment());
        }
        return searched.get(0);
    }

    public void deleteContact() throws Exception {
        String numAndInfo = cli.getDeleteContactMenu();
        ContactInfo c = searchContact(numAndInfo);

        if(c instanceof NormalContact){
            NormalContact n = (NormalContact)c;
            contactStorage.normalC.removeIf(item -> item.equals(n));
        }
        else if(c instanceof ClubContact){
            ClubContact n = (ClubContact)c;
            contactStorage.clubC.removeIf(item-> item.equals(n));
        }
        else if(c instanceof DepartmentContact){
            DepartmentContact n = (DepartmentContact)c;
            contactStorage.departmentC.removeIf(item-> item.equals(n));
        }

        System.out.println("Deleted Successfully");
    }

    public void editContact() throws Exception {
        System.out.println("To Edit contact, searching is needed");
        String numAndInfo = cli.getSearchContactMenu();
        ContactInfo c = searchContact(numAndInfo);

        int index = -1; // search 완료 했기 때문에 Index -1 들어가는 일은 없다
        if(c instanceof NormalContact){
            NormalContact temp = (NormalContact) c;
            for(int i = 0; i < contactStorage.normalC.size(); i++){
                // 어차피 equals 할 때 알아서 downcasting 해준다
                ContactInfo n = contactStorage.normalC.get(i);
                if(temp.equals(n)){
                    index = i;
                    break;
                }
            }
            String editInfo = cli.getEditContactMenu(3);
            if(Integer.parseInt(editInfo.substring(0, 1)) == 1){
                contactStorage.normalC.get(index).name = editInfo.substring(2);
            }
            else if(Integer.parseInt(editInfo.substring(0, 1)) == 2){
                contactStorage.normalC.get(index).phoneNum = editInfo.substring(2);
            }
            else if(Integer.parseInt(editInfo.substring(0, 1)) == 3){
                ((NormalContact)contactStorage.normalC.get(index)).setRelation(editInfo.substring(2));
            }
        }
        else if(c instanceof ClubContact){
            ClubContact temp = (ClubContact) c;
            for(int i = 0; i < contactStorage.clubC.size(); i++){
                ContactInfo n = contactStorage.clubC.get(i);
                if(temp.equals(n)){
                    index = i;
                    break;
                }
            }
            String editInfo = cli.getEditContactMenu(4);
            if(Integer.parseInt(editInfo.substring(0, 1)) == 1){
                contactStorage.clubC.get(index).name = editInfo.substring(2);
            }
            else if(Integer.parseInt(editInfo.substring(0, 1)) == 2){
                contactStorage.clubC.get(index).phoneNum = editInfo.substring(2);
            }
            else if(Integer.parseInt(editInfo.substring(0, 1)) == 3){
                ((ClubContact)contactStorage.normalC.get(index)).setClubName(editInfo.substring(2));
            }
        }
        else if(c instanceof DepartmentContact){
            DepartmentContact temp = (DepartmentContact) c;
            for(int i = 0; i < contactStorage.departmentC.size(); i++){
                ContactInfo n = contactStorage.departmentC.get(i);
                if(temp.equals(n)){
                    index = i;
                    break;
                }
            }
            String editInfo = cli.getEditContactMenu(4);
            if(Integer.parseInt(editInfo.substring(0, 1)) == 1){
                contactStorage.departmentC.get(index).name = editInfo.substring(2);
            }
            else if(Integer.parseInt(editInfo.substring(0, 1)) == 2){
                contactStorage.departmentC.get(index).phoneNum = editInfo.substring(2);
            }
            else if(Integer.parseInt(editInfo.substring(0, 1)) == 3){
                ((DepartmentContact)contactStorage.normalC.get(index)).setDepartment(editInfo.substring(2));
            }
        }

        System.out.println("Edited Successfully");
    }

}
