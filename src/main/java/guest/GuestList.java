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
@WebServlet("/guest/GuestList")
public class GuestList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int level = (int) session.getAttribute("sLevel");
		String contentsShow = "";
		if(level == 0) contentsShow = "adminOK";
		else contentsShow = (String) session.getAttribute("sMid");
		
		// 페이징 처리 시작
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		Pagination.pageChange(request, pag, pageSize, contentsShow, "guest", "");
		  	
  	String viewPage = "/guest/guestList.jsp";
  	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
  	dispatcher.forward(request, response);
	}
}
