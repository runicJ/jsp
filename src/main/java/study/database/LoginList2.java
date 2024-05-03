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
//@WebServlet("/study/database/LoginList")
public class LoginList2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDAO dao = new LoginDAO();
		
		//ArrayList<LoginVO> vos = dao.getLoginList();  // 자료 가져오는 것  // 여러개 vos Array나 ArrayList
		//System.out.println("vos : " + vos);  // vos의 자료가 다 나옴  // 확인 후에 주석 처리할 것
		
		//request.setAttribute("vos", vos);
		
		String viewPage = "/study/database/loginMain.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
