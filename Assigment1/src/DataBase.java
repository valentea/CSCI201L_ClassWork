import java.util.List;

public class DataBase {
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> tempStudents) {
        this.students = tempStudents;
    }

    public void addNewStudent(Name newStudentName) {
        Student newStudent = new Student(newStudentName);
        students.add(newStudent);
        System.out.println();
    }

    public void removeStudent(Student selectedStudent) {
        students.remove(selectedStudent);
        System.out.println();
    }

    public void addGrade(Student selectedStudent, Integer newGrade) {
        double newAverage = (selectedStudent.getAverage() * selectedStudent.getNumGrades()) + newGrade;
        newAverage = newAverage / (selectedStudent.getNumGrades() + 1);
        selectedStudent.setAverage(newAverage);
        int newNumberOfGrades = selectedStudent.getNumGrades();
        newNumberOfGrades++;
        selectedStudent.setNumGrades(newNumberOfGrades);
        System.out.println("New Average is: " + selectedStudent.getAverage() + "\n");
    }

    public void sort(int userSelection) {
        if (userSelection == 1) {
            students.sort(Student.studentNameComparator);
        } else if (userSelection == 2) {
            students.sort(Student::compareTo);
        }

    }

}
