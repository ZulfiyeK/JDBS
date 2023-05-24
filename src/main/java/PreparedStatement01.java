import java.sql.*;// tum importlar yildizin altinda toplanir.tek tek tum importlari yazmaya gerek yoktur.

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","****");//sifreyi duzeltmeyi unutma!!
        Statement statement = connection.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //prepared statement olusturmak icin
        //1. adim prepared statement query'i olustur.
        String sql = "update companies set number_of_employees =? where company = ?;";// ? koyarsam parametrelendirmek demek, yani bunun yerine baska degerlerde alabilir.
        connection.prepareStatement(sql);


        //2. Adim PreraredStatement objesi olusturma
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        //3. Adim preparedStatement objesi ile setInt gibi methodlarla soru isareti yerine koymak istedigimiz degerleri yerlestiririz.
        preparedStatement.setInt(1,9999);
        preparedStatement.setString(2,"IBM");


        //4. Adim Query'i calistir.
        int guncellenenSatirSayisi = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);


        //Guncelleme sonrasi yeni tableyi okuyalim
        String sql1 = "SELECT * FROM companies;";
        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()){
        System.out.println(resultSet.getObject(1)+ "--"+ resultSet.getObject(2)+"--"+ resultSet.getObject(3));
        }//100--IBM--9999 olarak guncelledim.


        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5000 olarak güncelleyin.
        System.out.println("==============");
        preparedStatement.setInt(1, 5000);
        preparedStatement.setString(2, "GOOGLE");
        int updateSayisi = preparedStatement.executeUpdate();
        System.out.println("updateSayisi = " + updateSayisi);


        //Güncelleme sonrası yeni table'ı okuyalım:
        ResultSet resultSet2 = statement.executeQuery(sql1);
        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + "--" + resultSet2.getObject(2) + "--" + resultSet2.getObject(3));
        }


        connection.close();
        statement.close();
    }
}
