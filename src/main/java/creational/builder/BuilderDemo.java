package creational.builder;

import lombok.Data;

import java.util.Objects;

public class BuilderDemo {
    public static void main(String[] args) {
        Person person = Person.PersonBuilder.builder().firstName("ranjan").lastName("mondal").age(25)
                .liveAt().city("Dayalpur").state("Delhi").city("Kolkata").lastName("kumar")
                .build();
        System.out.println(person);
    }
}


@Data
class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Address address;

    @Data
    private static class Address {
        private String state;
        private String city;
    }

    public static class PersonBuilder {
        protected Person person;
        private PersonBuilder(){
            this.person = new Person();
        }

        public static PersonBuilder builder(){
            return new PersonBuilder();
        }

        public PersonBuilder firstName(String firstName){
            person.setFirstName(firstName);
            return this;
        }

        public PersonBuilder lastName(String lastName){
            person.setLastName(lastName);
            return this;
        }
        public PersonBuilder age(int age){
            person.setAge(age);
            return this;
        }

        public AddressBuilder liveAt(){
            if(Objects.isNull(person.getAddress())) {
                person.setAddress(new Address());
            }

            return new AddressBuilder(person);
        }

        public Person build(){
            return person;
        }
    }

    public static class AddressBuilder extends PersonBuilder {

        public AddressBuilder(Person person){
            this.person = person;
        }

        public AddressBuilder city(String city){
            this.person.getAddress().city = city;
            return this;
        }

        public AddressBuilder state(String state){
            this.person.getAddress().state = state;
            return this;
        }
    }
}

