package lambdas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Track {
    private String name;
    private int length;
}
