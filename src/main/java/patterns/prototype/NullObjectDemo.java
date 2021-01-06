package patterns.prototype;

import lombok.ToString;
import org.springframework.data.util.Pair;

@ToString
class Student {
    String firstName;
    String lastName;

    Log log;

    public Student(String firstName, String lastName, Log log) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.log = log;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.log.info(this.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.log.info(this.lastName);
    }
}

interface Log {
    void info(String msg);
    void warn(String msg);
}

class ConsoleLog implements Log {

    @Override
    public void info(String msg) {
        System.out.println("Info :: " + msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("Warn :: " + msg);
    }
}

class NullLog implements Log {

    @Override
    public void info(String msg) {
    }

    @Override
    public void warn(String msg) {
    }
}

public class NullObjectDemo {
    public static void main(String[] args) {
        Log console = new ConsoleLog();
        Log nullLog = new NullLog();
        Student student = new Student("jane", "smith", nullLog);
        student.setFirstName("Joe");
    }
}
