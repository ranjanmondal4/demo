import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

    public static void main(String ...args) throws Exception {
        Stream<Person> personStream = Stream.of(Person.of("ranjan", 20), Person.of("arif", 30),
                Person.of("rohit", 25));

        List<Person> persons = personStream.sorted((a, b) -> b.getAge() - a.getAge()).collect(Collectors.toList());
        Collections.addAll(persons, Person.of("faizan", 25), Person.of("rizvi", 40));
//        System.out.println(persons.size());

        List<Person> destination = Arrays.asList(Person.of("a", 20), Person.of("b", 30),
                Person.of("c", 25),Person.of("d", 20), Person.of("e", 30),
                Person.of("f", 25));
        Collections.copy(destination, persons);

        Person max = Collections.min(persons, Comparator.comparingInt(Person::getAge));
        System.out.println(max);
//        destination.stream().forEach(System.out::println);
//        System.out.println(destination.size());



    }


}
