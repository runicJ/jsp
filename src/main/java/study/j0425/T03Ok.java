package study.j0425;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/T03Ok")
public class T03Ok extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		// Front에서 넘어온 값들을 변수에 담아서 처리한다.
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");  // null은 nullpoint 오류 나옴 그러나 공백은 체크 못함
		int age = (request.getParameter("age")== null || request.getParameter("age")== "") ? 0 : Integer.parseInt(request.getParameter("age"));  // 숫자는 기본값 0으로

		System.out.println("성명 : " + name);
		System.out.println("나이 : " + age);
		
		
	}
	
}
