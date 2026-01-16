package AplikasiParkirOtomatis;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                String url = "jdbc:mysql://localhost:3306/parkir_pintar";
                String user = "root";
                String pass = ""; // ganti jika ada password

                conn = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
