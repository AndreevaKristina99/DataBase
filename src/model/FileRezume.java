package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRezume {
    ArrayList<String> Dost=new ArrayList<>();

    public  void  addD(String nameD)
    {
        Dost.add(nameD);
    }
    public void remuveD(int index)
    {
        Dost.remove(index);
    }
public ArrayList<String> array()
{
   return  this.Dost;
}
    public void FilesRezume() {

        String fileReadName = "C:\\images\\test3.txt";
        String fileWriteName = "C:\\images\\news3.txt";
        String nazvZamena="";
        try {
            FileReader fin = new FileReader(fileReadName);
            Scanner scanner = new Scanner(fin);
            FileWriter writer = new FileWriter(fileWriteName);
            System.out.println(Dost.size() + "размер листа");
            String search = "кол";//что меняем
            String replace = String.valueOf(Dost.size());//на что мняем
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.now();
            String text = date.format(formatter);
            LocalDate parsedDate = LocalDate.parse(text, formatter);
            String datesys = text;
            String sysDate = "sysdate";
            String nazvanie = "название";
            for(int i=0;i<Dost.size();i++) {
                /*
                проверить добавление в файл
                 */
                nazvanie = Dost.get(i);
            }
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

