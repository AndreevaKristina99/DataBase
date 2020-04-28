package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;

import java.util.Date;

public class Dostizhenie {
    SimpleIntegerProperty id;
    SimpleStringProperty NameD;
    SimpleStringProperty OpisanieD;
    SimpleStringProperty DateD;

    public Dostizhenie(int id, String nameD,String opisanieD, String dateD) {
        this.id = new SimpleIntegerProperty(id);
       this.NameD =new SimpleStringProperty(nameD);
       this.OpisanieD =new SimpleStringProperty(opisanieD);
      this.DateD =new SimpleStringProperty(dateD);

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
        return NameD.get();
    }

    public SimpleStringProperty nameDProperty() {
        return NameD;
    }

    public void setNameD(String nameD) {
        this.NameD.set(nameD);
    }

    public String getOpisanieD() {
        return OpisanieD.get();
    }

    public SimpleStringProperty opisanieDProperty() {
        return OpisanieD;
    }

    public void setOpisanieD(String opisanieD) {
        this.OpisanieD.set(opisanieD);
    }

    public String getDateD() {
        return DateD.get();
    }

    public SimpleStringProperty dateDProperty() {
        return DateD;
    }

    public void setDateD(String dateD) {
        this.DateD.set(dateD);
    }
    public String toString()
    {

        return  id+""+NameD.get()+""+OpisanieD.get()+""+DateD.get() ;
    }

}
