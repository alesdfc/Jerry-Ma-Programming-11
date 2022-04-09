public class Teacher {

    // Fields
    private String firstName;
    private String lastName;
    private String subject;

    // Constructor to make a teacher object
    Teacher(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    // Returns the name and subject the teacher has
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + " Subject: " + this.subject;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Teacher)) {
            return false;
        }
        Teacher teachers = (Teacher) o;
        if (this.firstName == teachers.firstName && this.lastName == teachers.lastName
                && this.subject == teachers.subject) {
            return true;
        } else {
            return false;
        }
    }
}
