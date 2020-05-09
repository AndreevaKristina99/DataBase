package sample;

import controller.CreateController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Dostizhenie;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    private Stage primaryStage;
    private Parent rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Курсовая работа");
        showBaseWindow();
    }

    public void showBaseWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            InputStream iconStream = getClass().getResourceAsStream("/resurse/1.jpg");
            Image image = new Image(iconStream);
            primaryStage.getIcons().add(image);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void showCreateWindow(Dostizhenie dostizhenie) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("new.fxml"));
            Parent page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Курсовая работа...");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(page));
            InputStream iconStream = getClass().getResourceAsStream("/resurse/2.jpg");
            Image image = new Image(iconStream);
            dialogStage.getIcons().add(image);
            CreateController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDostizhenie(dostizhenie);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /* public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Курсовая работа");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
    public void showCreateWindow(Dostizhenie dostizhenie) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("new.fxml"));
            Parent page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление...");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(page));
            // InputStream iconStream = getClass().getResourceAsStream("/recources/image/userIcon.png");
            //Image image = new Image(iconStream);
            //dialogStage.getIcons().add(image);
            CreateController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            /*controller.setStory(story);*/
    // dialogStage.showAndWait();
    // } catch (IOException e) {
    //   e.printStackTrace();
    // }
    //   }


}