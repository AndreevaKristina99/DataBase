package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ListDostizhenie {
    /**
     * класс содержащий методы для работы с информацией из бд
     * @param rs
     * @return
     * @throws SQLException
     */

    private static Dostizhenie getQuestionFromResultSet(ResultSet rs) throws SQLException
    {
        /**
         * формирует экземпляр списка достижений из вернувшегося ответа
         */

        Dostizhenie dostizhenie = new Dostizhenie(1, "jj", "hhh", "2020", "kk.jpg");
        dostizhenie.setId(rs.getInt(DBConnection.nameCol.ID));
        dostizhenie.setNameD(rs.getString(DBConnection.nameCol.NAMEDOSTIZHENIE));
        dostizhenie.setOpisanieD(rs.getString(DBConnection.nameCol.OPISANIE));//получение по столбцу в БД
        dostizhenie.setDateD(rs.getString(DBConnection.nameCol.DATEPOL));
        dostizhenie.setImage(rs.getString(DBConnection.nameCol.IMAGES));

        return dostizhenie;
    }


    public static ObservableList<Dostizhenie> searchList() throws SQLException, ClassNotFoundException {
/**
 * метод для получения списка достижений
 */
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


    public static void deleteDostizhenie(String NameD) throws SQLException, ClassNotFoundException
    /**
     * метод для удаления записей из бд
     * в качетсве параметра прнимает id достижения
     */
    {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM " + DBConnection.DBName + " WHERE " + DBConnection.nameCol.NAMEDOSTIZHENIE + " = '" + NameD + "';";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e + ". Method: deleteQuestWithId()");
            throw e;
        }
    }


    public static ObservableList<Dostizhenie> searchDostizhenieWithNameD(String questionSelect) throws SQLException, ClassNotFoundException {
/**
 * метод для поиска достижений по названию
 */
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

    //поиск по id
    public static ObservableList<Dostizhenie> searchDostizhenieWithId(int id) throws SQLException, ClassNotFoundException {
        /**
         * метод для поиска по id достижения
         * принимает id
         * возвращет путь изображения
         */
        String selectStmt = "SELECT * FROM " + DBConnection.DBName + " WHERE " + DBConnection.nameCol.ID + " = " + id + ";";//ищем путь по ид
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


    public static void insertDostizhenie(Dostizhenie dostizhenie) throws SQLException, ClassNotFoundException
    { /**
     *
     * метод для вставки данных в БД на основе экзмепляра question.
     * ID - автоинкрементное поле
     */
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
