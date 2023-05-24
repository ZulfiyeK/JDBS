import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","151761ok");
        Statement statement = connection.createStatement();

    // Selamlama yapan bir function olusturup calistiriniz ?

        //1. adim kodu yazalim.
         String sql = "create or replace function selamlama(x text) returns text as " +
                     "$$ begin return 'Merhaba ' || x || ', nasilsin ?'; end $$" +
                     " language plpgsql;";

             // create'den sonra "or replace" yazarsak "  String sql2 = "SELECT selamlama('Ali')"; "
             // methos icerisindeki ismi her seferinde degistirip tekrar tekrar calistirabiliriz.
             // her seferinde yeni isim ile tekrar sorunsuz calisacaktir.
             // " or replace " yazmazsak ilk yazdigimiz isim gecerli olacaktir, tekrar calistirmak istedigimizde
             // zaten var deyip hata verecek.


        //2. adim function'u cagiralim.
         statement.execute(sql); // Function olusturan query'i calistirdik. Bunu kapatsak da yine de calisir, onceden zaten olustu,
                                // alttakini calistirmak icin bunun bir kez calismasi yeterli, tekrar tekrar calismasina gerek yok.

         String sql2 = "SELECT selamlama('Ali')"; // Ali yerine baska isimlerde koysak olur.
         ResultSet resultSet = statement.executeQuery(sql2); // function'i parametresi ile cagirdik. Bu bize bir table dondurdu.
         resultSet.next(); // Bunu yazinca while loop'a gerek yok.
         System.out.println(resultSet.getObject(1)); // Merhaba Ali, nasilsin ?


        //1. Ornek selamlama yapan bir function'i callabel statement ile cagiriniz ?
        //3. adim function'u  cagiralim.
         CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");


        // 4. adim Return icin registerOutParameter methodunu paramaetreler icin setInt, setString,...methodlarini kullaniriz.
         callableStatement.registerOutParameter(1,Types.VARCHAR);
         callableStatement.setString(2,"Ali");


        //5. adim execute methodu ile callabael statement'i calistirmis oluruz. Veriler yerlerine yerlesmis olur.
         callableStatement.execute();


        //6. adim sonucu gormek icin callableStatement'dan data turunu cagiralim
        //callableStatement'ta data, resultSet isine alinmaz. Direkt callableStatement'dan alinir.,
         System.out.println(callableStatement.getString(1));//Merhaba Ali, nasilsin ?





    //2. Örnek: İki sayıyı toplayan bir function yazınız ve Callable Statement ile çağırınız.

        //1. adim.
         String sql3 = "create or replace function toplama(x  int, y int)\n" +
                "returns numeric  as $$  begin  return x+y;  end\n" +
                "$$  language plpgsql;";


        //2. adim function'u cagiralim
         statement.execute(sql3);


        //3. adim function'u  Callable Statement ile cagiralim.
         CallableStatement callableStatement1 = connection.prepareCall("{? = call toplama(?,?)}");


        // 4. adim Return icin registerOutParameter methodunu paramaetreler icin setInt, setString,...methodlarini kullaniriz.
         callableStatement1.registerOutParameter(1,Types.NUMERIC);
         callableStatement1.setInt(2,6);
         callableStatement1.setInt(3,4);


        //5. adim execute methodu ile callabael statement'i calistirmis oluruz. veriler yerlerine yerlesmis olur.
         callableStatement1.execute();


        //6. adim sonucu gormek icin callableStatement'dan data turunu cagiralim
         System.out.println(callableStatement1.getBigDecimal(1));//10


          connection.close();
          statement.close();

    }
}
