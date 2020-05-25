package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import sample.Main;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseController implements Initializable {
    public TableColumn DateD;
    public TableColumn opisanieD;
    public TableColumn NameD;
    public TableColumn idD;
    public TableView tableD;
    public TextField name;
    public ImageView images;
    public Canvas canvas;
    public CheckBox rezumeChek;
    public ListView listview;
    public TableView tadWr;
   // public ArrayList<String> dosStr=new ArrayList<>();
    public Button Zapolnit;
    public Button namePoisk;
    public Button Delit;
    public Button ADD_dos;
    public Button EmailOtpravka;
    public Button SostavRezume;
    public Button Del_is_spisock;
    Stage primaryStage;
    public AnchorPane anchorPane;
    Main main;
    private ObservableList<Dostizhenie> dostizhenieObservableList;//основной список отображения

FileRezume fileRezume=new FileRezume();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.main = new Main();
        idD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("id"));
        NameD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("NameD"));
        opisanieD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("OpisanieD"));
        DateD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("DateD"));
        dostizhenieObservableList = FXCollections.observableArrayList();
        tableD.setFixedCellSize(40);
        name.setTooltip(new Tooltip("Поле ввода для поиска по названию,для ввода электронного адреса получателя резюме"));
        namePoisk.setTooltip(new Tooltip("Кнопка для поиска достижения по названия"));
        Zapolnit.setTooltip(new Tooltip("Вывод всех достижений из базы данных"));
        Delit.setTooltip(new Tooltip("Удаление выбранного достижения из таблицы"));
        ADD_dos.setTooltip(new Tooltip("Если необходимо добавить достижение, откроется форма для заполнения"));
        rezumeChek.setTooltip(new Tooltip("Если Вам необходимо составить резюме, поставьте отметку и выберите достижения для заполения"));
        SostavRezume.setTooltip(new Tooltip("Нажмите кнопку, когда Вы выбрали необходимы достижения, после этого откроется сам заполненый файла-резюме "));
        EmailOtpravka.setTooltip(new Tooltip("Перед отправкой файла введите адрес получателя в текстовое поле!Составленное Вами резюме отправится по этому адресу"));
        listview.setTooltip(new Tooltip("Отображение списка выбранных достижений для добавления"));
        Del_is_spisock.setTooltip(new Tooltip("Если Вы по ошибке добавили какое-либо достижение в список для резюме,то выберите его из списка и нажмите эту кнопку "));

    }
    public void openSql(ActionEvent actionEvent) {
        try {
            dostizhenieObservableList = ListDostizhenie.searchList();
            tableD.setItems(dostizhenieObservableList);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting questions information from DB.\n" + e + ". Method: fillTable()");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void poisk(ActionEvent actionEvent) {
        String findString;
        findString = name.getText();
        dostizhenieObservableList.removeAll();
        try {
            dostizhenieObservableList = ListDostizhenie.searchDostizhenieWithNameD(findString);
            tableD.setItems(dostizhenieObservableList);
        } catch (SQLException sqlEx) {
            System.out.println("ошибка поиска достижений.\n" + sqlEx + ". Method: findQuestion()");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void M_delete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Dostizhenie dostizhenie = (Dostizhenie) tableD.getSelectionModel().getSelectedItem();
        if (dostizhenie != null) {
            try {
                System.out.println(dostizhenie.getId());
                ListDostizhenie.deleteDostizhenie(dostizhenie.getNameD());
                ObservableList<Dostizhenie> dostizhenieObservableList = ListDostizhenie.searchList();
                tableD.setItems(dostizhenieObservableList);
            } catch (SQLException e) {
                System.out.println("Произошла следующая ошибка при удалении  достижения из БД: " + e.getMessage());
                throw e;
            }
        } else System.out.println("Не выбрана строка в таблице!");
    }

    public void add(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        Dostizhenie dostizhenie = new Dostizhenie(1, "1", "1", "1", "1");
        main.showCreateWindow(dostizhenie);
        try {
            ListDostizhenie.insertDostizhenie((dostizhenie));
            dostizhenieObservableList = ListDostizhenie.searchList();
            tableD.setItems(dostizhenieObservableList);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Произошла следующая ошибка, пока выполнялся поиск ползователя в БД: " + e.getMessage());
            throw e;
        }


    }

    public void email(ActionEvent actionEvent) {
        String findString;
        findString = name.getText();
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(findString);
        boolean matches = matcher.matches();
        if(matches==false)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Адрес получателя отсутствует или введен не верно!Проверьте корректность!");

            alert.showAndWait();
        }

        else {
            EmailAddress emailAddress = new EmailAddress();
            emailAddress.emailaddress(anchorPane, findString);
        }
    }

    public void code(ActionEvent actionEvent) {


    }

    public void imTabOpen(MouseEvent mouseEvent) throws FileNotFoundException, SQLException, ClassNotFoundException {


        Dostizhenie dostizhenie = (Dostizhenie) tableD.getSelectionModel().getSelectedItem();//получаем выбр элем
        if (dostizhenie != null) {//если не 0
            try {
                Image image = ListDostizhenie.getImagesDostizhenie(dostizhenie.getId());//передает ид в листдостижений
                images.setImage(image);//получаем путь и выводим картинку в имаджвью
                      } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                System.out.println("Произошла следующая ошибка при получение изображения из БД: " + e.getMessage());
                throw e;
            }
        } else System.out.println("Не выбрана строка в таблице!");

        if(rezumeChek.isSelected()==true)//ставим галочку ипосле этого выбираем достижения для резюме
        {
              //получ ид выбранного элемента
            if (dostizhenie != null) {//если не 0
                try {
                 dostizhenieObservableList= ListDostizhenie.searchDostizhenieWithId(dostizhenie.getId());//передает ид в листдостижений

                    fileRezume.addD(String.valueOf(dostizhenie.getOpisanieD()));
              //   dosStr.add(String.valueOf(dostizhenie.getOpisanieD()));

              listview.getItems().add(fileRezume.array());
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("Произошла следующая ошибка при получение изображения из БД: " + e.getMessage());
                    throw e;
                }
            } else System.out.println("Не выбрана строка в таблице!");
        }
    }

    public void rezume(ActionEvent actionEvent) throws IOException {//кнопка "составить резюме"
      /*  String fileReadName = "C:\\images\\test3.txt";
        String fileWriteName = "C:\\images\\news3.txt";
        try {
            FileReader fin = new FileReader(fileReadName);
            Scanner scanner = new Scanner(fin);
            FileWriter writer = new FileWriter(fileWriteName);
            System.out.println(dosStr.size()+"размер листа");
            String search = "кол";//что меняем
            String replace = String.valueOf(dosStr.size());//на что мняем
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.now();
            String text = date.format(formatter);
            LocalDate parsedDate = LocalDate.parse(text, formatter);
            String datesys=text;
            String sysDate="sysdate";
            String nazvanie="название";
            String nazvZamena=dosStr.toString();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                System.out.println(line);
                line = line.replaceAll(search, replace);
                line=line.replaceAll(sysDate,datesys);
                line=line.replaceAll(nazvanie,nazvZamena);
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
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

       */
      FileRezume fileRezume=new FileRezume();
      fileRezume.FilesRezume();


    }

    public void del_dost(ActionEvent actionEvent) {//"кнопка для удаления выбранных достижений

        int index=listview.getSelectionModel().getSelectedIndex();
        fileRezume.remuveD(index);
       // dosStr.remove(index);
        listview.getItems().remove(index);
        }

    public void imClick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==1) {
            images.setScaleX(1.3);
            images.setScaleY(1.3);
        }
        else
        {
            images.setScaleX(1);
            images.setScaleY(1);
        }
    }

    public void proverka(ActionEvent actionEvent) {
        // проверка почты
        String findString;
        findString = name.getText();
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(findString);
        boolean matches = matcher.matches();
        if(matches==false)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите адрес получателя в текстовое поле!");

            alert.showAndWait();
        }
        else {
            System.out.print("все оки!");
        }
    }
    }


