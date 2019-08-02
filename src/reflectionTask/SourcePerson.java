package reflectionTask;

public class SourcePerson {

    private String name;
    private int age;
    private String address;
    private short numberOfChildren;

    private Student student;
    private Example example;

    public SourcePerson() {
    }

    public SourcePerson(String name, int age, String address, short numberOfChildren, Student student, Example example) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.numberOfChildren = numberOfChildren;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public short getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(short numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
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

