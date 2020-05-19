
package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Dostizhenie;
import sample.Main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateController {


    public TextField name;
    public TextField opisanie;
    public DatePicker dated;
    public AnchorPane ap;
    public TextField dateADD;
    private Stage dialogStage;
    protected String str;
    Main main;
    private Dostizhenie dostizhenie;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDostizhenie(Dostizhenie dostizhenie) {
        this.dostizhenie = dostizhenie;
    }

    public void files(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();//класс работы с диалоговым окном
        fileChooser.setTitle("Выберите файл...");//заголовок диалога
        //задает фильтр для указанного расшиерения
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Файл", "*.jpg"),
                new FileChooser.ExtensionFilter("Файл", "*.png"));
        //url

        File file = fileChooser.showOpenDialog(ap.getScene().getWindow());
         str =file.toURI().getPath();//получаем строку с путем к файлу
        System.out.println("" + str);
        //String str1=;
    }

    public void adud(ActionEvent actionEvent) {
        System.out.println("" + str);
       if (name != null && !name.getText().isEmpty()) {
            dostizhenie.setNameD(name.getText());
        }
        if (opisanie != null && !opisanie.getText().isEmpty()) {
            dostizhenie.setOpisanieD(opisanie.getText());
        }
        if (dateADD != null && !dateADD.getText().isEmpty()) {
           // Date date=new SimpleDateFormat("dd/MM/yyyy").parse(dateADD);
            dostizhenie.setDateD(dateADD.getText());
        }
            dostizhenie.setImage(str);
        dialogStage.close();
    }
}
