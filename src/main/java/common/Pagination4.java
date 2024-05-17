package common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.BoardDAO;
import board.BoardVO;
import guest.GuestDAO;
import guest.GuestVO;
import pds.PdsDAO;

public class Pagination4 {

	public static void pageChange(HttpServletRequest request, int pag, int pageSize, String section, String part, String string) {  // 항상 쓰는 것이니까 static 붙여줌  // part는 자료실에서 쓰일 것
//		GuestDAO guestDao = new GuestDAO();
//		BoardDAO boardDao = new BoardDAO();
//		//PdsDAO pdsDao = new PdsDAO();
//		
//		int totRecCnt = 0;
//		
//		if(section.equals("guest")) {
//			totRecCnt = guestDao.getTotRecCnt();
//		}
//		else if(section.equals("board")) {
//			totRecCnt = boardDao.getTotRecCnt();	// 게시판의 전체 레코드수 구하기
//		}
//		else if(section.equals("pds")) {
//			//totRecCnt = pdsDao.getTotRecCnt();  // 자료실의 전체 레코드 수 구하기
//		}
//		
//		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1;
//		if(pag > totPage) pag = 1;
//		int startIndexNo = (pag - 1) * pageSize;
//		int curScrStartNo = totRecCnt - startIndexNo;
//		
//		int blockSize = 3;
//		int curBlock = (pag - 1) / blockSize;
//		int lastBlock = (totPage - 1) / blockSize;
//
//		List<GuestVO> gVos = null;
//		List<BoardVO> bVos = null;
//		//List<PdsVO> pVos = null;
//		if(section.equals("guest")) {
//			gVos = guestDao.getGuestList(startIndexNo, pageSize);
//			request.setAttribute("vos", gVos);
//		}
//		else if(section.equals("board")) {
//			bVos = boardDao.getBoardList(startIndexNo, pageSize);	// 게시판의 전체 자료 가져오기
//			request.setAttribute("vos", bVos);
//		}
//		else if(section.equals("pds")) {
//			//pVos = pdsDao.getPdsList(startIndexNo, pageSize);	// 게시판의 전체 자료 가져오기
//		}
//		
//		request.setAttribute("pag", pag);
//		request.setAttribute("pageSize", pageSize);
//		request.setAttribute("totRecCnt", totRecCnt);
//		request.setAttribute("totPage", totPage);
//		request.setAttribute("curScrStartNo", curScrStartNo);
//		request.setAttribute("blockSize", blockSize);
//		request.setAttribute("curBlock", curBlock);
//		request.setAttribute("lastBlock", lastBlock);
//		
//		request.setAttribute("part", part);
	}

}
