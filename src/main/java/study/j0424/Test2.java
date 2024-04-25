package study.j0424;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Test2")  // annotation @ MVC의 Controller  // URLMapping /Test2  // get 방식  // post 빙식은 form태그 값 넘겨서
//@WebServlet("/T2")  // jsp와 달리 경로 노출x
@WebServlet("/T22222")  // 서버 환경설정파일(xml, 서블릿)변경시에는 서버가 재시작 되어야 함 // jsp는 클라이언트에서 view는 다시 새로고침만 하면 됨
public class Test2 extends HttpServlet {  // 부모 추상메소드 상속받아서 사용  // Superclass: javax.servlet.http.HttpServlet
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());  // response.getWriter() 메소드(브라우저에다가 명령을 출력하겠다.) 16번에 상속줄이 있어야 사용가능  // Served at: javaclass
		System.out.println("이곳은 Get메소드 입니다.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
