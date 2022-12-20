package Zad2.S;

import java.io.*;
import java.util.Scanner;

class Tovar implements Serializable {
    String name; // название
    String proiz; // Производитель
    double kolvo; // Кол-во
    double tsen; // цена
}

public class Var4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner sc=new Scanner(System.in,"cp1251");
        // создается файл на диске
        File f=new File("C:\\Games\\txt_files\\MyFileSer");
        f.createNewFile();
        // -------------ЗАПИСЬ ОБЪЕКТА В ФАЙЛ-------------
        // Создается поток для записи объекта
        RandomAccessFile rf = new RandomAccessFile(f,"rw"); // чтение и запись
        long fSize = rf.length(); // размер файла
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // Вводится информация об объекте (стране)

        System.out.print("Введите количество товаров для записи в файл\n"
                + "=> ");
        int kol = sc.nextInt();
        sc.nextLine();
        Tovar tovar = new Tovar();
        for (int i = 0; i < kol; i++) {
            System.out.print("Введите наименование " + (i + 1) + "-го товара => ");
            System.out.print("Название товара => ");
            tovar.name = sc.nextLine();
            System.out.print("Название производителя => ");
            tovar.proiz = sc.nextLine();
            System.out.print("Кол-во товара => ");
            tovar.kolvo = sc.nextDouble();
            System.out.print("Цена товара => ");
            tovar.tsen = sc.nextDouble();
            // Объект записывается в файл
            oos.writeObject(tovar);
            // Дописывается информация и закрывается файловый поток
        }
        oos.flush();
        oos.close();
        // -------------ЧТЕНИЕ ОБЪЕКТА ИЗ ФАЙЛА-------------
        // Создается поток для чтения объекта из файла
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);
        // Объект считывается, и на экран выводится требуемая информация
        tovar = (Tovar) oin.readObject();
        System.out.println("Информация о товарах:");
        for (int i = 0; i < kol; i++) {
            System.out.print((i + 1) + "-й товар => \n");
            System.out.println(" Название товара " + tovar.name);
            System.out.println(" Название производителя " + tovar.proiz);
            System.out.println(" Кол-во товара = " + tovar.kolvo);
            System.out.println(" Цена товара = " + tovar.tsen);
        }
        // Поток закрывается
        oos.close();
    }
}
