import java.util.ArrayList;

public class Student {

    // Setting the fields in place
    private String firstName;
    private String lastName;
    private int grade;
    static int identification = 0;
    private int studentId;

    // Constructor that makes a student object
    Student(String firstName, String lastName, int grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = identification;
        this.grade = grade;
        identification++;
    }

    // Gives the full name and grade of a student
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + " Grade:" + this.grade;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        if (this.studentId == student.studentId) {
            return true;
        } else {
            return false;
        }
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGrade() {
        return grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
