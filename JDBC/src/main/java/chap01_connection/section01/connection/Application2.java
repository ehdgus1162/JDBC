package chap01_connection.section01.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application2 {
    public static void main(String[] args) {

        /**
         * ✅ 1. Properties prop = new Properties();란?
         * Properties 클래스는 설정 파일(예: config.properties)에서 값을 읽어오는 데 사용됩니다.
         * 이를 통해 DB 접속 정보를 코드에서 직접 관리하는 대신 설정 파일로 분리할 수 있습니다.
         */
        Properties prop = new Properties(); // 설정 정보 저장 객체
        Connection connection = null; // DB 연결 객체

        try {
            // 1. 설정 파일 로드
            prop.load(new FileInputStream("src/main/java/config/db.properties"));

            // 2. 설정 정보 가져오기
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");


            // 3. JDBC 드라이버 로드
            Class.forName(driver);

            // 4. 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ 데이터베이스 연결 성공!");
            System.out.println("connection : " + connection);

        } catch (IOException e) {
            System.out.println("❌ 설정 파일 로드 실패!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC 드라이버 로드 실패!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ 데이터베이스 연결 실패!");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("🔌 데이터베이스 연결 닫힘");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
