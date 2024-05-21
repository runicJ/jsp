package pds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guest.GuestVO;

public class PdsDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql = "";
	PdsVO vo = null;
	
	public PdsDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연동 실패~~" + e.getMessage());
		}
	}
	
	// conn객체 반납
	public void connClose() {
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {}  // 여기서 오류 잘 안남 나면 시스템 문제
		}
	}
	
	// pstmt 반납
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {}
		}
	}
	
	// rs 반납
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {}
		}
		pstmtClose();  // 정확히는 if 밖에 써야함
	}

	public int getTotRecCnt(String part) {
		int totRecCnt = 0;
		try {
			if(part.equals("전체")) {
				sql = "select count(*) as cnt from pds";
				pstmt = conn.prepareStatement(sql);
			}
			else {
				sql = "select count(*) as cnt from pds where part = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, part);
			}
			rs = pstmt.executeQuery();
			
			rs.next();
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}

	// 자료실의 모든(or part) 내용 가져오기
	public List<PdsVO> getPdsList(int startIndexNo, int pageSize, String part) {
		ArrayList<PdsVO> vos = new ArrayList<PdsVO>();
		try {
			if(part.equals("전체")) {
				sql = "select * from pds order by idx desc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
			}
			else {
				sql = "select * from pds where part = ? order by idx desc limit ?,?";  // limit 시작인덱스, 개수
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, part);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {  // 1개가 아닐 수 있으므로 여러개일 경우 while
				vo = new PdsVO();  // 위에 선언해 두었으므로 생성만 함
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setfName(rs.getString("fName"));
				vo.setFSName(rs.getString("fSName"));
				vo.setfSize(rs.getInt("fSize"));
				vo.setTitle(rs.getString("title"));
				vo.setPart(rs.getString("part"));
				vo.setfDate(rs.getString("fDate"));
				vo.setDownNum(rs.getInt("downNum"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setPwd(rs.getString("pwd"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setContent(rs.getString("content"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 자료실에 전송된 내용을 등록한다.
	public int setPdsInputOk(PdsVO vo) {
		int res = 0;
		try {
			sql = "insert into pds values(default,?,?,?,?,?,?,?,default,default,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getNickName());
			pstmt.setString(3, vo.getfName());
			pstmt.setString(4, vo.getFSName());
			pstmt.setInt(5, vo.getfSize());
			pstmt.setString(6, vo.getTitle());
			pstmt.setString(7, vo.getPart());
			pstmt.setString(8, vo.getOpenSw());
			pstmt.setString(9, vo.getPwd());
			pstmt.setString(10, vo.getHostIp());
			pstmt.setString(11, vo.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
