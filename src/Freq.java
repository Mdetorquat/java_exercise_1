import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Freq implements Command{
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean function(Scanner scanner) {
        return false;
    }

    @Override
    public boolean run(Scanner scanner) {
        frequences(scanner.nextLine());
        return false;
    }

    private static void frequences(String file) {
        System.out.println("Enter a file name : ");
        String s = null;
        try {
            s = java.nio.file.Files.readString(Path.of(file));
        } catch (IOException e) {
            System.err.println("Unreadable file: " + e.getMessage());
        }

        assert s != null;
        s = Arrays.toString(new String[]{s.replaceAll("[^a-zA-Z ]", "").toLowerCase()});

        Map<String, Integer> frequences = new HashMap<>();
        List<String> most_used = new ArrayList<>();

        for (var i : s.split(" ")) {
            if (i.isBlank()) {
                continue;
            }

            frequences.putIfAbsent(i, 0);
            frequences.put(i, frequences.get(i) + 1);
        }

        while(most_used.size() < 3 && frequences.keySet().size() > 0) {
            int max_value = Collections.max(frequences.values());
            var key = frequences.keySet().stream().filter(j -> frequences.get(j) == max_value).toList();
            var last_value = key.get(key.size() - 1);
            most_used.add(last_value);
            frequences.remove(last_value);
        }
        System.out.println(String.join(" ", most_used));
    }

}
