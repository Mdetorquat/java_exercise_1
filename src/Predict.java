import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean function(Scanner scanner) {
        return false;
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Enter a file name");

        String s;
        try {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            s = scanner.nextLine();
            String string = Files.readString(Paths.get(s));
            String[] dico = string.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");

            for (int i = 0; i < dico.length - 1; i++) {
                String str = dico[i];
                String next = dico[i+1];

                Map<String, Integer> map2 = map.get(str);

                if(map2 == null) {
                    map2 = new HashMap<>();
                    map.put(str, map2);
                    map2.put(next, 1);
                    continue;
                }
                Integer j = map2.get(next);
                if (j == null)
                    map2.put(next, 1);
                else
                    map2.replace(next, j+1);
            }
            System.out.println("Enter a word : ");
            s = scanner.nextLine();
            if (!map.containsKey(s)) {
                System.out.println("This word is not in the text !");
                return false;
            }
            String next = s;
            StringBuilder builder = new StringBuilder();
            int len = 1;
            while (map.containsKey(next) && len++ < 20) {
                Map<String, Integer> map3 = map.get(next);
                Integer value = 0;
                map.remove(next);
                builder.append(" ").append(next);
                for(Map.Entry<String, Integer> e : map3.entrySet()) {
                    if(e.getValue() > value) {
                        next = e.getKey();
                        value = e.getValue();
                    }
                }
            }
            builder.append(' ').append(next);
            System.out.println("Most common sentence :" + builder);
        } catch (IOException e) {
            System.out.println("Unreadable file : " + e.getClass() + " " + e.getMessage());
        }
        return false;
    }
}
