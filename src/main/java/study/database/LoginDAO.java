package study.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LoginDAO {

	private Connection conn = null;  // 선언만 해둠 => 필드 개념 // 생성할때(id, pw, url)
	private PreparedStatement pstmt = null;  // statement도 사용함
	private ResultSet rs = null;  // sql에 있는거 사용 3개 다 인터페이스
	//private Statement stmt = null;
	
	String sql = "";
	LoginVO vo = null;  // 생성x 사용할때 생성
	
	public LoginDAO() {  // 생성자
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  // Driver는 클래스 , 보통 jdbc.mysql.com 거꾸로 씀  // 회사에서 제공해줌
			conn = DriverManager.getConnection(url, user, password);
//			System.out.println("연결성공...");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 : " + e.getMessage());  // 주요 에러메시지 한줄
		} catch (SQLException e) {
			System.out.println("Database 연동 실패 : " + e.getMessage());
		}
	}
	
	// 사용한 객체의 반납(conn반납)
	public void connClose() {
		if(conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
	// pstmt 객체 반납
	public void pstmtClose() {
		if(pstmt != null ) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	
	// rs 객체 반납
	public void rsClose() {
		if(rs != null ) {
			try {
				rs.close();  // pstmt가 있어야 생성되니까 같이 닫음
				pstmtClose();
			} catch (SQLException e) {}
		}
	}

	// 아이디 체크
	public LoginVO getLoginIdCheck(String mid, String pwd) {
		LoginVO vo = new LoginVO();
		try {
			sql = "select * from hoewon where mid=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);  // ?에 값 넣기
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();  // 실행
			
			if(rs.next()) {  // 자료가 있으면 vo에 가득 채워서 넘기고 아니면 null값 넘김
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();  // select 사용시
		}
		return vo;
	}

	// 전체 회원 정보 검색
	public ArrayList<LoginVO> getLoginList(String sort, int startIndexNo, int pageSize) {
		ArrayList<LoginVO> vos = new ArrayList<LoginVO>();
		try {
			sql = "select * from hoewon order by " + sort + " limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();  // ?가 없으니 바로 실행
			
			while(rs.next()) {  // 여러 건이 나올 수 있으니 while로 비교
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vos.add(vo);  // vo에 넣을 것을 vos에 모두 담는 것
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 회원 가입처리
	public int setLoginInput(LoginVO vo) {
		int res = 0;
		try {
			sql = "insert into hoewon values (default, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getAddress());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 최근 가입한 5명의 회원 검색하기
	public ArrayList<LoginVO> getJoinList() {
		ArrayList<LoginVO> vos = new ArrayList<LoginVO>();
		try {
//			sql = "select * from hoewon order by idx desc limit 5";
			sql = "select idx,mid,name from hoewon order by idx desc limit 5";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
//				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
//				vo.setAge(rs.getInt("age"));
//				vo.setGender(rs.getString("gender"));
//				vo.setAddress(rs.getString("address"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	//최근 가입한 5명의 회원 검색하기
	public ArrayList<LoginVO> getRecentFiveMember() {
		ArrayList<LoginVO> vos = new ArrayList<LoginVO>();
		try {
			sql = "select * from hoewon order by idx desc limit 5";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));	
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 개별 조회
	public ArrayList<LoginVO> getLoginSearch(String name) {
		ArrayList<LoginVO> vos = new ArrayList<LoginVO>();
		try {
			sql = "select * from hoewon where name like ? order by name";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));	
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// idx로 검색처리
	public LoginVO getLoginIdxSearch(int idx) {
		LoginVO vo = new LoginVO();
		try {
			sql = "select * from hoewon where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 회원 정보 수정처리
	public int setLoginUpdate(LoginVO vo) {
		int res = 0;
		try {
			sql = "update hoewon set pwd=?,name=?,age=?,gender=?,address=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getAge());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getAddress());
			pstmt.setInt(6, vo.getIdx());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;  // 수정이 됐다는 메시지 때문에 int 씀
	}

	// 회원정보 삭제처리
	public void setLoginDelete(String mid) {
		try {
			sql = "delete from hoewon where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	public ArrayList<LoginVO> getSortList(String sort) {
		ArrayList<LoginVO> vos = new ArrayList<LoginVO>();
		try {
			sql = "select * from hoewon order by " + sort;  // O  // 넘어온 값은 ?로 쓸 수 있지만, 변수명(필드명)은 ?로 쓸 수 없음 '+sort'로 써야함
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
//			sql = "select * from hoewon order by ?";  // X
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, sort);
//			pstmt.setString(1, "sort");
//			pstmt.setString(1, ""+sort+"");
//			rs = pstmt.executeQuery();
			
//			pstmt = conn.prepareStatement(sql);  // O
//			pstmt = conn.prepareStatement(sort);
//			rs = pstmt.executeQuery("select * from hoewon order by "+sort+"");
			
//			stmt = conn.createStatement();  // O
//			rs = stmt.executeQuery("select * from hoewon order by "+sort+"");
			
			while(rs.next()) {
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));	
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 전체 회원 건수를 구한다.
	public int getTotRecCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as cnt from hoewon";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();  // 값이 무조건 오기 때문에 if 안해도 됨(null이어도 값이 0으로 옴)
			totRecCnt = rs.getInt("cnt");  // 숫자 0써도 되는데 변수명 쓰는 것이 좋음
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}
	
}
