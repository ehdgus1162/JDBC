package chap01_connection.section02;
import common.JdbcU;

import java.sql.Connection;

public class Application {
    public static void main(String[] args) {

        Connection connection = JdbcU.getConnection();
        System.out.println("con : " + connection);
    }
}
