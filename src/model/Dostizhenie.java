package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;

import java.util.Date;

public class Dostizhenie {
    SimpleIntegerProperty id;
    SimpleStringProperty NameDostizheniy;
    SimpleStringProperty Opisanie;
    SimpleStringProperty DatePol;
    SimpleStringProperty image;

    public Dostizhenie(int id, String nameD,String opisanieD, String dateD, String image) {
        this.id = new SimpleIntegerProperty(id);
       this.NameDostizheniy =new SimpleStringProperty(nameD);
       this.Opisanie =new SimpleStringProperty(opisanieD);
      this.DatePol =new SimpleStringProperty(dateD);
      this.image=new SimpleStringProperty(image);

    }

    public int getId() {

        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNameD() {
        return NameDostizheniy.get();
    }

    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public SimpleStringProperty nameDProperty() {
        return NameDostizheniy;
    }

    public void setNameD(String nameD) {
        this.NameDostizheniy.set(nameD);
    }

    public String getOpisanieD() {
        return Opisanie.get();
    }

    public SimpleStringProperty opisanieDProperty() {
        return Opisanie;
    }

    public void setOpisanieD(String opisanieD) {
        this.Opisanie.set(opisanieD);
    }

    public String getDateD() {
        return DatePol.get();
    }

    public SimpleStringProperty dateDProperty() {
        return DatePol;
    }

    public void setDateD(String dateD) {
        this.DatePol.set(dateD);
    }
    public String record()
    {

        return  id+""+NameDostizheniy+""+Opisanie+""+DatePol+""+image;
    }

}
