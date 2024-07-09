package photoGallery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.GetConn;

public class PhotoGalleryDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	private PhotoGalleryVO vo = null;
	
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {}
		}
	}
	
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {} 
			finally {
				pstmtClose();
			}
		}
	}

	// photoGallery전체 자료 가져오기
	public ArrayList<PhotoGalleryVO> getPhotoGalleryList(int startIndexNo, int pageSize, String part, String choice) {
		ArrayList<PhotoGalleryVO> vos = new ArrayList<PhotoGalleryVO>();
		try {
			//sql = "select pg.*,(select fSName from photoStorage where photoIdx=pg.idx limit 1) as fSName from photoGallery pg order by pg.idx desc";
			if(part.equals("전체")) {
				if(choice.equals("최신순")) {
					sql = "select pg.*,(select fSName from photoStorage where photoIdx=pg.idx limit 1) as fSName, (select count(*) from photoReply where photoIdx=pg.idx) as replyCnt from photoGallery pg order by pg.idx desc limit ?,?";
				}
				else {
					sql = "select pg.*,(select fSName from photoStorage where photoIdx=pg.idx limit 1) as fSName, (select count(*) from photoReply where photoIdx=pg.idx) as replyCnt from photoGallery pg order by "+choice+" desc, pg.idx desc limit ?,?";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
			}
			else {
				if(choice.equals("최신순")) {
					sql = "select pg.*,(select fSName from photoStorage where photoIdx=pg.idx limit 1) as fSName, (select count(*) from photoReply where photoIdx=pg.idx) as replyCnt from photoGallery pg where part=? order by pg.idx desc limit ?,?";
				}
				else {
					sql = "select pg.*,(select fSName from photoStorage where photoIdx=pg.idx limit 1) as fSName, (select count(*) from photoReply where photoIdx=pg.idx) as replyCnt from photoGallery pg where part=? order by "+choice+" desc, pg.idx desc limit ?,?";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, part);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new PhotoGalleryVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPart(rs.getString("part"));
				vo.setTitle(rs.getString("title"));
				vo.setPhotoCount(rs.getInt("photoCount"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setpDate(rs.getString("pDate"));
				vo.setGoodCount(rs.getInt("goodCount"));
				vo.setReadNum(rs.getInt("readNum"));
				
				vo.setfSName(rs.getString("fSName"));
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

	// 가장 큰 idx값 가져오기
	public int getPhotoGalleryMaxIdx() {
		int maxIdx = 1;
		try {
			sql = "select idx from photoGallery order by idx desc limit 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) maxIdx = rs.getInt("idx") + 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return maxIdx;
	}

	// photoGallery테이블에 레코드 삽입처리
	public int setPhotoGalleryInput(PhotoGalleryVO vo) {
		int res = 0;
		try {
			conn.setAutoCommit(false);
			
			sql = "insert into photoGallery values (?,?,?,?,?,?,default,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getPart());
			pstmt.setString(4, vo.getTitle());
			pstmt.setInt(5, vo.getPhotoCount());
			pstmt.setString(6, vo.getHostIp());
			pstmt.executeUpdate();
			pstmtClose();
			
			String[] fSNames = vo.getfSName().split("/");
			for(String fSName : fSNames) {
				sql = "insert into photoStorage values (default,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getIdx());
				pstmt.setString(2, fSName);
				pstmt.executeUpdate();
				pstmtClose();
			}
			
			conn.commit();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
			try {
				if(conn != null) conn.rollback();	// 예외오류 발생시는 기존에 작업된 sql문의 모두 rollback처리된다.
			} catch (Exception e2) {}
		}
		return res;
	}

	// 개별 상세내역 보기
	public PhotoGalleryVO getPhotoGalleryIdxSearch(int photoIdx) {
		PhotoGalleryVO vo = new PhotoGalleryVO();
		try {
			// photoStorage테이블의 정보를 먼저가져와서 변수에 담아두었다가, photoGallery테이블의 내용을 담을때 vo에 함께 담아준다.
			sql = "select fSName from photoStorage where photoIdx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photoIdx);
			rs = pstmt.executeQuery();
			
			String fSNames = "";
			while(rs.next()) {
				fSNames += rs.getString("fSName") + "/";
			}
			fSNames = fSNames.substring(0,fSNames.length()-1);
			pstmtClose();
			
			sql = "select pg.*,(select fSName from photoStorage where photoIdx=pg.idx limit 1) as fSName, "
					+ "(select count(*) from photoReply where photoIdx = ?) as replyCnt "
					+ "from photoGallery pg where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photoIdx);
			pstmt.setInt(2, photoIdx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new PhotoGalleryVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPart(rs.getString("part"));
				vo.setTitle(rs.getString("title"));
				vo.setPhotoCount(rs.getInt("photoCount"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setpDate(rs.getString("pDate"));
				vo.setGoodCount(rs.getInt("goodCount"));
				vo.setReadNum(rs.getInt("readNum"));
				
				// vo.setfSName(rs.getString("fSName"));
				vo.setfSName(fSNames);
				vo.setReplyCnt(rs.getInt("replyCnt"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 댓글 달기
	public int setReplyInput(PhotoGalleryVO vo) {
		int res = 0;
		try {
			sql = "insert into photoReply values (default,?,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setInt(2, vo.getIdx());
			pstmt.setString(3, vo.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 댓글 불러오기
	public ArrayList<PhotoGalleryVO> getPhotoGalleryReply(int photoIdx) {
		ArrayList<PhotoGalleryVO> vos = new ArrayList<PhotoGalleryVO>();
		try {
			sql = "select * from photoReply where photoIdx = ? order by idx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photoIdx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new PhotoGalleryVO();
				vo.setReplyIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setReplyPhotoIdx(rs.getInt("photoIdx"));
				vo.setContent(rs.getString("content"));
				vo.setPrDate(rs.getString("prDate"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 포토갤러리 조회수 증가처리
	public void setPhotoGalleryReadNumPlus(int idx) {
		try {
			sql = "update photoGallery set readNum = readNum + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
  }

	// 좋아요수 증가처리(중복방지)
	public void setPhotoGalleryGoodCheck(int idx) {
		try {
			sql = "update photoGallery set goodCount = goodCount + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();			
		}
	}

	// 댓글 삭제처리
	public int setPhotoGalleryReplyDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from photoReply where idx = ?";
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

	// 기존에 존재하는 사진정보 삭제하기
	public void setPhotoSingleDelete() {
		try {
			// sql = "delete from photoSingle";
			sql = "drop table photoSingle";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmtClose();
			
			sql = "create table photoSingle (idx int not null auto_increment primary key, photo varchar(50))";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();			
		}
	}

	// 사진 한장씩 등록시키기
	public void setPhotoSingleInput(String file) {
		try {
			sql = "insert into photoSingle values (default, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();			
		}
	}

	// 사진 20장씩 가져오기
	public ArrayList<String[]> getPhotoGallerySingleList(int startIndexNo, int pageSize) {
		ArrayList<String[]> vos = new ArrayList<String[]>();
		try {
			sql = "select * from photoSingle limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String[] photo = new String[2];
				photo[0] = rs.getInt("idx") + "";
				photo[1] = rs.getString("photo");
				vos.add(photo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
}
