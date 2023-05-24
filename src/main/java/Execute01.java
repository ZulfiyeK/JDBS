import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    //1. Adim: Driver'a kaydol.==> JDBS 4 sonrasi gerek yoktur.
         Class.forName("org.postgresql.Driver");


    //2. Adim:Database'e baglan
         Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","151761ok");

    //3. Adim : Statement olustur.
         Statement statement = connection.createStatement();

        //herzaman once 2 ve 3. adimlari yazmak zorundayiz.

    //4. Adim: Query calistir.
        /*
        1. Eger Excute() methodu DDL(create, drop, alter table) ile kullanilirsa her zaman false doner.Cunku data donmuyor.
        1. Eger Excute() methodu DQL(select) ile kullanilirsa data donerse "trueh", data donmezse " false"  doner.
         */



    //1. Ornek workers adinda bir table olusturunuz
         boolean sql=statement.execute("CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR (20), worker_salary INT);");
         // burda sql kodlarimizi olusturacagiz. boylece database'de bir table olusturmus olacagiz. burada olusturdugumuz table, SQL'de de olusmus oluyor.
         System.out.println("sql = " + sql);

    //2. Ornek workers table'ina adress sutunu ekleyiniz.
         String sglQuery1 = "ALTER TABLE workers add worker_address varchar(100);";
         boolean sql1 = statement.execute(sglQuery1);
         System.out.println("sql1 = " + sql1);

    //3. ornek: worker table 'i siliniz.
         String sqlQuery2 = "DROP TABLE workers";
         boolean sql2 = statement.execute(sqlQuery2);
         System.out.println("sql2 = " + sql2);

         // olusturup degistirip siliyor, bu yuzden her seferinde sildigi icin zaten olusturuldu diye hata vermiyor.
         //burada execute methodunu DDL(data defination langueges) ile kullanmayi ogrendik. return type olarak boolean kullandik..


    //4. adim : execute() methodu parametre olarak girilen String SQL komutunu bağlı olduğu database üzerinde uygular.


    //5. Adım: Bağlantıyı kapat
         connection.close();
         statement.close();
         //uzak server ile islerimiz oldugu zaman baglantiyi kapatmak gerekir.
         // bu sayfadakiler ddl ile ilgilidir.

    }
}
