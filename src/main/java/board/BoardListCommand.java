package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Pagination;
import common.PaginationVO;

public class BoardListCommand implements BoardInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		// 페이징 처리 시작
		// 아래 값들을 나중엔 vo로 묶을 것
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		int totRecCnt = dao.getTotRecCnt();
		// 페이징 처리 끝
		
		PaginationVO pVo = Pagination.pageChange(request, pag, pageSize, totRecCnt);
		
		List<BoardVO> vos = dao.getBoardList(pVo.getStartIndexNo(), pageSize);  // 페이징 처리  // 넘겨야함
		
		request.setAttribute("pVo", pVo);
		request.setAttribute("vos", vos);
	}
}

//public class BoardListCommand2 implements BoardInterface {
//	@Override
//	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BoardDAO dao = new BoardDAO();
//		
//		// 페이징 처리 시작
//		// 아래 값들을 나중엔 vo로 묶을 것
//		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
//		int pageSize = request.getParameter("pageSize")==null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
//		int totRecCnt = dao.getTotRecCnt();
//		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1;
//		if(pag > totPage) pag = 1;
//		int startIndexNo = (pag - 1) * pageSize;
//		int curScrStartNo = totRecCnt - startIndexNo;
//		int blockSize = 3;
//		int curBlock = (pag - 1) / blockSize;
//		int lastBlock = (totPage - 1) / blockSize;
//		// 페이징 처리 끝
//		
//		List<BoardVO> vos = dao.getBoardList(startIndexNo, pageSize);  // 페이징 처리  // 넘겨야함
//		
//		request.setAttribute("vos", vos);
//		
//		request.setAttribute("pag", pag);
//		request.setAttribute("pageSize", pageSize);
//		request.setAttribute("totRecCnt", totRecCnt);
//		request.setAttribute("totPage", totPage);
//		request.setAttribute("curScrStartNo", curScrStartNo);
//		
//		request.setAttribute("blockSize", blockSize);
//		request.setAttribute("curBlock", curBlock);
//		request.setAttribute("lastBlock", lastBlock);
//	}
//}