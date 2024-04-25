package study.j0425;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0425/T10Ok")
public class T10Ok extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		
		// DB에서 mid와 같은 자료가 있는지 검색 후 돌아온다.
		if(!mid.equals("admin") || !pwd.equals("1234")) {
			// jsp로 보내서 다시 로그인하라는 메시지를 띄우도록 한다.
			response.sendRedirect(request.getContextPath() + "/study/0425/t10_Login.jsp?msgFlag=no");  // response.sendRedirect : 가다가 멈춰서 어디로 가야할지 방향을 보고 가는 것 // ?매개변수 get방식 쿼리스트링(문자로 값이 가는 것) // location.href와 거의 같음 // location.href 쓰려면 out 생성해서 js코드 써야함
			return;
		}
		
		// 정상 회원들 처리부분...
		System.out.println("정상로그인 처리됨.....");
		response.sendRedirect(request.getContextPath() + "/study/0425/t10_Main.jsp?msgFlag=ok&mid="+mid);
	}
	
}
