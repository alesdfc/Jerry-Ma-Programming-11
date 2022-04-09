import java.util.ArrayList;

public class School {

    // Array lists
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> courses = new ArrayList<>();

    // Fields for courses
    private String name;
    private String address;
    private int budget = 5000000;

    // Constructor
    School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Get and set
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getBudget() {
        return budget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    // Adds and deletes teachers from the array list
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void deleteTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    // Shows all the teachers
    public void showTeachers() {
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(teachers.get(i));
        }
    }

    // Adds and removes students from the arraylist
    public void addStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (student.getStudentId() == i) {
                System.out.println("ID already taken");
                return;
            }
        }
        students.add(student);
    }

    public void deleteStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == studentId) {
                students.remove(i);
            }
        }
    }

    // Shows all the students
    public void showStudents() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }
}
