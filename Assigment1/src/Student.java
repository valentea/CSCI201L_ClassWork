import java.util.Comparator;

public class Student implements Comparable<Student> {
    private Name name;
    private double average;
    private int numGrades;

    private Student() {
    }

    Student(Name newName) {
        this.setName(newName);
        this.setAverage(0);
        this.setNumGrades(0);
    }

    public Name getName() {
        return this.name;
    }

    public void setName(Name newName) {
        this.name = newName;
    }

    public double getAverage() {
        return this.average;
    }

    public void setAverage(double tempAve) {
        this.average = tempAve;
    }

    public int getNumGrades() {
        return this.numGrades;
    }

    public void setNumGrades(int tempNumGrade) {
        this.numGrades = tempNumGrade;
    }

    public int compareTo(Student compareStudent) {
        if (this.getAverage() < compareStudent.getAverage())
            return -1;
        else if (compareStudent.getAverage() < this.getAverage())
            return 1;
        return 0;


    }

    public static Comparator<Student> studentNameComparator
            = new Comparator<Student>() {

        public int compare(Student student1, Student student2) {
            String student1NameLast = student1.getName().getLname().toUpperCase();
            String student2NameLast = student2.getName().getLname().toUpperCase();

            if (student1NameLast.equals(student2NameLast)) {
                String student1NameFirst = student1.getName().getFname().toUpperCase();
                String student2NameFirst = student2.getName().getFname().toUpperCase();
                return student1NameFirst.compareTo(student2NameFirst);
            } else {
                return student1NameLast.compareTo(student2NameLast);
            }
        }
    };


    @Override
    public String toString() {
        return this.getName() + "||" + this.getAverage() + ", " + this.numGrades;
    }

}
