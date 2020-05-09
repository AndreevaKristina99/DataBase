package controller;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import sample.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BaseController implements Initializable {
    public TableColumn DateD;
    public TableColumn opisanieD;
    public TableColumn NameD;
    public TableColumn idD;
    public TableView tableD;
    public TextField name;
    public ImageView images;
    public Canvas canvas;
    Stage primaryStage;
    public AnchorPane anchorPane;
    Main main;
    private ObservableList<Dostizhenie> dostizhenieObservableList;//основной список отображения
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.main = new Main();
        idD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("id"));
        NameD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("NameD"));
        opisanieD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("OpisanieD"));
        DateD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("DateD"));

        dostizhenieObservableList = FXCollections.observableArrayList();

        File file = new File("C:\\images\\ex.pdf");
        Image image = new Image(file.toURI().toString());
        images.setImage(image);
        System.out.println(file);
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
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.emailaddress(anchorPane);

    }

    public void code(ActionEvent actionEvent) {
        BarCode barCode = new BarCode();
        barCode.barCode(anchorPane, images);

    }

    public void imTabOpen(MouseEvent mouseEvent) throws FileNotFoundException, SQLException, ClassNotFoundException {

        Dostizhenie dostizhenie = (Dostizhenie) tableD.getSelectionModel().getSelectedItem();//получаем выбр элем
        if (dostizhenie != null) {//если не 0
            try {
                Image image = ListDostizhenie.getImagesDostizhenie(dostizhenie.getId());//передает ид в листдостижений
                images.setImage(image);//получаем путь и выводим картинку в имаджвью
               /* FadeTransition ft = new FadeTransition();
                ft.setNode(images);
                ft.setDuration(new Duration(2000));
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(6);
                ft.setAutoReverse(true);
                images.setOnMouseClicked(me -> ft.play());
              */
            } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                System.out.println("Произошла следующая ошибка при получение изображения из БД: " + e.getMessage());
                throw e;
            }
        } else System.out.println("Не выбрана строка в таблице!");
    }
}