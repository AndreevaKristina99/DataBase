package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
   Dostizhenie dostizhenie = new Dostizhenie(1,"jj","hhh","2020");
    dostizhenie.setId(rs.getInt(DBConnection.nameCol.ID));
    dostizhenie.setNameD(rs.getString(DBConnection.nameCol.NAMEDOSTIZHENIE));
    dostizhenie.setOpisanieD(rs.getString(DBConnection.nameCol.OPISANIE));//получение по столбцу в БД
    dostizhenie.setDateD(rs.getString(DBConnection.nameCol.DATEPOL));

    return dostizhenie;
}
}
