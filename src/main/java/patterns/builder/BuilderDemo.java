package patterns.builder;

import lombok.ToString;

@ToString
class Person {
    String firstName;
    String lastName;
    String does;
}

class PersonBuilder<SELF extends PersonBuilder<SELF>>{
    protected Person person = new Person();
    public SELF withFirstName(String name){
        person.firstName = name;
        return self();
    }
    public SELF withLastName(String name){
        person.lastName = name;
        return self();
    }

    protected SELF self(){
        return (SELF) this;
    }

    public Person build(){
        return person;
    }
}

class StudentBuilder extends PersonBuilder<StudentBuilder> {
    public StudentBuilder works(String work){
        person.does = work;
        return self();
    }

    @Override
    protected StudentBuilder self(){
        return this;
    }
}
@ToString
class Employee {
    String firstName;
    String lastName;
    String city;
    String street;
    String workAs;
    String workAt;
}

class EmployeeBuilder {
    Employee employee = new Employee();
    public EmployeeBuilder withFirstName(String name){
        employee.firstName = name;
        return this;
    }

    public EmployeeBuilder withLastName(String name){
        employee.lastName = name;
        return this;
    }

    public AddressBuilder liveAt(){
        return new AddressBuilder(employee);
    }

    protected Employee build(){
        return employee;
    }
}

class AddressBuilder extends EmployeeBuilder {
    AddressBuilder(Employee employee){
        this.employee = employee;
    }
    public AddressBuilder setStreet(String street){
        employee.street = street;
        return this;
    }

    public AddressBuilder setCity(String city){
        employee.city = city;
        return this;
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        Person person = new StudentBuilder()
                .withFirstName("Joe")
                .withLastName("Smith")
                .works("dfdafsa")
                .build();
        System.out.println(String.format("The person %s", person));

        Employee employee = new EmployeeBuilder()
                .withFirstName("Jane")
                .withLastName("Smith")
                .liveAt()
                    .setCity("New york")
                    .setStreet("Down Town")
                .withFirstName("Bruce")
                .build();

        System.out.println(String.format("The employee %s", employee));
    }
}
