package lambdas;

import lombok.Data;

import java.util.Set;

@Data
public class Artist {
    private String name;
    private Set<Artist> members;
    private String origin;
}
