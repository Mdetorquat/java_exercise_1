import java.util.Scanner;

public interface Command {
    String name();
    boolean function(Scanner scanner);

    boolean run(Scanner scanner);
}
