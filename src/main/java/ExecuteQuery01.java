import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {
        // * satir okumak icin next() methodunu kullanacagiz, varsa true dondurecek, almak istedigim datayi ise stun ismi ile cagiracagim.

         Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","151761ok");
         Statement statement = connection.createStatement();

    //1. Ornek; region id'si 1 olan "country name" lerini cagiriniz.

         String sql= " select country_name from countries where region_id =1;";
         boolean r1 = statement.execute(sql);    //dataquery ile kullanmak cok saglikli degil.
                                                // data doner ama cok saglikli degildir cunku olmayan datayi da cagirsak megadatayi verecek,
         System.out.println("r1 = " + r1);       //r1 = true

         //satirlari gormek icin executeQuery methodunu kullanmaliyiz. execute() methodu sadece tru veya false doner.

         ResultSet resultset = statement.executeQuery(sql);
         while(resultset.next()){//bir sonraki satira gecip sout calisacak, bu dongu bitene kadar devam edecek.
                                // Bu kismin her zaman true yada false verecek, bu yuzden her zaman boolean olmak zorunda.

         System.out.println(resultset.getString("country_name"));// Belgium, Switzerlan, Germany, Denmark, France, Italy, Netherlands, Inited Kingdom.
        }
    /*
     bir stunda bir satir okumak icin her seferinde satir atlamak icin next() diyoruz,
     indeks her zaman 1 olmali, surekli next() yapmamak  icin while loop olustururuz.
     bu sayfadakiler dml ile ilgilidir.
    */



System.out.println("***************************** 2. gun, 16.05.2023 ***********************************************");

       //2. ornek : "region_id nin 2 den buyuk oldugu country_id ve country_id degerlerini cagiriniz.
         String sql2 = "select country_id, country_name from countries where region_id>2;";
         ResultSet resultSet2 = statement.executeQuery(sql2);
         while( resultSet2.next()){//en son satira geldigin de kendinden sonra artik satir olmadigi icin false veriri ve kapanir.
                                 // kapali resultSet2 uzerinde islem yapilirsa exception atar.

         System.out.println(resultSet2.getString(1)+"--"+resultSet2.getString(2));//1.satirdaki iki sutunu alacagiz.
       }


    /*
    pointer once tablonun basligina gider, stun isimlerinin oldugu yere, next ile sirada satir varsa calis demek,
    while ile pointer ilk satira gelir, pointer hangi satir uzerinde ise o satir uzerinde islem yapilir.
    bu islem bitince loop basa donecek ve ayni islemi tekrarlayacak ta ki satir kalamayana kadar.
    son satirdan sonra artik false verecegi icin resultSet kapanir.
    */


      //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "select * from companies where number_of_employees =(select min(number_of_employees)from companies);";
        ResultSet resultSet3 = statement.executeQuery(sql3);
        while (resultSet3.next()) {
            System.out.println(resultSet3.getString(1) + "--" + resultSet3.getString(2) + "--" + resultSet3.getString(3));//102--MICROSOFT--10000
        }
    // 1. 2. 3. yerine asagidaki gibi tirnak icinde sutun isimleride  yazilabilir. bu daha garanti bir yol olur.
    // System.out.println(resultSet3.getString("company_id")+"--"+ resultSet3.getString("company")+"--"+ resultSet3.getString("number_of_employees"));

        connection.close();
        statement.close();

}
}
