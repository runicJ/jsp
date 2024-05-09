package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {  // Singleton(DB를 연결하는 용도로만 쓰는 것 Conn 쓰고 있음) => 아래 것만 사용함(싱글톤으로)
	private static Connection conn = null;  // java.sql  // 여기는 거의 static
	
	private String driver = "com.mysql.jdbc.Driver";  // Driver class명이니까 대문자
	private String url = "jdbc:mysql://localhost:3306/javaclass";  // 밑에서 사용할 것 따로 빼놓은 것
	private String user = "root";
	private String password = "1234";  // 밑에 넣어줌
	
	private static GetConn instance = new GetConn();  // 필드 선언 private static 타입 변수명 = 생성
	
	private GetConn() {  // 생성자, 외부에서 생성 못하게 private으로 설정
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패 : " + e.getMessage());
		}
	}
	
	public static GetConn getInstance() {  // 정적 메소드(얘를 어떻게 불러쓸건데) public static 타입 이름
		return instance;  // 사용은 DAO에서 하겠지
	}
	
	public static Connection getConn() {  // DAO에서 얘를 불러서 conn 사용
		return conn;  // 위에 conn 객체를 넘겨주세요
	}
}
