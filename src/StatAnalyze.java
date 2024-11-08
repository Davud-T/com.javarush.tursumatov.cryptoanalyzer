import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StatAnalyze {
    private final Encryptor encryptor = new Encryptor();

    public void analyzeFile(String filePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            HashMap<Character, Integer> frequencyMap = new HashMap<>();
            int character;
            while ((character = reader.read()) != -1) {
                char currentChar = (char) character;
                frequencyMap.put(currentChar, frequencyMap.getOrDefault(currentChar, 0) + 1);
            }

            char mostFrequentChar = ' ';
            int maxFrequency = 0;
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() > maxFrequency) {
                    mostFrequentChar = entry.getKey();
                    maxFrequency = entry.getValue();
                }
            }

            int assumedSpaceIndex = encryptor.getIndexInAlphabet(' ');
            int mostFrequentCharIndex = encryptor.getIndexInAlphabet(mostFrequentChar);
            int key = (mostFrequentCharIndex - assumedSpaceIndex + encryptor.ALPHABET.length) % encryptor.ALPHABET.length;

            try (BufferedReader readerToDecrypt = new BufferedReader(new FileReader(filePath));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                while ((character = readerToDecrypt.read()) != -1) {
                    char decryptedChar = encryptor.shiftChar((char) character, -key);
                    writer.write(decryptedChar);
                }
            }

            System.out.println("Анализ завершен. Результат сохранен в файл: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Ошибка при анализе файла: " + filePath);
        }
    }
}
