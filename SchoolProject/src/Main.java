public class Main {
    public static void main(String[] args) {
        String[] firstname = { "John", "Moe", "Lorem", "Ispum", "Bob", "Bethany", "Impostor", "Kade", "Johnny",
                "Poggy" };
        String[] lastname = { "Doe", "Chan", "Kujo", "Kira", "World", "Ice", "Fire", "Water", "Issac", "Test",
                "Woggy" };
        int[] grade = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        String[] subjects = { "Science", "Math", "English", "History" };
        School school = new School("Testing High", "12758492371");
        for (int i = 0; i < 10; i++) {
            school.addStudent(new Student(firstname[i], lastname[i], grade[i]));
        }

        for (int i = 0; i < 3; i++) {
            school.addTeacher(new Teacher(lastname[i], firstname[i], subjects[i]));
        }

        school.showStudents();
        school.showTeachers();

        school.deleteStudent(3);
        school.deleteStudent(5);
        school.deleteTeacher(new Teacher("Chan", "Moe", "Math"));

        school.showStudents();
        school.showTeachers();
    }
}