package chap01_connection.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {


        /**
         * 📌 1. Connection이란?
         * Connection 객체는 데이터베이스와의 연결을 관리하는 역할을 합니다.
         * DriverManager.getConnection(url, user, password)를 호출하면 데이터베이스와 연결됩니다.
         * 연결 후에는 SQL 실행하고 데이터를 조회할 수 있습니다.
         * 사용 후 반드시 닫아야 (close()) 합니다.
         */

        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://localhost/employee";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        // Collection 객체 선언
        Connection connection = null; // Collection 객체 선언

        try {
            // 1. JDBC 드라이버 로드 (MySQL 8 이상 생략 가능)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ 데이터베이스 연결 성공!");
            System.out.println("connection : " + connection);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
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
}
