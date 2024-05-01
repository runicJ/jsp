package study.j0430;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
//@WebServlet("/JavaclassInit")  // web.xml에서 호출하는 주소명
@WebServlet(urlPatterns="/GreenJavaclassInit", initParams= {@WebInitParam(name="mid", value="hkd1234"),@WebInitParam(name="pwd", value="123456"),@WebInitParam(name="className", value="영업과")})
public class JavaclassInit extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// web.xml에 설정해둔 초기 변수/값을 가져온다.
		String mid = getInitParameter("mid");
		String pwd = getInitParameter("pwd");
		String className = getInitParameter("className");
		
		System.out.println("아이디 : " + mid);
		System.out.println("비밀번호 : " + pwd);
		System.out.println("부서명 : " + className);
		
		request.setAttribute("mid", mid);
		request.setAttribute("pwd", pwd);
		request.setAttribute("className", className);
		
		// 위에서 설정한 것이 아래로 적용되고 밑으로
		String viewPage = "/study/0430_web_xml/init/ex1_Login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
