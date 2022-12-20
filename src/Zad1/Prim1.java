package Zad1;

import java.io.*;
import java.util.Scanner;

public class Prim1 {
    public static void main(String[] args) {
        try {
            File folder = new File("C:\\Games\\txt_files");
            if (!folder.exists())
                folder.mkdir();
            File f1 = new File("C:\\Games\\txt_files\\num1Mart.txt");
            f1.createNewFile();
            Scanner sc = new Scanner(System.in);
            System.out.print("Сколько чисел надо записать в файл? \n => ");
            long count = sc.nextLong(); // ввести количество чисел
            // Открыть файл одновременно для чтения и записи "rw"
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            System.out.println("Исходный размер файла в байтах =" + rf.length()
                    + ", указатель стоит на " + rf.getFilePointer() + "-м байте");

            System.out.println("Введите числа:");
            for (long i = 0; i < count; i++) {
                rf.writeLong(sc.nextLong()); // Записать числа в файл. На каждое
            } // число типа long приходится 8 байта
            System.out.println("Новый размер файла в байтах =" + rf.length()
                    + ", указатель стоит на " + rf.getFilePointer() + "-м байте");
            System.out.println("Количество байт на 1 число = " + rf.length() / count);
            rf.close();
            // Открыть файл только для чтения "r"
            rf = new RandomAccessFile(f1, "r");
            // Прочитать числа из файла и вывести на экран
            System.out.println("\n Числа в файле:");
            for (long i = 0; i < count; i++) { // i – текущий номер числа
                rf.seek(i * 8); // перевод указателя на текущее число  i* 4 байта
                // в данной ситуации при последовательном считывании
                // операцию seek() можно было не использовать
                System.out.println("Число" + i + ": " + rf.readLong());
            }
            System.out.println("Числа в обратном порядке:");
            for (long i = count - 1; i >= 0; i--) {
                rf.seek(i * 8); // операцию использовать обязательно!
                System.out.println("Число" + i + ": " + rf.readLong());
            }
            rf.seek(rf.getFilePointer() - 8); // перевод указателя на последнее число
            System.out.println(" Количество чисел в файле= " + rf.length()/8
                    + ", последнее число= " + rf.readLong());
            // Поиск заданного числа в файле и определение его номера (номеров)
            System.out.print("\nВведите число, которое нужно найти в файле => ");
            long x = sc.nextLong();
            long kol = 0; // количество искомых чисел в файле
            for (long i = 0; i < count; i++) {
                rf.seek(i * 8);
                long number = rf.readLong();
                if (number == x) {
                    kol++;
                    System.out.print("номер " + i + ", ");
                }
            }
            System.out.println(" количество искомых чисел = " + kol);
            rf.close();
            // СОРТИРОВКА ЧИСЕЛ В ФАЙЛЕ МЕТОДОМ «ПУЗЫРЬКА»
            rf = new RandomAccessFile(f1, "rw"); // открыт для чтения и записи
            for (long k = 0; k < count; k++) { // k – номер просмотра
                for (long i = 0; i < count - k -1; i++) { // i – номер числа
                    rf.seek(i * 8); // переход к i-тому числу
                    long number1 = rf.readLong();
                    long number2 = rf.readLong();
                    if (number1 > number2) { // условие для сортировки по возрастанию
                        rf.seek(i * 8);
                        rf.writeLong(number2);
                        rf.writeLong(number1);
                    }
                }
            }
            System.out.println("\n Числа, отсортированные в файле:");
            for (long i = 0; i < count; i++) {
                rf.seek(i * 8);
                System.out.print(" " + rf.readLong());
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}
