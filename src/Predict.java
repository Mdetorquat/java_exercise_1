import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command {
    private static class Word {
        private final String word;
        private final Map<String, Integer> occurences = new HashMap<>();

        public Word(String word) {
            this.word = word;
        }

        public void putFollower(String w) {
            this.occurences.put(w, this.occurences.getOrDefault(w, 0) + 1);
        }

        public String predict() {
            if (occurences.isEmpty())
                return null;

            var i = Collections.max(occurences.values());

            var list = this.occurences.keySet().stream().filter(k -> occurences.get(k).equals(i)).toList();

            return list.get(0);
        }
    }

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Enter a filename:");
        String s;
        try {
            s = Files.readString(Path.of(scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Unreadable file: " + e.getMessage());
            return false;
        }

        s = s.toLowerCase()
                .replaceAll("[.!?\\-'\"\t\n]", " ")
                .replaceAll(" {2}", " ");

        if (s.isBlank())
            return false;

        Map<String, Word> mapWords = new HashMap<>();

        var lastWord = Arrays.stream(s.split(" "))
                .filter(k -> !k.isBlank())
                .reduce("", (prev, next) -> {
                    if (!prev.isBlank()) {
                        mapWords.putIfAbsent(prev, new Word(prev));
                        mapWords.get(prev).putFollower(next);
                    }
                    return next;
                });

        mapWords.putIfAbsent(lastWord, new Word(lastWord));

        System.out.println("Enter a word :");
        var startWord = scanner.nextLine().toLowerCase();

        if (!mapWords.containsKey(startWord))
            System.err.println("Word not found in the text!");

        else {
            var sentence = new ArrayList<>(List.of(startWord));
            while (sentence.size() < 20) {
                var nextWord = mapWords.get(sentence.get(sentence.size() - 1)).predict();
                if (nextWord == null)
                    break;
                sentence.add(nextWord);
            }

            System.out.println(String.join(" ", sentence));
        }


        return false;
    }
}
