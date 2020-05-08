package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Dostizhenie;

import java.awt.*;

public class CreateController {


    public TextField name;
    public TextField opisanie;
    public DatePicker dated;
    private Stage dialogStage;
    Main main;
    private Dostizhenie dostizhenie;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void files(ActionEvent actionEvent) {
    }

    public void adud(ActionEvent actionEvent) {
        if (name!= null && !name.getText().isEmpty()) {
            dostizhenie.setNameD(name.getText());
        }
        if (opisanie != null && !opisanie.getText().isEmpty()) {
            dostizhenie.setOpisanieD(opisanie.getText());
        }

        dialogStage.close();
    }
    }

