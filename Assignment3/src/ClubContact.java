public class ClubContact extends ContactInfo{
    private String clubName;

    public ClubContact(String name, String phoneNum, String clubName){
        super(name, phoneNum);
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Override
    public ContactInfo getInfo() {
        return new ClubContact(name, phoneNum, clubName);
    }

    @Override
    public String toString() {
        return "name: "+ name + " / phone number: " + phoneNum + " / club name: "+ clubName;
    }

    public boolean equals(Object object){
        ClubContact n = (ClubContact) object;

        if(n.name.equals(this.name) && n.phoneNum.equals(this.phoneNum) && n.clubName.equals(this.clubName)){
            return true;
        }
        return false;
    }
}
