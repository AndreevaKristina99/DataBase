package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListDostizhenie {
    //формирует экземпляр списка достижений из вернувшегося ответа
    private static Dostizhenie getQuestionFromResultSet(ResultSet rs) throws SQLException {
        Dostizhenie dostizhenie = new Dostizhenie(1, "jj", "hhh", "2020", "kk.jpg");
        dostizhenie.setId(rs.getInt(DBConnection.nameCol.ID));
        dostizhenie.setNameD(rs.getString(DBConnection.nameCol.NAMEDOSTIZHENIE));
        dostizhenie.setOpisanieD(rs.getString(DBConnection.nameCol.OPISANIE));//получение по столбцу в БД
        dostizhenie.setDateD(rs.getString(DBConnection.nameCol.DATEPOL));
        dostizhenie.setImage(rs.getString(DBConnection.nameCol.IMAGES));

        return dostizhenie;
    }

    //получим весь список достижений
    public static ObservableList<Dostizhenie> searchList() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM " + DBConnection.DBName + ";";
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Dostizhenie> dostizhenie = FXCollections.observableArrayList();
            while (rs.next()) {
                Dostizhenie dost = getQuestionFromResultSet(rs);
                dostizhenie.add(dost);
            }
            return dostizhenie;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e + ". Method: searchList()");
            throw e;
        }
    }

    // yдаление  из бд||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    public static void deleteDostizhenie(String NameD) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM " + DBConnection.DBName + " WHERE " + DBConnection.nameCol.NAMEDOSTIZHENIE + " = '" + NameD + "';";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e + ". Method: deleteQuestWithId()");
            throw e;
        }
    }

    //поиск по NameD
    public static ObservableList<Dostizhenie> searchDostizhenieWithNameD(String questionSelect) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM " + DBConnection.DBName + " WHERE " + DBConnection.nameCol.NAMEDOSTIZHENIE + " LIKE '%" + questionSelect + "%';";
        try {

            ResultSet rsQue = DBConnection.dbExecuteQuery(selectStmt);

            ObservableList<Dostizhenie> questListFind = FXCollections.observableArrayList();
            while (rsQue.next()) {
                Dostizhenie que = getQuestionFromResultSet(rsQue);
                questListFind.add(que);
            }
            return questListFind;
        } catch (SQLException e) {
            System.out.println("While searching an question with " + questionSelect + " question, an error occurred: " + e
                    + ". Method: searchDostizhenie");
            throw e;
        }

    }

    //поиск по ID
    public static ObservableList<Dostizhenie> searchDostizhenieWithId(int id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT  * FROM " + DBConnection.DBName + " WHERE " + DBConnection.nameCol.ID + " = " + id + ";";//ищем путь по ид
        try {
            ResultSet rsQue = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Dostizhenie> questListFind = FXCollections.observableArrayList();
            while (rsQue.next()) {
                Dostizhenie que = getQuestionFromResultSet(rsQue);
                questListFind.add(que);
            }
            return questListFind;
        } catch (SQLException e) {
            System.out.println("While searching an question with " + id + " question, an error occurred: " + e
                    + ". Method: searchDostizhenie");
            throw e;
        }

    }

    //возвр одно поле имадже селектом ,где id =id в этом
    ///вернуть изображение достижения
    public static Image getImagesDostizhenie(int id) throws SQLException, ClassNotFoundException, FileNotFoundException {

        try {
            Dostizhenie dostizhenie = searchDostizhenieWithId(id).get(0);//
            String path = dostizhenie.getImage();//получаем строку с путем к файлу
            System.out.println("ПУТЬ "+path+"изображение ");
            Image image = new Image(new FileInputStream(path));
            return image;
        } catch (SQLException/* | FileNotFoundException*/ e) {
            System.out.print("Ошибка получения изображения " + e + ". Method: imagesDostizhenie()");
            throw e;
        }


    }

    //вставка данных в БД на основе экзмепляра question. ID - автоинкрементное поле|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    public static void insertDostizhenie(Dostizhenie dostizhenie) throws SQLException, ClassNotFoundException {
        //INSERT INTO dostixhenie VALUES ((NameDostizheniy, Opisanie, DatePol, image)'ggg', 'jjj', '2020-02-02', 'C:\images\kursk1.JPG');
       // INSERT INTO dostixhenie (NameDostizheniy, Opisanie, DatePol, image) VALUES ('Gram', 'ds', '2020-02-02', 'C:\images\resurse\2.jpg')

        String updateStmt = "INSERT INTO " + DBConnection.DBName + " (NameDostizheniy, Opisanie, DatePol, image) "+" VALUES (" + dostizhenie.record() + ");";
        System.out.println(""+updateStmt);
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Ошибка вставки: " + e + ". Method: insertQuest()");
            throw e;
        }
    }

}
