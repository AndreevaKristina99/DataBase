package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TableColumn DateD;
    public TableColumn opisanieD;
    public TableColumn NameD;
    public TableColumn idD;
    public TableView tableD;
    public TextField name;
    public ImageView images;
    public Canvas canvas;
    public AnchorPane anchorPane;
Main main;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("id"));
        NameD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("NameD"));
        opisanieD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("OpisanieD"));
        DateD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("DateD"));

          File file=new File("C:\\images\\ex.pdf");
         Image image = new Image(file.toURI().toString());
        images.setImage(image);
        System.out.println(file);
    }

    private ObservableList<Dostizhenie> dostizhenieObservableList = FXCollections.observableArrayList();//основной список отображения

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
            dostizhenieObservableList = ListDostizhenie.searchDostizhenie(findString);
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

    public void add(ActionEvent actionEvent) throws IOException, SQLException {
     //   Dostizhenie dostizhenie = (Dostizhenie) tableD.getSelectionModel().getSelectedItem();
       // main.showCreateWindow(dostizhenie);
     //   try{
         //   DBConnection.;
      //  }
      //  catch (SQLException e){
        //   System.out.println("Произошла следующая ошибка, пока выполнялся поиск ползователя в БД: " + e.getMessage());
        //   throw e;
   //    }


    }

    public void email(ActionEvent actionEvent) {
        EmailAddress emailAddress=new EmailAddress();
        emailAddress.emailaddress(anchorPane);

    }

    public void code(ActionEvent actionEvent) {
        BarCode barCode=new BarCode();
    barCode.barCode(anchorPane,images);

    }

    public void img(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, FileNotFoundException {
    }

    public void imTabOpen(MouseEvent mouseEvent) throws FileNotFoundException, SQLException, ClassNotFoundException {

        Dostizhenie dostizhenie = (Dostizhenie) tableD.getSelectionModel().getSelectedItem();//получаем выбр элем
        if (dostizhenie != null) {//если не 0
            try {
                Image image= ListDostizhenie.imagesDostizhenie(dostizhenie.getId());//передает ид в листдостижений
                images.setImage(image);//получаем путь и выводим картинку в имаджвью
            } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                System.out.println("Произошла следующая ошибка при получение изображения из БД: " + e.getMessage());
                throw e;
            }
        } else System.out.println("Не выбрана строка в таблице!");
    }
}

