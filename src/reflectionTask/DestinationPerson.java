package reflectionTask;

public class DestinationPerson {

    private String name;
    private int age;
    private int numberOfChildren;
    private String petName;
    private String middleName;

    private Student student;
    private Example example;

    public DestinationPerson() {
    }

    public DestinationPerson(String name, int age, int numberOfChildren, String petName, String middleName, Student student, Example example) {
        this.name = name;
        this.age = age;
        this.numberOfChildren = numberOfChildren;
        this.petName = petName;
        this.middleName = middleName;
        this.student = student;

        this.example = example;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
}
