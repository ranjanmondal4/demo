package patterns.chainOfCommand;

import java.util.Objects;

class Weight {
    int weight;
    Weight(int weight){
        this.weight = weight;
    }
}

class Command {
    int w;
    Weight weight;
    Command next;
    private Command(Weight weight, int w){
        this.weight = weight;
        this.w = w;
    }

    public static Command of(Weight weight, int w){
        return new Command(weight, w);
    }

    public void addCommand(Command other){
        if(Objects.isNull(next))
            next = other;
        else {
            Command current = next;
            while(!Objects.isNull(current.next)){
                current = current.next;
            }
            current.next = other;
        }
    }

    public void operate(){
        this.weight.weight += w;
        if(!Objects.isNull(this.next)){
            this.next.operate();
        }
    }

}
public class ChainOfCommandDemo {
    public static void main(String[] args) {
        Weight weight = new Weight(2);
        Command command = Command.of(weight, 10);
        command.addCommand(Command.of(weight, 20));
        command.operate();
        System.out.println(weight.weight);
    }
}
