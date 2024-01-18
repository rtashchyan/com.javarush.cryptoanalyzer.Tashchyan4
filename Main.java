import java.io.*;
import java.util.Scanner;


        public class Main{
            public static char[] russianAlphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', ',', '.', '!', '?'};

 
            public static void main(String[] args) {


                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите путь к файлу: ");
                String filePath = scanner.nextLine();

                try (FileInputStream fileInputStream = new FileInputStream(filePath);
                     InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {


                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    String encryptedContent = encrypt(content.toString(), 3); 


                    String outputFilePath = filePath.replace(".txt", "_encrypted.txt");
                    try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                        fileWriter.write(encryptedContent);
                        System.out.println("Файл успешно зашифрован и сохранен по пути: " + outputFilePath);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private static String encrypt(String input, int shift) {
                StringBuilder result = new StringBuilder();

                for (char ch : input.toCharArray()) {
                    if (Character.isLetter(ch) || ch == ' ' || ch == '\n') {
                        for (int i = 0; i <  russianAlphabet.length; i++) {
                            if (Character.toLowerCase(ch) == russianAlphabet[i]) {
                                int encryptedIndex = (i + shift) % russianAlphabet.length;
                                char encryptedChar = (Character.isUpperCase(ch)) ? Character.toUpperCase(russianAlphabet[encryptedIndex]) : russianAlphabet[encryptedIndex];
                                result.append(encryptedChar);
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



