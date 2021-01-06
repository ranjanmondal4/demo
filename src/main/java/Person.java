import annotation.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
//@Course(cid = "D-112")
public class Person {
    private String name;
    private int age;
}
