package creational.prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Person implements Serializable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class Address implements Serializable {
    String city;
    String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        Address address = new Address("Gotham", "Oklahoma");
        Person bruce = new Person("Bruce", address);
        Person alfred = SerializationUtils.roundtrip(bruce);
        alfred.name = "Alfred";

        System.out.println(bruce);
        System.out.println(alfred);
    }
}
