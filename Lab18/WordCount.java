import java.util.*;
import java.io.File;

public class WordCount {
    Map<String, Integer> counter;

    public WordCount(String filename) {
        String text = readFile(filename);
        if (text == null)
            text = filename;
        counter = countWords(text);
    }

    public int getWordCount(String word) {
        sanitize(word);
        Integer count = counter.get(word);
        return (count != null) ? count : 0;
    }

    public Set<String> getDistinctWords() {
        return counter.keySet();
    }

    public int countDistinctWords() {
        return counter.size();
    }

    private Map<String, Integer> countWords(String text) {
        Map<String, Integer> ret = new ListMap<String, Integer>();
        String[] words = text.split(" ");
        for (String word : words) {
            word = word.trim();
            word = sanitize(word);

            if (!word.isEmpty()) { // Skip empty strings
                if (ret.containsKey(word)) {
                    ret.put(word, ret.get(word) + 1);
                } else {
                    ret.put(word, 1);
                }
            }
        }
        return ret;
    }


    private String readFile(String filename) {
        try {
            Scanner in = new Scanner(new File(filename));
            StringBuilder ret = new StringBuilder();
            while (in.hasNextLine())
                ret.append(in.nextLine()).append("\n");
            return ret.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private String sanitize(String str) {
        str = str.toLowerCase();
        String legal = "qwertyuioplkjhgfdsazxcvbnm'";
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            String letter = str.substring(i, i + 1);
            if (legal.indexOf(letter) > -1)
                ret.append(letter);
        }
        return ret.toString();
    }
}
