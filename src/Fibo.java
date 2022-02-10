import java.util.Scanner;

public class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean function(Scanner scanner) {
        return false;
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Enter a positive number :");
        System.out.println(fibonacci(scanner.nextInt()));
        scanner.nextLine();

        return false;
    }

    public static int fibonacci(int n) {
        int fibo1 = 0;
        int fibo2 = 1;
        int fibo3 = 1;
        for (int i = 2; i <= n; i++) {
            fibo3 = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibo3;
        }
        return fibo3;
    }
}
