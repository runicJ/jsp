package study.j0425;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0425/T13Ok")
public class T13Ok extends HttpServlet {
	
	@Override  // 405 오류 신경안씀(get인지 post인지) doGet doPost
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String loginFlag = request.getParameter("loginFlag")==null ? "" : request.getParameter("loginFlag");
		
		if(loginFlag.equals("javascript")) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='"+request.getContextPath()+"/study/0425/t13Res.jsp?mid="+mid+"&pwd="+pwd+"&loginFlag="+loginFlag+"';");  // get방식 쿼리스트링(?변수=값) 패스베어리어블(/경로명만 노출)
			out.println("</script>");
		}
		else if(loginFlag.equals("response")) {
			response.sendRedirect(request.getContextPath() + "/study/0425/t13Res.jsp?mid="+mid+"&pwd="+pwd+"&loginFlag=" + loginFlag);
		}
		else {
//			String viewPage = "/study/0425/t13Res.jsp?mid="+mid+"&pwd="+pwd+"&loginFlag="+loginFlag;  // request 객체로 직진하고 있는 중 getContextPath() 안적어도 여기에 있다고 감지중  // 매개변수에 값을 넘기기 때문에 변수로 받아야함
			request.setAttribute("mid", mid);  // request란 저장소에 저장해두고 EL로 불러옴
			request.setAttribute("pwd", pwd);
			request.setAttribute("loginFlag", loginFlag);
			
			String viewPage = "/study/0425/t13Res.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);  // 한번만 담는게 아니라 여러번 request할 수 있다는 장점
			dispatcher.forward(request, response);  // 주소보면 종착지가 다음 // 목적지(viewPage)만 보고감  // 어디로 가는지 알고 가져가는 경우에 (queryString으로) t13Res 위에처럼 request.getParameter로 받음 // vo 넘긴 것과 다름 옆에서 짐을 담은 것 attribute 저장소에 저장한 것 => EL로 꺼냄 
		}
		
	}
	
}
