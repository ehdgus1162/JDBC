package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력하세요 : ");
        String empId = sc.nextLine();
        /* ? : 위치홀더 */
        String query = "SELECT emp_id, emp_name FROM employee WHERE emp_id = ? /*AND dept_code = ?*/ ";

        try {
            pstmt = con.prepareStatement(query);
            /* sql문의 위치홀더를 설정 */
            pstmt.setString(1, empId);
//            pstmt.setString(2, deptCode);     // 위치홀더가 여러개일경우

            rset = pstmt.executeQuery();

            if(rset.next()) {
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name"));
            } else {
                System.out.println("조회하신 사번이 존재하지 않습니다. ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

    }
}
