package solidDesignPrinciples;

import java.util.ArrayList;
import java.util.List;

class Journal {
    private List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text){
        entries.add("" + (++count) + " : " + text);
    }
    public void remove(int index){
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}
public class SingleResponsibilityPrinciple {

    public static void main(String ...args){
        Journal journal = new Journal();
        journal.addEntry("I cried today");
        journal.addEntry("I ate bug");
        System.out.println(journal);
    }
}
