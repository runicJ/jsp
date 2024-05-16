package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Pagination;
import common.Pagination2;
import common.PaginationVO;

public class BoardListCommand3 implements BoardInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		// 페이징 처리 시작
		// 아래 값들을 나중엔 vo로 묶을 것
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		String part = request.getParameter("part")==null ? "전체" : request.getParameter("part");  // 자료실부터 사용(게시판x)
		int totRecCnt = dao.getTotRecCnt();  // 게시판의 전체 레코드 수 구하기
		// 페이징 처리 끝
				
		int startIndexNo = (pag - 1) * pageSize;
		List<BoardVO> vos = dao.getBoardList(startIndexNo, pageSize);  // 게시판의 전체 자료 가져오기
		request.setAttribute("vos", vos);
		// Pagination pagination = new Pagination();  // static으로 처리했으니까 밑에서 그냥 클래스명으로 쓰면됨
		
		// Pagination.pageChange(request, pag, pageSize, totRecCnt, startIndexNo, part);  // 그냥 여기서 쓰고 생성하면 됨(연습한거)
		
	}
}