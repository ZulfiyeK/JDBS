import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","*****");// sifreyi duzeltmeyi unutma!!
        Statement statement = connection.createStatement();// her class ta bu baglantiyi yapmak zorundayiz. o yuzden bunlar icin bir method olurturacagiz.

    //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        String sql = "select company,number_of_employees from companies order by number_of_employees desc offset 1 limit 1;";//en yok birinci deger, limit 2 dersem en yoksek iki degeri veririr.
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
        System.out.println(resultSet.getObject(1) + "--" + resultSet.getObject(2));
        }


    //2. subquery ile yapalim.
        System.out.println("2.yol");
        String sql1 = "select company,number_of_employees from companies " +
        "where number_of_employees=(select max(number_of_employees) from companies " +
        "where number_of_employees < (select max(number_of_employees) from companies));";

        ResultSet resultSet1 = statement.executeQuery(sql1);
        while(resultSet1.next()) {
        System.out.println(resultSet1.getObject(1) + "--" + resultSet1.getObject(2));
        }

        connection.close();
        statement.close();

    }
}
