import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;


public class Validate {
    public boolean checkDirectory(String directoryPath) {
        File file = new File(directoryPath);
        return file.isDirectory();
    }

    public boolean checkFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    public void processFile(String inputFilePath, String outputFilePath, int key, Encryptor encryptor, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            int character;

            while ((character = reader.read()) != -1) {
                char processedChar = encrypt ? encryptor.shiftChar((char) character, key) : encryptor.shiftChar((char) character, -key);
                writer.write(processedChar);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла: " + inputFilePath);
        }
    }

    public static int keyInt(Scanner console) {
        while (true) {
            try {
                return Integer.parseInt(console.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Некорректный ввод. Введите целое число: ");
            }
        }
    }
}
