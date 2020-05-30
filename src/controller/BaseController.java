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
    public TextField emails;
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
        name.setTooltip(new Tooltip("Поле ввода для поиска по названию"));
        namePoisk.setTooltip(new Tooltip("Кнопка для поиска достижения по названия"));
        Zapolnit.setTooltip(new Tooltip("Вывод всех достижений из базы данных"));
        Delit.setTooltip(new Tooltip("Удаление выбранного достижения из таблицы"));
        ADD_dos.setTooltip(new Tooltip("Если необходимо добавить достижение, откроется форма для заполнения"));
        rezumeChek.setTooltip(new Tooltip("Если Вам необходимо составить резюме, поставьте отметку и выберите достижения для заполения"));
        SostavRezume.setTooltip(new Tooltip("Нажмите кнопку, когда Вы выбрали необходимы достижения, после этого откроется сам заполненый файла-резюме "));
        EmailOtpravka.setTooltip(new Tooltip("Перед отправкой файла введите адрес получателя в текстовое поле!Составленное Вами резюме отправится по этому адресу"));
        listview.setTooltip(new Tooltip("Отображение списка выбранных достижений для добавления"));
        Del_is_spisock.setTooltip(new Tooltip("Если Вы по ошибке добавили какое-либо достижение в список для резюме,то выберите его из списка и нажмите эту кнопку "));
emails.setTooltip(new Tooltip("Поле для ввода электронного адреса адресата"));
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
        findString = emails.getText();
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(findString);
        boolean matches = matcher.matches();
        if(matches==false )
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Инфрмация об отправке");
            alert.setHeaderText(null);
            alert.setContentText("Письмо отправлено!");

            alert.showAndWait();
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
            if (dostizhenie != null) {//если не 0
                try {
                 dostizhenieObservableList= ListDostizhenie.searchDostizhenieWithId(dostizhenie.getId());//передает ид в листдостижений

                    fileRezume.addD(String.valueOf(dostizhenie.getOpisanieD()));
              //   dosStr.add(String.valueOf(dostizhenie.getOpisanieD()));
                    listview.getItems().add(String.valueOf(dostizhenie.getOpisanieD()));
                    System.out.println(fileRezume.toString());
                    //fileRezume.arrayToListView(listview);

                //

              //     fileRezume.array(listview);


                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("Произошла следующая ошибка при получение изображения из БД: " + e.getMessage());
                    throw e;
                }
            } else System.out.println("Не выбрана строка в таблице!");
        }
    }

    public void rezume(ActionEvent actionEvent) throws IOException {//кнопка "составить резюме"

      fileRezume.FilesRezume();


    }

    public void del_dost(ActionEvent actionEvent) {//"кнопка для удаления выбранных достижений

        int index=listview.getSelectionModel().getSelectedIndex();
       listview.getItems().remove(index);

         fileRezume.remuveD(index);

       //dosStr.remove(index);
      //  listview.getItems().remove(fileRezume.array());

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
System.out.println(fileRezume.sizeD());
}
    }


