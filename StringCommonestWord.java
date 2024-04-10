import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StringCommonestWord {
    public static void main(String[] args) {
        String filename = "input.txt"; // Замініть на шлях до вашого файлу
        try {
            String commonestWord = commonestWord(filename);
            System.out.println("Найчастіше зустрічається слово: " + commonestWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String commonestWord(String filename) throws IOException {
        if (filename == null) {
            throw new NullPointerException("Файл не може бути null");
        }

        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase(); // Робимо всі слова в нижньому регістрі
                    int count = wordCountMap.getOrDefault(word, 0);
                    wordCountMap.put(word, count + 1);
                }
            }
        } catch (IOException e) {
            throw new IOException("Помилка читання файлу", e);
        }

        String commonestWord = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                commonestWord = entry.getKey();
            }
        }

        return commonestWord;
    }
}
