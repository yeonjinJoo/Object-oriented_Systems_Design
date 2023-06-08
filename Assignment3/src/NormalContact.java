public class NormalContact extends ContactInfo{
    private String relation;

    public NormalContact(String name, String phoneNum, String relation){
        super(name, phoneNum);
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public ContactInfo getInfo(){
        return new NormalContact(name, phoneNum, relation);
    }

    public String toString(){
        return "name: "+ name + " / phone number: " + phoneNum + " / relation: "+ relation;
    }

    public boolean equals(Object object){
        NormalContact n = (NormalContact)object;

        if(n.name.equals(this.name) && n.phoneNum.equals(this.phoneNum) && n.relation.equals(this.relation)){
            return true;
        }
        return false;
    }
}
