package generics;

import com.amazonaws.services.dynamodbv2.xspec.M;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
class Person {
    private String name;
    private int age;
    private Address address;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}

@Data
@AllArgsConstructor(staticName = "of")
class Address {
    private String street;
}

public class GenericDemo {

    @SneakyThrows
    public static void main(String[] args) {
        Class<String> stringClass = String.class;
        Class<?> squareClass = Class.forName("generics.GenericDemo$Square");
        Class<HashMap> hashMapClass = HashMap.class;

        //printClassInfo(stringClass, squareClass, hashMapClass);

//        printConstructor(Person.class);

        /*Person person = createObjectWithArguments(Person.class, new Object[]{"Joe", 29, Address.of("Nyork")});
        System.out.println(person);*/

//        Movie movie = new Movie("Lord of the Rings", 2010, 15.99, true, Category.ACTION);
//        getFieldsDeclared(Movie.class, movie);

        int[] intArray = {1, 2, 4};
        int[][] int2dArray = {{1,2,3}, {4,5,6}};
        printArray(intArray);
        printArray(int2dArray);
    }


    private static void printArray(Object array){
        int arrayLength = Array.getLength(array);
        System.out.print("[");
        for(int i=0; i<arrayLength; i++){
            Object element = Array.get(array, i);
            if(element.getClass().isArray())
                printArray(element);
            else
                System.out.print(element);

            if(i != arrayLength-1){
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    @SneakyThrows
    private static <T> void getFieldsDeclared(Class<? extends T> clazz, T instance){
        for(Field field : clazz.getDeclaredFields()){
            System.out.println(String.format("Field Name %s, Type name %s, value %s \n",
                    field.getName(), field.getType().getName(), field.get(instance)));
        }
    }

    private static class Movie extends Product {

        public static final double MIN_PRICE = 10.99;
        public boolean isReleased;
        public Category category;
        public double actualPrice;

        public Movie(String name, int year, double price, boolean isReleased, Category category) {
            super(name, year);
            actualPrice = Math.max(price, MIN_PRICE);
            this.category = category;
            this.isReleased = isReleased;
        }
    }

    private static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }

    private static enum Category {
        ADVENTURE, ACTION, COMEDY;
    }

    @SneakyThrows
    private static <T> T createObjectWithArguments(Class<T> clazz, Object ... args){
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()){
            if(constructor.getParameterTypes().length == args.length){
                return (T) constructor.newInstance(args);
            }
        }
        System.out.println("Intended class with correct constructor not found");
        return null;
    }

    private static void printConstructor(Class<?> clazz){
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.out.println(String.format("Class name : %s and number of constructor : %s", clazz.getSimpleName(), constructors.length));

        for(Constructor<?> constructor : constructors){
            Class<?>[] parameters = constructor.getParameterTypes();
            List<String> prs = Arrays.stream(parameters).map(Class::getSimpleName).collect(Collectors.toList());
            System.out.println("parameters " + prs);
        }
    }

    private static void printClassInfo(Class<?> ... classes){
        for(Class<?> clazz : classes){
            System.out.println(String.format("Class name %s , and package name %s", clazz.getSimpleName(),
                    clazz.getPackage().getName()));

            Class<?> [] implementedInterfaces = clazz.getInterfaces();
            for(Class<?> implementedInterface : implementedInterfaces) {
                System.out.println(String.format("Interface name %s , and package name %s", implementedInterface.getSimpleName(),
                        implementedInterface.getPackage().getName()));
            }

            System.out.println("Is Array : " + clazz.isArray());
            System.out.println("Is primitive : " + clazz.isPrimitive());
            System.out.println("Is Enum : " + clazz.isEnum());
            System.out.println("Is Interface : " + clazz.isInterface());
            System.out.println("Is anonymous : " + clazz.isAnonymousClass());

            System.out.println("\n");
        }
    }

    private static class Square implements Drawable{
        @Override
        public int getNoOfCorners() {
            return 4;
        }
    }
    private static interface Drawable {
        int getNoOfCorners();
    }
}
