package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListDostizhenie {
    //получим весь список достижений
public static  ObservableList<Dostizhenie> searchList() throws SQLException, ClassNotFoundException{

    String selectStmt = "SELECT * FROM " + DBConnection.DBName + ";";
    try {
        ResultSet rs = DBConnection.dbExecuteQuery(selectStmt);
        ObservableList<Dostizhenie> dostizhenie= FXCollections.observableArrayList();
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

//формирует экземпляр списка достижений из вернувшегося ответа
private static Dostizhenie getQuestionFromResultSet(ResultSet rs) throws SQLException {
   Dostizhenie dostizhenie = new Dostizhenie(1,"jj","hhh","2020","kk");
    dostizhenie.setId(rs.getInt(DBConnection.nameCol.ID));
    dostizhenie.setNameD(rs.getString(DBConnection.nameCol.NAMEDOSTIZHENIE));
    dostizhenie.setOpisanieD(rs.getString(DBConnection.nameCol.OPISANIE));//получение по столбцу в БД
    dostizhenie.setDateD(rs.getString(DBConnection.nameCol.DATEPOL));
    dostizhenie.setImage(rs.getString(DBConnection.nameCol.IMAGES));

    return dostizhenie;
}

    public static ObservableList<Dostizhenie> searchDostizhenie (String questionSelect) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM " + DBConnection.DBName + " WHERE " + DBConnection.nameCol.NAMEDOSTIZHENIE + " = '" + questionSelect + "';";
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
    // yдаление  из бд||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    public static void deleteDostizhenie (String NameD) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM " + DBConnection.DBName + " WHERE " + DBConnection.nameCol.NAMEDOSTIZHENIE + " = '" +NameD + "';";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e + ". Method: deleteQuestWithId()");
            throw e;
        }
    }

    //возвр одно поле имадже селектом ,где id =id в этом


    ///вернуть изображение достижения
    public static Image imagesDostizhenie (int id) throws SQLException, ClassNotFoundException {
//SELECT image FROM dostixhenie where id=2 ;
        String selectteStmt = "SELECT "+DBConnection.nameCol.IMAGES+"FROM" + DBConnection.DBName + " WHERE " + DBConnection.nameCol.ID + " = '" +id + "';";
        try {
            ResultSet resultSet=DBConnection.dbExecuteQuery(selectteStmt);
            Dostizhenie dostizhenie=getQuestionFromResultSet(resultSet);
           String path=dostizhenie.getImage();//получаем строку с путем к файлу
            Image image=new Image(String.valueOf(new File(path)));
            System.out.println(""+path);
            return  image;

        } catch (SQLException e) {
            System.out.print("Ошибка получения изображения " + e + ". Method: imagesDostizhenie()");
            throw e;
        }


    }




    //вставка данных в БД на основе экзмепляра question. ID - автоинкрементное поле|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    public static void insertDostizhenie(Dostizhenie dostizhenie) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO " + DBConnection.DBName + "(NameD,OpisanieD,DateD) VALUES (" + dostizhenie.record() + ");";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Ошибка вставки: " + e + ". Method: insertQuest()");
            throw e;
        }
    }

}
