package patterns.prototype;

import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

@ToString
class Address  implements Serializable{
    String state;
    String city;
    private Address(String state, String city){
        this.state = state;
        this.city = city;
    }

    public static Address of(String state, String city){
        return new Address(state, city);
    }

    public static Address of(Address other){
        return new Address(other.state, other.city);
    }
}

@ToString
class Employee implements Serializable {
    String firstName;
    String lastName;
    Address address;

    private Employee (String firstName, String lastName, Address address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public static Employee of(String firstName, String lastName, Address address){
        return new Employee(firstName, lastName, address);
    }

    public static Employee of(Employee other){
        return new Employee(other.firstName, other.lastName, Address.of(other.address));
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        Address firstAddress = Address.of("London", "DownTown Abbey");
//        Address secondAddress = Address.of(firstAddress);
//        secondAddress.city = "New York";
//
//        System.out.println(firstAddress);
//        System.out.println(secondAddress);
        Employee firstEmployee = Employee.of("Jane", "Smith", firstAddress);
        Employee secondEmployee = Employee.of(firstEmployee);
        System.out.println(firstEmployee);
        secondEmployee.address.city = "Byzantine";
        System.out.println(secondEmployee);

        Employee thirdEmployee = SerializationUtils.roundtrip(firstEmployee);
        thirdEmployee.address.city = "Mosul";
        System.out.println(thirdEmployee);
    }
}