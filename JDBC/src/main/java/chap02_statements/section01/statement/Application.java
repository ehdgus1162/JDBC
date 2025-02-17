package chap02_statements.section01.statement;

import common.JdbcU;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
    public static void main(String[] args) {

        // 데이터베이스 연결을 위한 Connection 객체 생성
        Connection connection = JdbcU.getConnection();

        // SQL 쿼리를 실행하기 위한 Statement 객체 선언
        Statement statement = null;

        // SELECT 결과를 저장하는 ResultSet 객체 선언
        ResultSet rset = null;

        try {
            // Connection 객체를 이용하여 Statement 객체 생성 (쿼리를 실행하는 역할)
            statement = connection.createStatement();

            // SQL 실행: "employee" 테이블에서 "emp_id"와 "emp_name"을 조회
            rset = statement.executeQuery("SELECT emp_id, emp_name FROM employee");

            // 조회된 결과(ResultSet)를 반복문을 사용하여 출력
            while (rset.next()) {
                /*
                 * rset.next() : ResultSet의 커서를 한 줄 아래로 이동시키며,
                 *               데이터가 있으면 true, 없으면 false를 반환
                 * rset.getXXX(컬럼명) : 현재 커서가 가리키는 행(Row)의 특정 컬럼 값을 가져옴
                 */
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name"));
            }
        } catch (SQLException e) {
            // 데이터베이스 관련 예외 발생 시, RuntimeException으로 감싸서 던짐
            throw new RuntimeException(e);
        } finally {
            // 사용했던 리소스(Connection, Statement, ResultSet)를 닫아줌 (자원 해제)
            JdbcU.close(rset);       // ResultSet 닫기
            JdbcU.close(statement);  // Statement 닫기
            JdbcU.close(connection); // Connection 닫기 (DB 연결 종료)
        }
    }
}
