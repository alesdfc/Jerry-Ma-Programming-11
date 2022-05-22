public class Friend {

    public String name;
    public String phone;
    public String birthday;
    public String hobby;

    // Requires: string, string, string, string
    // Modifies: none
    // Effects: creates a friend object with given information
    Friend(String name, String phone, String birth, String hobby) {
        this.name = name;
        this.phone = phone;
        birthday = birth;
        this.hobby = hobby;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getHobby() {
        return hobby;
    }

}
