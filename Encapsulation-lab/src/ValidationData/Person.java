package ValidationData;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        if (firstName.length() < 3) {
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        if (lastName.length() < 3) {
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    private void setAge(int age) {
        if (age < 1) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        if (salary < 460.00) {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.lastName = lastName;
        this.salary = salary;
    }

    public void increaseSalary(double bonus) {
        if (age < 30) {
            bonus = bonus / 2;
        }
        salary = salary * (1 + (bonus / 100));
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %.1f leva", firstName, lastName, salary);
    }
}
