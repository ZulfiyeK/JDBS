public class Runner {
    public static void main(String[] args) {

        // database baglan
        JDBCUtils.connectToDataBase();

        // Statement olustur.
        JDBCUtils.createStatement();

        //Query calistir.
        String sql = "CREATE TABLE workers (worker_id VARCHAR(20)," +
                     " worker_name VARCHAR(20), worker_salary INT)";

        JDBCUtils.execute(sql);// calistirmadan once pgAdminde tabloyu  sil.


    }
}
