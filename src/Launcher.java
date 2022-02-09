import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        String var1 = "quit";
        Scanner clavier = new Scanner(System.in);
        System.out.println("Bienvenue");
        String var2 = clavier.nextLine();
        while (!var2.equals(var1)) {
            System.out.println("Unknown command");
            var2 = clavier.nextLine();
        }

        clavier.close();
    }
}
