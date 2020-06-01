package model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Класс FileRezume</h1>
 *этот класс предназначен для формирования файла резюме, на основе шаблона
 * @author Kristina Andreeva
 * @version 1.2
 */
public class FileRezume {
    /**
     *
     */
    private   ArrayList<String> Dost=new ArrayList<>();
    public ArrayList<String> getDost() {
        return Dost;
    }
    public void setDost(ArrayList<String> dost) {
        Dost = dost;
    }
    public  void  addD(String nameD)
    {
        Dost.add(nameD);
    }
    public void remuveD(int index)
    {
        Dost.remove(index);
    }
    public int sizeD()
    {
        /**
         * возвращает размер arraylist
         */
        return Dost.size();
    }
public  String toString() {
    /**
     * этот метод возвращает элементы эррайлист
     * @return список достижений через разделитель ","
     */
    String str = "";
    for (int i=0;i<Dost.size();i++) {
       str +=  Dost.get(i) + ", ";
    }
    return str.substring(0, str.length() - 2);
}
    public void FilesRezume() {
        /**
         *
         * этот метод производит перезапись шаблона файла test3.txt в файл news3.txt
         * с заменой ключевых слов в шаблоне на выбранные параметры
         * передается системная дата,для указания,когда составлено резюме
         * далее,для корректирровки открывается программа "Блокнот" для просмотра сформированного содержимого
         *
         */
        String fileReadName = "C:\\images\\test3.txt";
        String fileWriteName = "C:\\images\\news3.txt";
        try {
            FileReader fin = new FileReader(fileReadName);
            Scanner scanner = new Scanner(fin);
            FileWriter writer = new FileWriter(fileWriteName);
            System.out.println(sizeD() + "размер листа");
            System.out.println(Dost.size() + "размер листа");
            String search = "kol";//что меняем
            String replace = String.valueOf(sizeD());//на что мняем
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.now();
            String text = date.format(formatter);
            LocalDate parsedDate = LocalDate.parse(text, formatter);
            String datesys = text;
            String sysDate = "sysdate";
            String nazvanie = "название";
            String nazvZamena=toString();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                System.out.println(line);
                line = line.replaceAll(search, replace);
                line = line.replaceAll(sysDate, datesys);
                line = line.replaceAll(nazvanie, nazvZamena);
                try {
                    writer.write(line + "\r\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            scanner.close();
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String testFilePath = "C:\\images\\news3.txt";
        try {
            Process process = Runtime.getRuntime().exec("cmd /c notepad.exe " + testFilePath);
            process.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

