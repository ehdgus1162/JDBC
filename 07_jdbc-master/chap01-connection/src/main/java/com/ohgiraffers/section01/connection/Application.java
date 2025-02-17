package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {

        Connection con = null;

        try {
            /* 사용할 Driver 등록 */
            Class.forName("com.mysql.cj.jdbc.Driver");

            /* DriverManager의 getConnection 메소드를 이용해서 Connection(통로) 생성 */
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee","ohgiraffers", "ohgiraffers");

            System.out.println("con : " + con);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
