package chap02_statements.section01.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static common.JdbcU.close;
import static common.JdbcU.getConnection;

public class Application1 {
    public static void main(String[] args) {

        /* 1. 데이터베이스 연결을 위한 Connection 객체 생성 */
        Connection connection = getConnection();

        /* 2. SQL 실행을 위한 PreparedStatement 객체 선언 */
        PreparedStatement preparedStatement = null;

        /* 3. SELECT 결과를 저장할 ResultSet 객체 선언 */
        ResultSet rset = null;

        try {
            /* 4. PreparedStatement 객체 생성 (SQL 쿼리를 미리 준비) */
            preparedStatement = connection.prepareStatement("SELECT emp_id, emp_name FROM employee");

            /* 5. SQL 실행 및 결과 저장 */
            rset = preparedStatement.executeQuery();

            /* 6. 조회된 결과 출력 */
            while(rset.next()) {
                /*
                 * rset.next() : ResultSet의 커서를 한 줄 아래로 이동하며, 데이터가 있으면 true 반환
                 * rset.getString("컬럼명") : 해당 컬럼 값을 문자열(String)로 가져옴
                 */
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name"));
            }

        } catch (SQLException e) {
            /* 7. SQL 실행 중 오류 발생 시 예외 처리 */
            throw new RuntimeException(e);
        } finally {
            /* 8. 사용한 리소스 닫기 (ResultSet, PreparedStatement, Connection) */
            close(rset);  // ResultSet 닫기
            close(preparedStatement);  // PreparedStatement 닫기
            close(connection);  // Connection 닫기
        }
    }
}
