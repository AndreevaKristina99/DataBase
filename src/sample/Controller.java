package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Dostizhenie;
import model.ListDostizhenie;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TableColumn DateD;
    public TableColumn opisanieD;
    public TableColumn NameD;
    public TableColumn idD;
    public TableView tableD;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        idD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("id"));
        NameD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("NameD"));
        opisanieD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("OpisanieD"));

        DateD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("DateD"));

    }

    private ObservableList<Dostizhenie> dostizhenieObservableList= FXCollections.observableArrayList();//основной список отображения
    public void openSql(ActionEvent actionEvent) {
        try {
            dostizhenieObservableList = ListDostizhenie.searchList();
            tableD.setItems(dostizhenieObservableList);
        }
        catch (SQLException e)
        {
            System.out.println("Error occurred while getting questions information from DB.\n" + e + ". Method: fillTable()");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
