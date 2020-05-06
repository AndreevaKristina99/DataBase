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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import model.Dostizhenie;
import model.EmailAddress;
import model.ListDostizhenie;
import org.krysalis.barcode4j.fop.BarcodeElement;
import org.krysalis.barcode4j.impl.code128.Code128;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.CanvasProvider;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.krysalis.barcode4j.xalan.BarcodeExt;

import java.awt.image.BufferedImage;
import java.io.File;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("id"));
        NameD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("NameD"));
        opisanieD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("OpisanieD"));
        DateD.setCellValueFactory(new PropertyValueFactory<Dostizhenie, String>("DateD"));

          File file=new File("C:\\images\\kursk.jpg");
         Image image = new Image(file.toURI().toString());
        images.setImage(image);
        //File file=new File("C:\\images\\kursk.jpg");
       // File file = new File("image");
       // Image image = new Image(file.toURI().toString());

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
            System.out.println("Error occurred while getting questions information from DB.\n" + sqlEx + ". Method: findQuestion()");
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

    public void add(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("C:/newForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void email(ActionEvent actionEvent) {
        EmailAddress emailAddress=new EmailAddress();
        emailAddress.emailaddress(anchorPane);

    }

    public void code(ActionEvent actionEvent) {

        Code128Bean bean = new Code128Bean();
        final int dpi = 50;
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
        bean.doQuietZone(false);
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider (160, BufferedImage.TYPE_BYTE_BINARY,
                        false, 0);
        bean.generateBarcode (canvas,"1234567889");
        BufferedImage bufferedImage = canvas.getBufferedImage ();
      images = new ImageView ();
        images.setImage (SwingFXUtils.toFXImage (bufferedImage, null));
        anchorPane.getChildren ().add (images);

    }
}
