public class DepartmentContact extends ContactInfo{
    private String department;

    public DepartmentContact(String name, String phoneNum, String department){
        super(name, phoneNum);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public ContactInfo getInfo() {
        return new DepartmentContact(name, phoneNum, department);
    }

    public String toString(){
        return "name: "+ name + " / phone number: " + phoneNum + " / department: "+ department;
    }

    public boolean equals(Object object){
        DepartmentContact n = (DepartmentContact) object;

        if(n.name.equals(this.name) && n.phoneNum.equals(this.phoneNum) && n.department.equals(this.department)){
            return true;
        }
        return false;
    }
}
