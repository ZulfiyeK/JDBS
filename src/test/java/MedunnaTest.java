import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MedunnaTest {
      /*
 Given
   User connects to the database
   (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

 When
   User sends the query to get the created room
   (Kullanıcı oluşturulan odayı getirmek için sorgu gönderir)

 Then
   Assert that room is created properly
   (Odanın düzgün kaydedildiğini doğrular)

 And
   User closes the connection

*/

    @Test
    public void medunnaTest() throws SQLException {
       // User connects to the database
      JDBCUtils.connectToMedunnaDataBase();
      JDBCUtils.createStatement();

      //  User sends the query to get the created room.
        String sql = "select * from room where room_number = 9995033";
        ResultSet resultSet =  JDBCUtils.executeQuery(sql);


       // Assert that room is created properly
        resultSet.next();//tek satir cagirdigimiz icin bir tane next methodu yeterlisir, coklu satirlarda loop kullanmak gerekir.
        assertEquals("123.00", resultSet.getString("price"));//esit mi degil mi diye kontrol eder assertEquals
        assertEquals("DataBase Test İçin Oluşturuldu", resultSet.getString("description"));
        assertEquals("mark_twain", resultSet.getString("created_by"));
        //benim olsuturdugum veriler data base' de benim olusturdugum sekilde mi diye test ediyoruz.




        //User closes the connection
        JDBCUtils.closeConnection();


    }



    @Test
    public void medunnaTest2() throws SQLException {
        JDBCUtils.connectToMedunnaDataBase();
        JDBCUtils.createStatement();

        String sql = "select * from staff where last_name = 'Koyuncu';";
        ResultSet resultSet = JDBCUtils.executeQuery(sql);

        resultSet.next();
        assertEquals("MALE", resultSet.getObject("gender"));

        JDBCUtils.closeConnection();
    }








}
