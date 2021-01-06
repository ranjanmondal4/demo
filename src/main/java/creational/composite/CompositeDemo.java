package creational.composite;

import java.util.ArrayList;
import java.util.List;

class Employee {
    String name;
    List<Employee> employees;

    public Employee(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addJunior(Employee junior){
        employees.add(junior);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", employees=" + employees.stream().map(employee -> employee) +
                '}';
    }
}

public class CompositeDemo {

    public static void main(String[] args) {
        Employee abhimanyu = new Employee("Abhimanyu");
        Employee amit = new Employee("Amit");
        Employee ranjan = new Employee("ranjan");
        abhimanyu.addJunior(amit);
        abhimanyu.addJunior(ranjan);

        System.out.println(abhimanyu);
    }
}
