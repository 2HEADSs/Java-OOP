package person;

public class Child extends Person{


    public Child(String name, int age) {
        super(name, age);
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
