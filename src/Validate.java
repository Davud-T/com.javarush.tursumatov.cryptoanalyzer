import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Validate {
    public static void processFile(String inputFilePath, String outputFilePath, int key, Encryptor encryptor, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(inputFilePath).toAbsolutePath().toString()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(outputFilePath).toAbsolutePath().toString()))) {
            int character;
            while ((character = reader.read()) != -1) {
                char processedChar = encrypt ? encryptor.shiftChar((char) character, key) : encryptor.shiftChar((char) character, -key);
                writer.write(processedChar);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла: " + inputFilePath);
        }
    }

    public static int keyInt(Scanner console){
        while (true){
            try {
                return Integer.parseInt(console.nextLine());
            }
            catch (NumberFormatException nfe){
                System.out.println("Некорректный ввод. Введите целое число: ");
            }
        }
    }
}
