package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcU {
    private static final String PROPERTIES_FILE = "src/main/java/config/db.properties";

    // 클래스 초기화 블록 (JDBC 드라이버 로드)
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(PROPERTIES_FILE));
            String driver = properties.getProperty("driver");
            Class.forName(driver);
        } catch (IOException e) {
            throw new RuntimeException("❌ 설정 파일 로드 실패!", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ JDBC 드라이버 로드 실패!", e);
        }
    }

    // 데이터베이스 연결 메서드
    public static Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(PROPERTIES_FILE));

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            return DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("❌ 데이터베이스 연결 실패!", e);
        }
    }
    public static void close (Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔌 데이터베이스 연결 닫힘");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
