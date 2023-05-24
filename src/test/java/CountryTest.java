import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountryTest {
       /*
        Given
          User connects to the database
          (Kullanıcı veritabanına bağlanır)

        When
          User sends the query to get the region ids from "countries" table
          (Kullanıcı, 'countries' table'dan 'region id' almak üzere query gönderir )

        Then
          Assert that the number of region ids greater than 1 is 17.
          (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

        And
          User closes the connection
       */


    @Test
    public void countryTest() throws SQLException {
        //User connects to the database
        JDBCUtils.connectToDataBase();
        JDBCUtils.createStatement();

        // User sends the query to get the region ids from "countries" table
        String sql = "SELECT COUNT(region_id) FROM countries WHERE region_id > 1";
        ResultSet resultSet = JDBCUtils.executeQuery(sql);

        //Assert that the number of region ids greater than 1 is 17.
        int number = 0;
        while (resultSet.next()){
            number = resultSet.getInt(1);
        }
        assertEquals(17,number);

    }


    public void countryTest2() throws SQLException {
        //User connects to the database
        JDBCUtils.connectToDataBase();
        JDBCUtils.createStatement();

        // User sends the query to get the region ids from "countries" table
        String sql = "SELECT COUNT(region_id) FROM countries WHERE region_id > 1";
        ResultSet resultSet = JDBCUtils.executeQuery(sql);
        List<Integer>region_idList = new ArrayList<>();//region_id yi list icine tasidik.

        while ((resultSet.next())){//her next dedigimiz de result set ile aldigimiz sayilari region_id ye yazdiriyoruz.
            region_idList.add(resultSet.getInt(1));
        }
        System.out.println("region_idList = " + region_idList);


         assertEquals(17,region_idList.size());// region_id 'nin eleman sayisinin(size) 17 olmasi gerekir.

        JDBCUtils.closeConnection();
    }



















}