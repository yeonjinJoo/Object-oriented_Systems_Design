public abstract class ContactInfo {
    public String name, phoneNum;

    public ContactInfo(String name, String phoneNum){
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public abstract ContactInfo getInfo();
    public abstract String toString();
    public abstract boolean equals(Object object);
}
