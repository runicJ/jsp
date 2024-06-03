package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import guest.GuestVO;
import memeber.MemberVO;
import study.database.LoginVO;

public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql = "";
	BoardVO vo = null;
	
	public BoardDAO() {
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

	// 전체 게시글 보기  /* , date_format(wDate, '%Y-%m-%d %H:%i:%s') as wDate */
	public ArrayList<BoardVO> getBoardList(int startIndexNo, int pageSize, String contentsShow, String search, String searchString) {
		ArrayList<BoardVO> vos = new ArrayList<BoardVO>();
		try {
			if(search == null || search.equals("")) {  // 전체 검색(조건X)
			
				if(contentsShow.equals("adminOK")) {
					sql = "select *, datediff(wDate, now()) as date_diff, timestampdiff(hour, wDate, now()) as hour_diff, "
							+ "(select count(*) from boardReply where boardIdx = b.idx) as replyCnt "  // 끝에 무조건 하나 띄우기
							+ "from board b order by idx desc limit ?,?";  /* 새로운 변수가 만들어진 필드가 되었으므로 vo에 등록 */
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, startIndexNo);
					pstmt.setInt(2, pageSize);
				}
				else {
					sql = "select *, datediff(wDate, now()) as date_diff, timestampdiff(hour, wDate, now()) as hour_diff, "  // 줄 바꿀때 끝에  공간이 있어야 함
							+ "(select count(*) from boardReply where boardIdx = b.idx) as replyCnt "
							+ "from board b where openSW = 'OK' and complaint = 'NO' union select *, datediff(wDate, now()) as date_diff, "
							+ "timestampdiff(hour, wDate, now()) as hour_diff, "
							+ "(select count(*) from boardReply where boardIdx = b.idx) as replyCnt "
							+ "from board b where mid = ? order by idx desc limit ?,?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, contentsShow);
					pstmt.setInt(2, startIndexNo);
					pstmt.setInt(3, pageSize);
				}
			}
			else {
				if(contentsShow.equals("adminOK")) {
					sql = "select *, datediff(wDate, now()) as date_diff, timestampdiff(hour, wDate, now()) as hour_diff, "
							+ "(select count(*) from boardReply where boardIdx = b.idx) as replyCnt "
							+ "from board b where "+search+" like ? order by idx desc limit ?,?";  // search는 변수(따당), searchString은 값  // != <>
					pstmt = conn.prepareStatement(sql);						
					pstmt.setString(1, "%"+searchString+"%");
					pstmt.setInt(2, startIndexNo);
					pstmt.setInt(3, pageSize);
				}
				else {
					sql = "select *, datediff(wDate, now()) as date_diff, timestampdiff(hour, wDate, now()) as hour_diff, "
							+ "(select count(*) from boardReply where boardIdx = b.idx) as replyCnt "
							+ "from board b where openSW = 'OK' and complaint = 'NO' and "+search+" like ? union select *, datediff(wDate, now()) as date_diff, "
							+ "timestampdiff(hour, wDate, now()) as hour_diff, "
							+ "(select count(*) from boardReply where boardIdx = b.idx) as replyCnt "
							+ "from board b where mid = ? and "+search+" like ? order by idx desc limit ?,?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+searchString+"%");
					pstmt.setString(2, contentsShow);
					pstmt.setString(3, "%"+searchString+"%");
					pstmt.setInt(4, startIndexNo);
					pstmt.setInt(5, pageSize);
				}
			}
			rs = pstmt.executeQuery();

			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));
				vo.setComplaint(rs.getString("complaint"));
				
				vo.setHour_diff(rs.getInt("hour_diff"));
				vo.setDate_diff(rs.getInt("date_diff"));
				
				vo.setReplyCnt(rs.getInt("replyCnt"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	public int setBoardInput(BoardVO vo) {
		int res = 0;
		try {
			sql = "insert into board values (default,?,?,?,?,default,?,?,default,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getNickName());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getHostIp());
			pstmt.setString(6, vo.getOpenSw());
			res = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 게시글 내용보기
	public BoardVO getBoardContent(int idx) {
		//BoardVO vo = new BoardVO();
		try {
			sql = "select * from board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));		
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 조회수 증가처리
	public void setBoardReadNumPlus(int idx) {
		try {
			sql = "update board set readNum = readNum + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();  // 넘기는거 없음
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 게시글 삭제하기
	public int setBoardDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 게시물의 총 레코드 건수
	public int getTotRecCnt(String contentsShow, String search, String searchString) {
		int totRecCnt = 0;
		try {
			if(search == null || search.equals("")) {
				if(contentsShow.equals("adminOK")) {
				  sql = "select count(*) as cnt from board";
				  pstmt = conn.prepareStatement(sql);
				}
				else {
					//sql = "select sum(a.cnt) as cnt from (select count(*) as cnt from board where openSW = 'OK' and complaint = 'NO' union select count(*) as cnt from board where mid = ?) as a";
					sql = "select sum(a.cnt) as cnt from (select count(*) as cnt from board where openSW = 'OK' and complaint = 'NO' union select count(*) as cnt from board where mid = ? and (openSW = 'NO' or complaint = 'OK')) as a";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, contentsShow);
				}
			}
			else {
				if(contentsShow.equals("adminOK")) {
					sql = "select count(*) as cnt from board where "+search+" like ?";  // search는 변수명(따당), searchString은 값
				  pstmt = conn.prepareStatement(sql);
				  pstmt.setString(1, "%"+searchString+"%");
				}
				else {
					sql = "select sum(a.cnt) as cnt from (select count(*) as cnt from board where openSW = 'OK' and complaint = 'NO' and "+search+" like ? union select count(*) as cnt from board where mid = ? and (openSW = 'NO' or complaint = 'OK') and "+search+" like ?) as a";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+searchString+"%");
					pstmt.setString(2, contentsShow);
					pstmt.setString(3, "%"+searchString+"%");
				}
			}
			rs = pstmt.executeQuery();  // rs 나오면 무조건 읽어야 count면 while이나 if 쓸필요 없음 값이 무조건 0이라도 넘어옴
			rs.next();
			totRecCnt = rs.getInt("cnt");  // rs.getInt(0) 해도 되는데 나중에 유지보수 할 때 힘듦
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}

	// 이전글/다음글 idx,title 가져오기
	public BoardVO getPreNextSearch(int idx, String str) {
		BoardVO vo = new BoardVO();
		try {
			if(str.equals("preVo")) sql = "select idx, title from board where idx < ? order by idx desc limit 1";
			else sql = "select idx, title from board where idx > ? order by idx limit 1";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
	
			if(rs.next()) {  // 값이 없을 수도 있으니까 if 꼭 써야함
				vo.setIdx(rs.getInt("idx"));
				vo.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 좋아요 수 증가처리
	public int setBoardGoodCheck(int idx) {
		int res = 0;
		try {
			sql = "update board set good = good + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 좋아요 수 증가/감소 처리
	public void setBoardGoodCheckPlusMinus(int idx, int goodCnt) {
		try {
			sql = "update board set good = good + ? where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodCnt);  // 2개의 메소드를 사용할 필요가 없다.
			pstmt.setInt(2, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	public int setBoardUpdate(BoardVO vo) {
		int res = 0;
		try {
			sql = "update board set title=?,content=?,openSw=?,hostIp=?, wDate=now() where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getOpenSw());
			pstmt.setString(4, vo.getHostIp());
			pstmt.setInt(5, vo.getIdx());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public ArrayList<BoardReplyVO> getBoardReply(int idx) {
		ArrayList<BoardReplyVO> replyVos = new ArrayList<BoardReplyVO>();
		try {
			sql = "select * from boardReply where boardIdx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			BoardReplyVO vo = null;
			while(rs.next()) {  // 없을수도 있고 많을수도 있음
				vo = new BoardReplyVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setBoardIdx(rs.getInt("boardIdx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setwDate(rs.getString("wDate"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setContent(rs.getString("content"));
				
				replyVos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return replyVos;
	}

	// 댓글 저장하기
	public int setBoardReplyInput(BoardReplyVO vo) {
		int res = 0;
		try {
			sql = "insert into boardReply values (default,?,?,?,default,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoardIdx());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getNickName());
			pstmt.setString(4, vo.getHostIp());
			pstmt.setString(5, vo.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setBoardReplyDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from boardReply where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

  // 댓글 수정처리 
  public int setBoardReplyEdit(BoardReplyVO vo) {
      int res = 0;
      try {
          sql = "update boardreply set content=?, wDate=now() where idx = ?";
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, vo.getContent());
          pstmt.setInt(2, vo.getIdx());
          res = pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println("SQL 오류 : " + e.getMessage());
      } finally {
          pstmtClose();            
      }
      return res;
  }
}
