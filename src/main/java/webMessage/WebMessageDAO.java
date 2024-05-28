package webMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.complaint.ComplaintVO;
import admin.review.ReviewVO;
import guest.GuestVO;
import memeber.MemberVO;
import study.database.LoginVO;

public class WebMessageDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql = "";
	WebMessageVO vo = null;
	
	public WebMessageDAO() {
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
	
	// 메시지 전체 리스트보기
	public ArrayList<WebMessageVO> getMessageList(String mid, int mSw, int startIndexNo, int pageSize) {
		ArrayList<WebMessageVO> vos = new ArrayList<WebMessageVO>();
		try {
			if(mSw == 1 ) {	// 받은 메세지(처음에는 전체메세지(새메세지+읽은메세지)
				sql = "select *,timestampdiff(hour, sendDate, now()) as hour_diff from webMessage where receiveId=? and (receiveSw='n' or receiveSw='r') order by idx desc limit ?,?";
			}
			else if(mSw == 2 ) {	// 신규메세지
				sql = "select *,timestampdiff(hour, sendDate, now()) as hour_diff from webMessage where receiveId=? and receiveSw='n' order by idx desc limit ?,?";
			}
			else if(mSw == 3 ) {	// 보낸메세지
				sql = "select *,timestampdiff(hour, sendDate, now()) as hour_diff from webMessage where sendId=? and sendSw='s' order by idx desc limit ?,?";
			}
			else if(mSw == 4 ) {	// 보낸메세지
				sql = "select *,timestampdiff(hour, sendDate, now()) as hour_diff from webMessage where sendId=? and receiveSw='n' order by idx desc limit ?,?";
			}
			else if(mSw == 5 ) {	// 휴지통
				sql = "select *,timestampdiff(hour, sendDate, now()) as hour_diff from webMessage where (receiveId=? and receiveSw='g') or (sendId=? and sendSw='g') order by idx desc limit ?,?";
			}
			else {  // mSw가 0(메시지 작성), 6번(메시지 내용보기)인 경우 => 혹시나 넘어왔을때
				return vos;  // vos에 null이면 아무것도 안감
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			if(mSw == 5) {
				pstmt.setString(2, mid);
				pstmt.setInt(3, startIndexNo);
				pstmt.setInt(4, pageSize);
			}
			else {
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new WebMessageVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setSendId(rs.getString("sendId"));
				vo.setSendSw(rs.getString("sendSw"));
				vo.setSendDate(rs.getString("sendDate"));
				vo.setReceiveId(rs.getString("receiveId"));
				vo.setReceiveSw(rs.getString("receiveSw"));
				vo.setReceiveDate(rs.getString("receiveDate"));
				
				vo.setHour_diff(rs.getInt("hour_diff"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 메세지 작성처리
	public int setWmInputOk(WebMessageVO vo) {
		int res = 0;
		try {
			sql = "insert into webMessage values(default,?,?,?,default,default,?,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getSendId());
			pstmt.setString(4, vo.getReceiveId());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 1건의 메세지 내용보기
	public WebMessageVO getWebMessageContent(int idx, int mFlag) {
		WebMessageVO vo = new WebMessageVO();
		try {
			if(mFlag == 11 || mFlag == 12) {  // 받은메시지에서 신규메시지 클릭하면 n을 r로 변경하기 // 휴지통이 아닌(받은 메시지이거나 보낸 메시지에서의 처리)X
				sql = "update webMessage set receiveSw='r', receiveDate=now() where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.executeUpdate();
				pstmtClose();  // 사용했던건 닫아주거나 pstmt2로 만들어야 함
			}
			sql = "select * from webMessage where idx = ?";  // 하나의 메소드 안에서 쿼리 2개  // pstmt 닫고 또 사용 가능 다시 생성하면 됨
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			rs.next();  // 분명 자료가 넘어오니 if 붙일 필요없음
			
			vo.setIdx(rs.getInt("idx"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setSendId(rs.getString("sendId"));
			vo.setSendSw(rs.getString("sendSw"));
			vo.setSendDate(rs.getString("sendDate"));
			vo.setReceiveId(rs.getString("receiveId"));
			vo.setReceiveSw(rs.getString("receiveSw"));
			vo.setReceiveDate(rs.getString("receiveDate"));
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	public int setWmDeleteCheck(int idx, int mFlag) {
		int res = 0;
		try {
			if(mFlag == 11 || mFlag ==12) {	// mSw(=mFlag)값이 11은 받은편지함에서 휴지통으로 보낸경우, mSw는 새편지함에서 휴지통으로 넣은경우.
			  sql = "update webMessage set receiveSw='g' where idx = ?";
			}
			else if(mFlag == 13) {	// mSw(=mFlag)값이 13은 보낸편지함에서 휴지통으로 보낸경우
				sql = "update webMessage set sendSw='g' where idx = ?";
			}
			else {	// mSw(=mFlag)값이 133이 올때는 휴지통거치지않고 삭제처리(x)
				sql = "update webMessage set sendSw='x' where idx = ?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 휴지통에 들어있는 모든 자료들을 삭제(update) 처리한다. 이때 receiveSw와 sendSw가 모두 'x'이면 실제 자료를 삭제(delete)처리한다.
	public int setWmDeleteAll(String mid) {
		int res = 0;
		try {
			// 받은 메시지에서의 휴지통 처리 시에 수행할 sql문
			sql = "update webMessage set receiveSw = 'x' where receiveId = ? and receiveSw = 'g'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			pstmtClose();

			// 보낸 메시지에서의 휴지통 처리 시에 수행할 sql문
			sql = "update webMessage set sendSw = 'x' where sendId = ? and sendSw = 'g'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			pstmtClose();
			
			sql = "delete from webMessage where sendSw='x' and receiveSw='x'";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	// 조건에 맞는 메세지 건수 구하기
	public int getWmTotRecCnt(String mid, int mSw) {
		int totRecCnt = 0;
		try {
			sql = "";
			if(mSw == 1) 	// 받은메세지(새메세지 + 읽은메세지)
				sql = "select count(*) from webMessage where receiveId=? and (receiveSw='n' or receiveSw='r')";
			else if(mSw == 2) 	// 새메세지(새메세지)
				sql = "select count(*) from webMessage where receiveId=? and receiveSw='n'";
			else if(mSw == 3) 	// 보낸메세지
				sql = "select count(*) from webMessage where sendId=? and sendSw='s'";
			else if(mSw == 4) 	// 수신확인
				sql = "select count(*) from webMessage where sendId=? and receiveSw='n'";
			else if(mSw == 5) 	// 휴지통
				sql = "select count(*) from webMessage where (receiveId=? and receiveSw='g') or (sendId=? and sendSw='g')";
			else return totRecCnt;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			if(mSw == 5) pstmt.setString(2, mid);
			
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}
	
//	// 조건에 맞는 메세지 건수 구하기
//	public int getWmTotRecCnt(String mid, String mSw) {
//		int totRecCnt = 0;
//		try {
//			sql = "";
//			if(mSw.equals("1")) 	// 받은메세지(새메세지 + 읽은메세지)
//				sql = "select count(*) from webMessage where receiveId=? and (receiveSw='n' or receiveSw='r')";
//			else if(mSw.equals("2")) 	// 새메세지(새메세지)
//				sql = "select count(*) from webMessage where receiveId=? and receiveSw='n'";
//			else if(mSw.equals("3")) 	// 보낸메세지
//				sql = "select count(*) from webMessage where sendId=? and sendSw='s'";
//			else if(mSw.equals("4")) 	// 수신확인
//				sql = "select count(*) from webMessage where sendId=? and receiveSw='n'";
//			else if(mSw.equals("5")) 	// 휴지통
//				sql = "select count(*) from webMessage where (receiveId=? and receiveSw='g') or (sendId=? and sendSw='g')";
//			else return totRecCnt;
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, mid);
//			if(mSw.equals("5")) pstmt.setString(2, mid);
//			
//			rs = pstmt.executeQuery();
//			rs.next();
//			totRecCnt = rs.getInt(1);
//		} catch (SQLException e) {
//			System.out.println("SQL오류 : " + e.getMessage());
//		} finally {
//			rsClose();
//		}
//		return totRecCnt;
//	}

	//회원 부분 검색하기
	public ArrayList<MemberVO> getIdSearchCheck(String mid) {
		ArrayList<MemberVO> vos = new ArrayList<MemberVO>();
		try {
			sql = "select idx,mid,nickName from member2 where mid like ? order by mid";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+mid+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 고유번호(idx)로 메세지 내역 가져오기
	public WebMessageVO getWebMessageIxdSearch(int idx) {
		WebMessageVO vo = new WebMessageVO();
		try {
			sql = "select * from webMessage where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			rs.next();
			
			vo.setIdx(rs.getInt("idx"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setSendId(rs.getString("sendId"));
			vo.setSendSw(rs.getString("sendSw"));
			vo.setSendDate(rs.getString("sendDate"));
			vo.setReceiveId(rs.getString("receiveId"));
			vo.setReceiveSw(rs.getString("receiveSw"));
			vo.setReceiveDate(rs.getString("receiveDate"));
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 메세지 복원시키기
	public void setWmRestore(int idx, String sw) {
		try {
			if(sw.equals("s")) sql = "update webMessage set sendSw = 's' where idx = ?";
			else sql = "update webMessage set receiveSw = 'r' where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

}
