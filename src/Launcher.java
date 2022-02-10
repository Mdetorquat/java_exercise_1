import java.util.Scanner;

public class Launcher {
    public static void fibonacci(int n) {
        int fibo1 = 0;
        int fibo2 = 1;
        int fibo3 = 1;
        for (int i = 2; i <= n; i++) {
            fibo3 = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibo3;
        }
        System.out.print(fibo3);
    }
    public static void main(String[] args) {
        System.out.println("Bienvenue");
        String var1 = "quit";
        Scanner clavier = new Scanner(System.in);
        String var2 = clavier.nextLine();
        String var3 = "fibo";
        while (!var1.equals(var2)) {
            if (var3.equals(var2)) {
                int n = clavier.nextInt();
                fibonacci(n);
                break;
            }
            System.out.println("Unknown command");
            var2 = clavier.nextLine();
        }
        clavier.close();
    }
}
