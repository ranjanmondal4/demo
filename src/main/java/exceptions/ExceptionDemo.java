package exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ExceptionDemo {
    public static void main(String[] args) {
        paintAllBuildings(Arrays.asList(1, 3));
        try (Scanner scanner = new Scanner(new File("testRead.txt"));
             BufferedReader br = new BufferedReader(new FileReader("text.txt"))){

        }catch (Exception e){

        }
    }

    public static <T extends Number & Comparable> List<T> getListFromArray(T[] a){
        return Arrays.stream(a).collect(Collectors.toList());
    }

    public static List<? extends Integer> paintAllBuildings(List<? super String> buildings) {
            return buildings.stream().map(ele -> Integer.parseInt((String) ele))
                    .collect(Collectors.toList());
    }
}
