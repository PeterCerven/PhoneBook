package phonebook;

public class Person implements Comparable<Person> {
    private Integer number;
    private String name;

    public Person(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person p) {
        if (this.getName().compareTo(p.getName()) > 0) {
            return 1;
        }
        if (this.getName().compareTo(p.getName()) < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
