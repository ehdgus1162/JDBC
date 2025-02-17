package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴 이름 입력 : ");
        String menuName = sc.nextLine();
        System.out.println("메뉴 가격 입력 : ");
        int menuPrice = sc.nextInt();
        System.out.println("카테고리 코드 입력 : ");
        int categoryCode = sc.nextInt();
        System.out.println("판매 여부(Y/N) 입력 : ");
        sc.nextLine();
        String orderableStatus = sc.nextLine().toUpperCase();

        MenuDTO newMenu = new MenuDTO();
        newMenu.setName(menuName);
        newMenu.setPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(orderableStatus);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newMenu.getName());
            pstmt.setInt(2, newMenu.getPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatus());

            /* 조회 시에는 executeQuery() 메소드를 사용하여 ResultSet 객체를 반환 받지만,
             * 삽입, 수정, 삭제 시에는 executeUpdate() 메소드를 사용하여 수행 된 행의 갯수를 반환 받는다. */
            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("메뉴 등록 완료!");
        } else {
            System.out.println("메뉴 등록 실패 ㅠㅠ");
        }
    }
}
