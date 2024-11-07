import java.io.*;
import java.nio.file.Paths;

public class BruteForce {
    private final Encryptor encryptor = new Encryptor();

    public void bruteForceFile(String filePath, String directoryPath) {
        try {
            for (int key = 0; key < encryptor.ALPHABET.length; key++) {
                try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(filePath).toString()))) {
                    String inputFileName = new File(filePath).getName();
                    String outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".")) + "_key_" + key + ".txt";
                    File outputFile = new File(Paths.get(directoryPath, outputFileName).toAbsolutePath().toString());
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                        int character;
                        while ((character = reader.read()) != -1){
                            char decryptedChar = encryptor.shiftChar((char) character, -key);
                            writer.write(decryptedChar);
                        }
                    }
                }
            }
            System.out.println("Файлы с результатами взлома сохранены в директорию: " + directoryPath);
        }
        catch (IOException e){
            System.out.println("Ошибка при чтении файла: " + filePath);
        }
    }
}
