package study2.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql = "";
	
	public TransactionDAO() {  // 기본생성자
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버가 없습니다." + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB연동 실패~" + e.getMessage());
		}
	}
	
	public void connClose() {
		try {
			if(conn != null) conn.close();
		} catch (Exception e) {}
	}
	
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();
		} catch (Exception e) {}
	}
	
	public void rsClose() {
		try {
			if(rs != null) rs.close();
			pstmtClose();
		} catch (Exception e) {}
	}

	// BankBook정보 가져오기
	public ArrayList<BankBookVO> getBankBookList(String mid) {
		ArrayList<BankBookVO> vos = new ArrayList<>();
		try {
			sql = "select * from bankBook where mid=? order by idx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BankBookVO vo = new BankBookVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setBalance(rs.getInt("balance"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// mid를 bankBook테이블에서 검색하여 해당 항목의 idx를 구해온다.
	public BankBookVO getBankBookMidSearch(String mid) {
		BankBookVO vo = new BankBookVO();
		try {
			sql = "select * from bankBook where mid = ? order by idx desc limit 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(mid);
				vo.setBalance(rs.getInt("balance"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

/* 트랜잭션 적용 시작 */
	// BankBookHistory에 사용내역에 저장하기
	public void setBankBookHistoryInput(BankBookVO vo) {
		try {
			// 트랜잭션 설정 : false를 인자값으로 설정하여 수동커밋으로 지정한다. => DB에 직접 반영되지 않고 트랜잭션에만 저장
			conn.setAutoCommit(false);  // true가 기본값
			
			sql = "insert into bankBookHistory values (default, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 실제 잔고에 적용하기
	public void setBankBookInput(BankBookVO vo, String err) {
		try {
			sql = "insert into bankBook values (default, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setInt(2, vo.getBalance());
			pstmt.executeUpdate();  // 정상처리
			
			if(err!=null && !err.equals("")){
				try {
					throw new Exception("진행 오류 발생 !!");
				} catch (Exception e) {
					if(conn != null) conn.rollback();
				}
			}
			// 정상적으로 트랜잭션 작업단위가 종료된 후에 트랜잭션을 커밋시킨다.
			conn.commit();  // 트랜잭션 컨테이너에 있던 얘들이 단계적으로 차곡차곡 실행시킴  // commit 되면 롤백이 안됨
		} catch (SQLException e) {  // 문제가 발생할 경우 예외 처리로 옴
			System.out.println("SQL 오류 : " + e.getMessage());
			try {
				if(conn != null) conn.rollback();  // 예외오류 발생 시는 기존에 작업된 sql문은 모두 rollback 처리된다. // 트랜잭션이 비어있지 않은 상태에서 에러발생하면 롤백시켜라
			} catch (Exception e2) {}
		} finally {
			pstmtClose();
		}
	}
/* 트랜잭션 적용 끝 */
}
