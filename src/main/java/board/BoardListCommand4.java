package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Pagination;

public class BoardListCommand4 implements BoardInterface {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징 처리 시작
//		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
//		int pageSize = request.getParameter("pageSize")==null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		
		//Pagination.pageChange(request, pag, pageSize, "board", "");
	}
}