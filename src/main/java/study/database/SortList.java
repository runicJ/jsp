package study.database;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/database/SortList")
public class SortList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort = request.getParameter("sort")==null ? "" : request.getParameter("sort");
		
		LoginDAO dao = new LoginDAO();
		
		ArrayList<LoginVO> vos = dao.getSortList(sort);
//		System.out.println("sort : " + sort);
		
		request.setAttribute("vos", vos);
		request.setAttribute("sort", sort);  // 이 값을 보내서 EL로 써서 뭐로 정렬했는지 고정시킴
		
		String viewPage = "/study/database/loginMain.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
