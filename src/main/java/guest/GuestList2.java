package guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Pagination;

@SuppressWarnings("serial")
@WebServlet("/guest/GuestList2")
public class GuestList2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징 처리 시작
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		Pagination.pageChange(request, pag, pageSize, "", "guest", "");
		  	
  	String viewPage = "/guest/guestList.jsp";
  	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
  	dispatcher.forward(request, response);
	}
}
