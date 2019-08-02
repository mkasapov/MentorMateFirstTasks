package reflectionTask;

public class Student {

    private String firstName;
    private String lastName;
    private int age;
    private double avgGrade;

    public Student() {
    }

    public Student(String firstName, String lastName, int age, double avgGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.avgGrade = avgGrade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }
}
