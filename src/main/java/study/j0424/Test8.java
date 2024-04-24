package study.j0424;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet({"/T8"})  // 옛날 프로그램 annotation(5년됨) 없다면  // web.xml에서 등록
public class Test8 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");  // 설정하는 건 set
//		response.getWriter().append("1.Served at: ").append(request.getContextPath());
		System.out.println("이곳은 Get 메소드 입니다.");
		
		PrintWriter out = response.getWriter();  // out 객체 생성
		out.println("<p><a href='/javaclass/study/0424/test8.jsp'>돌아가기</a></p>");
		
//		response.getWriter().append("<p><a href='/javaclass/test8.jsp'>돌아가기</a></p>");  // out.println innerHTML // 서버로 들어갔다 나오면 무조건 한글이 깨짐
	}
	
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.getWriter().append("2.Served at: ").append(request.getContextPath());
	  System.out.println("이곳은 Post 메소드 입니다.");
	  doGet(request, response); // 메소드호출
  }
  // Server Tomcat v9.0 Server at localhost failed to start.  // 같은 url 주소가 있다는 오류
}
