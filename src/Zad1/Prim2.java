package Zad1;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Prim2 {
    public static void main(String[] args) {
        try{
            File folder=new File("C:\\Games\\txt_files");
            if (!folder.exists())
                folder.mkdir();
            File f1=new File("C:\\Games\\txt_files\\strokiRand.txt");
            f1.createNewFile();
            Scanner sc = new Scanner(System.in);
            System.out.print("Сколько строк надо записать в файл? \n =>");
            long count = sc.nextLong();
            sc.nextLine(); // очистка буфера после ввода числа

            RandomAccessFile rf = new RandomAccessFile(f1, "rw"); // чтение/запись
            rf.setLength(0);
            long len=rf.length();
            System.out.println("Открыт файл размером "+len+ " байт");
            System.out.println("Введите строки:");
            long kol=0; // счетчик букв

// Записать строки в файл
            for (long i = 0; i < count; i++) {
                String s=sc.nextLine();
                rf.writeUTF(s);
                kol+=s.length();
            }
            len=rf.length();
            System.out.println("Размер файла в байтах = "+len);
            rf.close();

            // Открыть файл для чтения "r"
            rf = new RandomAccessFile(f1, "r");
// Вывод строк из файла на экран
            System.out.println("Строки из файла:");
            rf.seek(0); // перевести указатель в начало файла (на первое слово)
            for (long i = 0; i < count; i++)
                System.out.println("Строка " + i + " начинается с байта " + rf.getFilePointer() + " - " + rf.readUTF() + " - заканчивается байтом "+ (rf.getFilePointer()-1));
                        rf.close();
        }catch(IOException e){
            System.out.println("End of file " +e);
        }
    }
}