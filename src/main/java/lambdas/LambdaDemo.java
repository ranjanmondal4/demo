package lambdas;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class LambdaDemo {
    public static void main(String[] args) {

//        Stream.of(Arrays.asList("1a", "b", "1hello"), Arrays.asList("1a", "b", "1hello")).flatMap(Collection::stream)
//                .forEach(System.out::println);

        List<Track> tracks = Arrays.asList(Track.of("Bakai", 524),
                Track.of("Violets for Your Furs", 378),
                Track.of("Time Was", 451));
        tracks.stream().mapToInt(Track::getLength).reduce(0, (acc, track) -> acc + track);
    }
}
