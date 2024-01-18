import java.io.*;
import java.util.Scanner;


public class DecryptionMain {
        public static char[] russianAlphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', ',', '.', '!', '?'};

        public static void main(String[] args) {
            System.out.print("Введите путь к файлу для дешифровки: ");
            String filePath = new Scanner(System.in).nextLine();

            try (FileInputStream fileInputStream = new FileInputStream(filePath);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }


                String decryptedContent = encrypt(content.toString(), -3);


                String outputFilePath = filePath.replace("_encrypted.txt", "_decrypted.txt");
                try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                    fileWriter.write(decryptedContent);
                    System.out.println("Файл успешно дешифрован и сохранен по пути: " + outputFilePath);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static String encrypt(String input, int shift) {
            StringBuilder result = new StringBuilder();

            for (char ch : input.toCharArray()) {
                if (Character.isLetter(ch) || ch == ' ' || ch == '\n') {
                    for (int i = 0; i < russianAlphabet.length; i++) {
                        if (Character.toLowerCase(ch) == russianAlphabet[i]) {
                            int decryptedIndex = (i - shift + russianAlphabet.length) % russianAlphabet.length;
                            char decryptedChar = (Character.isUpperCase(ch)) ? Character.toUpperCase(russianAlphabet[decryptedIndex]) : russianAlphabet[decryptedIndex];
                            result.append(decryptedChar);
                            break;
                        }
                    }
                } else {
                    result.append(ch);
                }
            }
            return result.toString();
        }
    }


