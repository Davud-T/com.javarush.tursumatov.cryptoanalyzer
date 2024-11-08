import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Encryptor encryptor = new Encryptor();
        Validate validate = new Validate();
        BruteForce bruteForce = new BruteForce();
        StatAnalyze statAnalyze = new StatAnalyze();

        while (true) {
            System.out.println("ШИФР ЦЕЗАРЯ \uD83D\uDD12\uD83D\uDD11\uD83C\uDFDB\uFE0F\nВыберите операцию: \uD83D\uDE0A");
            System.out.println("1. Шифрование \uD83D\uDD12");
            System.out.println("2. Расшифровка текста с помощью ключа \uD83D\uDD13");
            System.out.println("3. Расшифровка текста с помощью brute force \uD83D\uDD11");
            System.out.println("4. Статистический анализ \uD83E\uDDE0");
            System.out.println("0. Выход \uD83D\uDEAA");
            String choice = console.nextLine();

            switch (choice) {
                case "1":
                    String filePathToEncrypt = getValidFilePath(validate, console, "Введите путь к файлу для шифрования: \uD83D\uDCC4");
                    System.out.println("Введите ключ (целое число): \uD83D\uDD11");
                    int key = validate.keyInt(console);
                    String directoryPathEncrypt = getValidDirectoryPath(validate, console, "Введите путь к директории для сохранения зашифрованного файла: \uD83D\uDCC1");
                    String inputFileNameEncrypt = new File(filePathToEncrypt).getName();
                    String outputFileNameEncrypt = inputFileNameEncrypt.substring(0, inputFileNameEncrypt.lastIndexOf('.')) + "_encrypted.txt";
                    String outputFilePathEncrypt = Paths.get(directoryPathEncrypt, outputFileNameEncrypt).toAbsolutePath().toString();
                    validate.processFile(filePathToEncrypt, outputFilePathEncrypt, key, encryptor, true);
                    System.out.println("Текст зашифрован и сохранен в файл: " + outputFilePathEncrypt);
                    break;

                case "2":
                    String filePathToDecrypt = getValidFilePath(validate, console, "Введите путь к файлу для дешифрования: \uD83D\uDCC4");
                    System.out.println("Введите ключ (целое число): \uD83D\uDD11");
                    key = validate.keyInt(console);
                    String directoryPathDecrypt = getValidDirectoryPath(validate, console, "Введите путь к директории для сохранения дешифрованного файла: \uD83D\uDCC1");
                    String inputFileNameDecrypt = new File(filePathToDecrypt).getName();
                    String outputFileNameDecrypt = inputFileNameDecrypt.substring(0, inputFileNameDecrypt.lastIndexOf('.')) + "_decrypted.txt";
                    String outputFilePathDecrypt = Paths.get(directoryPathDecrypt, outputFileNameDecrypt).toAbsolutePath().toString();
                    validate.processFile(filePathToDecrypt, outputFilePathDecrypt, key, encryptor, false);
                    System.out.println("Текст дешифрован и сохранен в файл: " + outputFilePathDecrypt);
                    break;

                case "3":
                    String filePathToBruteForce = getValidFilePath(validate, console, "Введите путь к файлу для взлома: \uD83D\uDCC4");
                    String directoryPath = getValidDirectoryPath(validate, console, "Введите путь к директории для сохранения файлов с перебором ключей: \uD83D\uDCC1");
                    bruteForce.bruteForceFile(filePathToBruteForce, directoryPath);
                    break;

                case "4":
                    String filePathToAnalyze = getValidFilePath(validate, console, "Введите путь к файлу для статистического анализа: 📄");
                    String directoryPathAnalyze = getValidDirectoryPath(validate, console, "Введите путь к директории для сохранения результата: 📁");
                    String inputFileNameAnalyze = new File(filePathToAnalyze).getName();
                    String outputFileNameAnalyze = inputFileNameAnalyze.substring(0, inputFileNameAnalyze.lastIndexOf('.')) + "_analyzed.txt";
                    String outputFilePathAnalyze = Paths.get(directoryPathAnalyze, outputFileNameAnalyze).toAbsolutePath().toString();
                    statAnalyze.analyzeFile(filePathToAnalyze, outputFilePathAnalyze);
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

    private static String getValidFilePath(Validate validate, Scanner scanner, String message) {
        String filePath;
        while (true) {
            System.out.println(message);
            filePath = scanner.nextLine();
            if (validate.checkFile(filePath)) {
                break;
            } else {
                System.out.println("Введен неверный путь к файлу. Попробуйте снова.");
            }
        }
        return filePath;
    }

    private static String getValidDirectoryPath(Validate validate, Scanner scanner, String message) {
        String directoryPath;
        while (true) {
            System.out.println(message);
            directoryPath = scanner.nextLine();
            if (validate.checkDirectory(directoryPath)) {
                break;
            } else {
                System.out.println("Введен неверный путь к директории. Попробуйте снова.");
            }
        }
        return directoryPath;
    }
}