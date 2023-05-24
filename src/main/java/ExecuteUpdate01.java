import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","****");//Sifreyi duzeltmeyi unutma!!
        Statement statement = connection.createStatement(); // her class ta bu baglantiyi yapmak zorundayiz. o yuzden bunlar icin bir method olurturacagiz.

    //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql = " update companies set number_of_employees = 16000 where number_of_employees <(select avg(number_of_employees) as ortalama_calisan from companies);";// bu koda query deriz.
        int updateEdilenSatirSayisi = statement.executeUpdate(sql); // executeUpdate methodu update edilen satir sayisini int deger olarak verir.
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi); //2 tane update var.


        // update sonrasi datayi okumak icin DQL yapmaliyiz.
        String sql2 = "SELECT * FROM companies;"; // tablonun son halini bu kod ile goruyorum.
        ResultSet resultSet = statement.executeQuery(sql2);
        while (resultSet.next()){
        System.out.println(resultSet.getObject(1)+ "--"+ resultSet.getObject(2)+"--"+ resultSet.getObject(3));
        }


// executeUpdate methodu update edilen satir sayisini int deger olarak verir. digerleri String.

        connection.close();
        statement.close();
    }
}
