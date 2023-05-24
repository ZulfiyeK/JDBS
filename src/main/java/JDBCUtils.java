import java.sql.*;

public class JDBCUtils {
    // Bu class'da tekrarli kullanilacak methodlar olusturacagiz.
    //static blogun icine static olmayan dis veriabla,obje  alamayiz.
    // private yaparak encapsulation yapmis oluyoruz.
      private static Connection connection;
      private static Statement statement;
      private static ResultSet resultSet;
      private  static ResultSet executeUpdate;


    //database baglanma methodu==> connection return eder.
    public static Connection connectToDataBase() {  // bu sadece lokal'e baglaniyor.
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "151761ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public static Connection connectToMedunnaDataBase() {// Medunna database'ina baglandik
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    //statement olusturma methodu--exception gelmesin diye burada handl ediyoruz.
    public static Statement createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }


    // execute methodu ile query calistiran method
    public static boolean execute(String sql) {
        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //executeQuery() methodu ile Query çalıştıran method
    public static ResultSet executeQuery(String sql)  {

        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


    //baglantiyi kapatan method
    public static void closeConnection(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}



