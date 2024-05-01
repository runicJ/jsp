package study.j0430;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/j0430/T03_init")
public class T03_init extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 T03_init 서블릿 입니다.");
		
		String logoName = getServletContext().getInitParameter("logoName");  // 이 서블릿 컨테이너 안에 모두 들어 있음
		String homeAddress = getServletContext().getInitParameter("homeAddress");
		
		HttpSession session = request.getSession();  // 필터에서 안하는 이유 session을 사용 못하기 때문(jsp에서는)
		
		session.setAttribute("sLogoName", logoName);  // 세션에 저장 // 세션에 넣는 것 헷갈릴 수 있으니 s 붙임
		session.setAttribute("sHomeAddress", homeAddress);
		
		String viewPage = "/study/0430_web_xml/init/t03_Init.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
