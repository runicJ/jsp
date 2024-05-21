package common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.BoardDAO;
import board.BoardVO;
import guest.GuestDAO;
import guest.GuestVO;
import pds.PdsDAO;
import pds.PdsVO;

public class Pagination {

	public static void pageChange(HttpServletRequest request, int pag, int pageSize, String contentsShow, String section, String part) {  // 관리자는 adminOK 아니면 일반사용자(contentShow) // 항상 쓰는 것이니까 static 붙여줌  // part는 자료실에서 쓰일 것
		GuestDAO guestDao = new GuestDAO();
		BoardDAO boardDao = new BoardDAO();
		PdsDAO pdsDao = new PdsDAO();
		
		// part의 값이 넘어올 경우는 search/searchString 의 값이 넘어올 경우와, _ 가 있다.  // 자료실 배울때 _ 채움
		String search = "", searchString = "";  // 선언
		if(part != null && !part.equals("")) {
			if(section.equals("board")) {
				search = part.split("/")[0];
				searchString = part.split("/")[1];
			}
			else if(section.equals("guest")) {
				search = part.split("/")[0];
				searchString = part.split("/")[1];
			}
		}
		// pds는 part가 직접 넘어옴
		
		int totRecCnt = 0;
		
		if(section.equals("guest")) {
			if(part == null || part.equals("")) {
				totRecCnt = guestDao.getTotRecCnt(contentsShow, "", "");
			}
			else {
				totRecCnt = guestDao.getTotRecCnt(contentsShow, search, searchString);
			}
		}
		else if(section.equals("board")) {
			if(part == null || part.equals("")) {
				totRecCnt = boardDao.getTotRecCnt(contentsShow, "", "");	// 게시판의 전체 레코드수 구하기
			}
			else {
				totRecCnt = boardDao.getTotRecCnt(contentsShow, search, searchString);// part가 내용이 있다면(값이 왔다면) contentsShow만 넘기지 않고 search랑 searchString 같이 넘김
			}
		}
		else if(section.equals("pds")) {
			totRecCnt = pdsDao.getTotRecCnt(part);  // 자료실의 전체 레코드 수 구하기
		}
		
		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1;
		if(pag > totPage) pag = 1;
		int startIndexNo = (pag - 1) * pageSize;
		int curScrStartNo = totRecCnt - startIndexNo;
		
		int blockSize = 3;
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;

		List<GuestVO> gVos = null;
		List<BoardVO> bVos = null;
		List<PdsVO> pVos = null;
		
		if(section.equals("guest")) {
			if(part == null || part.equals("")) {
				gVos = guestDao.getGuestList(startIndexNo, pageSize, contentsShow, "", "");	 // null 보단 공백으로 // 게시판의 전체 자료 가져오기
			}
			else {
				gVos = guestDao.getGuestList(startIndexNo, pageSize, contentsShow, search, searchString);
			}
			request.setAttribute("vos", gVos);
		}
		if(section.equals("board")) {
			if(part == null || part.equals("")) {
				bVos = boardDao.getBoardList(startIndexNo, pageSize, contentsShow, "", "");	 // null 보단 공백으로 // 게시판의 전체 자료 가져오기
			}
			else {
//				search = part.split("/")[0];
//				searchString = part.split("/")[1];
				bVos = boardDao.getBoardList(startIndexNo, pageSize, contentsShow, search, searchString);
			}
			request.setAttribute("vos", bVos);
		}
		if(section.equals("pds")) {
			pVos = pdsDao.getPdsList(startIndexNo, pageSize, part);	// 게시판의 전체 자료 가져오기
			request.setAttribute("vos", pVos);
		}
		
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totRecCnt", totRecCnt);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		if(section.equals("board") && part != null && !part.equals("")) {
			String searchTitle = "";
			if(search.equals("title")) searchTitle = "글제목";
			else if(search.equals("nickName")) searchTitle = "작성자";
			else searchTitle = "글내용";
			
			request.setAttribute("search", search);
			request.setAttribute("searchTitle", searchTitle);
			request.setAttribute("searchString", searchString);
			request.setAttribute("searchCount", totRecCnt);
		}
		else if(section.equals("pds")) {
			request.setAttribute("part", part);
		}
	}

}
