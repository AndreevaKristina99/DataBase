/*package model;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBConnection {
   // private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //    jdbc:mysql://localhost:3306/kursov1
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kursov1?serverTimezone=Europe/Moscow";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    public static final String DBName = "dostixhenie";

    public static final class nameCol {
        public static final String ID = "id";
        public static final String NAMEDOSTIZHENIE = "NameDostizheniy";
        public static final String OPISANIE = "Opisanie";
        public static final String DATEPOL = "DatePol";
        public  static  final  String IMAGES="image";
    }
    //соеденяемся
private  static  Connection getDBConnection()
{
    Connection dbConnection=null;
    try {
        dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Соединение установлено");
        return dbConnection;
    } catch (SQLException e) {
        System.out.println("Ошибка: " + e.getMessage() + ". Method: getDBConnection()");
    }
    return dbConnection;
}

//запрос бд
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnection = getDBConnection();
            System.out.println("Select statement: " + queryStmt + "\n");
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e + ". Method: dbExecuteQuery()");
            throw e;
        } finally { //закрыли соединения и данные
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return crs;
    }

  //обновление бд
  public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
      Connection dbConnection = null;
      Statement statement = null;
      try {
          dbConnection = getDBConnection();

          statement = dbConnection.createStatement();

          statement.executeUpdate(sqlStmt);
      } catch (SQLException e) {
          System.out.println("Problem occurred at executeUpdate operation : " + e + ". Method: dbExecuteUpdate()");
          throw e;
      } finally {
          if (statement != null) {
              statement.close();
          }
          if (dbConnection != null) {
              dbConnection.close();
          }
      }
  }

}
*/
package model;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBConnection {
    // private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //    jdbc:mysql://localhost:3306/kursov1
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kursov1?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=Europe/Moscow";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    public static final String DBName = "dostixhenie";
    public static final class nameCol {

        public static final String ID = "id";
        public static final String NAMEDOSTIZHENIE = "NameDostizheniy";
        public static final String OPISANIE = "Opisanie";
        public static final String DATEPOL = "DatePol";
        public static final String IMAGES = "image";
    }

    //соеденяемся
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Соединение установлено");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage() + ". Method: getDBConnection()");
        }
        return dbConnection;
    }

    //запрос бд
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnection = getDBConnection();
            System.out.println("Select statement: " + queryStmt + "\n");
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e + ". Method: dbExecuteQuery()");
            throw e;
        } finally { //закрыли соединения и данные
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return crs;
    }

    //обновление бд
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();

            statement = dbConnection.createStatement();

            statement.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e + ". Method: dbExecuteUpdate()");
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

}