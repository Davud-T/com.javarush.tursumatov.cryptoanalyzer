import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Encryptor encryptor = new Encryptor();
        Validate validate = new Validate();
        BruteForce bruteForce = new BruteForce();

        while (true){
            System.out.println("ШИФР ЦЕЗАРЯ \uD83D\uDD12\uD83D\uDD11\uD83C\uDFDB\uFE0F\nВыберите операцию: \uD83D\uDE0A");
            System.out.println("1. Шифрование \uD83D\uDD12");
            System.out.println("2. Дешифрование \uD83D\uDD13");
            System.out.println("3. Взлом методом Brute Force \uD83D\uDD11");
            System.out.println("0. Выход \uD83D\uDEAA");
            String choice = console.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Введите путь к файлу для шифрования: \uD83D\uDCC4");
                    String filePathToEncrypt = console.nextLine();
                    System.out.println("Введите ключ (целое число): \uD83D\uDD11");
                    int key = validate.keyInt(console);
                    System.out.println("Введите путь к директории для сохранения зашифрованного файла: \uD83D\uDCC1");
                    String directoryPathEncrypt = console.nextLine();
                    String inputFileNameEncrypt = new File(filePathToEncrypt).getName();
                    String outputFileNameEncrypt = inputFileNameEncrypt.substring(0, inputFileNameEncrypt.lastIndexOf('.')) + "_encrypted.txt";
                    String outputFilePathEncrypt = Paths.get(directoryPathEncrypt, outputFileNameEncrypt).toAbsolutePath().toString();
                    validate.processFile(filePathToEncrypt, outputFilePathEncrypt, key, encryptor, true);
                    System.out.println("Текст зашифрован и сохранен в файл: " + outputFilePathEncrypt);
                    break;
                case "2":
                    System.out.println("Введите путь к файлу для дешифрования: \uD83D\uDCC4");
                    String filePathToDecrypt = console.nextLine();
                    System.out.println("Введите ключ (целое число): \uD83D\uDD11");
                    key = validate.keyInt(console);
                    System.out.println("Введите путь к директории для сохранения дешифрованного файла: \uD83D\uDCC1");
                    String directoryPathDecrypt = console.nextLine();
                    String inputFileNameDecrypt = new File(filePathToDecrypt).getName();
                    String outputFileNameDecrypt = inputFileNameDecrypt.substring(0, inputFileNameDecrypt.lastIndexOf('.')) + "_decrypted.txt";
                    String outputFilePathDecrypt = Paths.get(directoryPathDecrypt, outputFileNameDecrypt).toAbsolutePath().toString();
                    validate.processFile(filePathToDecrypt, outputFilePathDecrypt, key, encryptor, false);
                    System.out.println("Текст дешифрован и сохранен в файл: " + outputFilePathDecrypt);
                    break;
                case "3":
                    System.out.println("Введите путь к файлу для взлома: \uD83D\uDCC4");
                    String filePathToBruteForce = console.nextLine();
                    System.out.println("Введите путь к директории для сохранения файлов с перебором ключей: \uD83D\uDCC1");
                    String directoryPath = console.nextLine();
                    bruteForce.bruteForceFile(filePathToBruteForce, directoryPath);
                    break;
                case "0":
                    System.out.println("Завершение работы программы. \uD83D\uDC4B");
                    console.close();
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова. ⚠\uFE0F");
            }
        }
    }
}
