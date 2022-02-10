import java.util.Scanner;

public class Quit implements Command {
    @Override
    public String name() {
        return "quit";
    }

    @Override
    public boolean function(Scanner scanner) {
        return false;
    }

    @Override
    public boolean run(Scanner scanner) {
        return true;
    }
}
