package reflection;

import lombok.Data;

@Data
public class GameConfig {
    private int releaseYear;
    private String gameName;
    private double price;
    private String[] charactersName;
}
