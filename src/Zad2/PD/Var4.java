package Zad2.PD;

import java.io.*;
import java.util.Scanner;

public class Var4 {
    public static void main(String[] args) {
        try {
            File folder = new File("C:\\Games\\txt_files");
            if (!folder.exists())
                folder.mkdir();
            File f1 = new File("C:\\Games\\txt_files\\rec_RAF.txt");
            if (!f1.exists())
                f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1,"rw"); // чтение и запись
            long fSize = rf.length(); // размер файла
            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("Введите количество товаров для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine(); // очистка буфера после ввода числа

            String fam, doljnost;
            int oklad, kolv;
            //----ЗАПИСЬ ИНФОРМАЦИИ О СОТРУДНИКАХ В ФАЙЛ----
            for (int i = 0; i < kol; i++) {
                System.out.print("Введите наименование " + (i + 1) + "-го товара => ");
                fam = sc.next();
                rf.seek(rf.length()); // поиск конца файла
                rf.writeUTF(fam); // запись фамилии
                for (int j = 0; j < 20 - fam.length(); j++)
                    rf.writeByte(1); // добавление байтов до 20-ти любой цифрой (=1)
                System.out.print("Введите название производителя => ");
                doljnost = sc.next();
                rf.writeUTF(doljnost); // запись должности
                for (int j = 0; j < 20 - doljnost.length(); j++)
                    rf.writeByte(1); // добавление байтов до кол=20 любым числом
                System.out.print("Введите кол-во товара => ");
                kolv = sc.nextInt();
                sc.nextLine(); // очистка буфера
                rf.writeInt(kolv); // запись оклада
                for (int j = 0; j < 20 - doljnost.length(); j++)
                    rf.writeByte(1); // добавление байтов до кол=20 любым числом
                System.out.print("Введите стоимость товара => ");
                oklad = sc.nextInt();
                sc.nextLine(); // очистка буфера
                rf.writeInt(oklad); // запись оклада
            }
            rf.close();
            //----ЧТЕНИЕ ИНФОРМАЦИИ О СОТРУДНИКАХ ИЗ ФАЙЛА----
            rf = new RandomAccessFile(f1, "r");
            rf.seek(0); // перемещение в начало файла
            System.out.println("Информация о товарах");
            System.out.println("Наименование \t\t Производитель \t\t Кол-во \t\t Цена");
            for (int i = 0; i < kol; i++) {
                fam = rf.readUTF();
                for (int j = 0; j < 20 - fam.length(); j++)
                    rf.readByte();
                doljnost = rf.readUTF();
                for (int j = 0; j < 20 - doljnost.length(); j++)
                    rf.readByte();
                oklad = rf.readInt();
                kolv = rf.readInt();
                System.out.println(fam + "\t\t\t" + doljnost + "\t\t\t" + kolv + "\t\t\t" + oklad);
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}
