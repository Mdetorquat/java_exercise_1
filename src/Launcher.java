import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue !!!");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<Command> commandList = new ArrayList<>();
        commandList.add(new Quit());
        commandList.add(new Fibo());
        commandList.add(new Freq());
        commandList.add(new Predict());
        boolean stop = false;
        boolean test = false;

        do {
            for (Command command : commandList)
            {
                if (s.equals(command.name()))
                {
                    stop = command.run(scanner);
                    test = false;
                    break;
                }
                else
                    test = true;
            }
            if (test)
                System.out.println("Unknown command");

            if (!stop)
                s = scanner.nextLine();
        }while (!stop);
    }
}
