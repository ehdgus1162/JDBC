package chap02_statements.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static common.JdbcU.getConnection;
import static common.JdbcU.close;

public class Application2 {
    public static void main(String[] args) {

        /* 1. 데이터베이스 연결을 위한 Connection 객체 생성 */
        Connection connection = getConnection();

        /* 2. SQL 쿼리를 실행할 Statement 객체 선언 */
        Statement statement = null;

        /* 3. SELECT 결과를 저장할 ResultSet 객체 선언 */
        ResultSet rset = null;

        try {
            /* 4. Connection을 이용하여 Statement 객체 생성 (SQL 실행에 필요) */
            statement = connection.createStatement();

            /* 5. 사용자 입력을 받아 변수로 저장 */
            Scanner scanner = new Scanner(System.in);
            System.out.println("사번을 입력하세요"); // 사용자에게 사번 입력 요청
            String empId = scanner.nextLine(); // 입력된 사번 저장

            /* 6. 동적으로 SQL 쿼리 생성 (사용자 입력 포함) */
            String query = "SELECT emp_id, emp_name FROM employee WHERE emp_id = '" + empId + "'";

            /* 7. 생성된 쿼리문 출력 (디버깅용) */
            System.out.println(query);

            /* 8. SQL 실행 및 결과 조회 (ResultSet에 저장) */
            rset = statement.executeQuery(query);

            /* 9. 결과가 존재하는 경우, 사원 정보 출력 */
            if (rset.next()) {
                /*
                 * ResultSet의 커서를 한 줄 아래로 이동하며 데이터가 있는지 확인
                 * rset.getString("컬럼명") → 해당 컬럼의 값을 문자열(String)로 가져옴
                 */
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name"));
            } else {
                /* 조회 결과가 없는 경우 */
                System.out.println("해당 사원의 조회 결과가 없습니다");
            }
        } catch (SQLException e) {
            /* SQL 실행 중 오류 발생 시 예외 처리 */
            throw new RuntimeException(e);
        } finally {
            /* 10. 사용한 자원들(Connection, Statement, ResultSet) 닫기 */
            close(rset);       // ResultSet 닫기
            close(statement);  // Statement 닫기
            close(connection); // Connection 닫기
        }
    }
}
