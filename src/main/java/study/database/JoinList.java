package study.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/study/database/JoinList")
public class JoinList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDAO dao = new LoginDAO();
		
		ArrayList<LoginVO> vos = dao.getJoinList();
		System.out.println("vos : " + vos);
		
		request.setAttribute("vos", vos);
		
		String viewPage = "/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
//		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession();
//		session.setAttribute("vos", vos);
//		out.println("<script>");
//		out.println("location.href='"+request.getContextPath()+"';");
//		out.println("</script>");
	}
}
