package study.j0425;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0425/T05Ok")
public class T05Ok extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		// Front에서 넘어온 값들을 변수에 담아서 처리한다.
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		
		String loginOk = "";
		if(mid.equals("admin") && pwd.equals("1234")) loginOk = "OK";
		else loginOk = "NO";
		
		PrintWriter out = response.getWriter();
		
		if(loginOk.equals("OK")) {
		out.println("<script>");  // 메시지 보내는 것이니까 script
		out.println("alert('"+mid+"님 로그인 되었습니다.');"); 
		out.println("location.href='"+request.getContextPath()+"/study/0425/t05Main.jsp?mid="+mid+"';"); 
		out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('로그인 실패~~');"); 
			out.println("location.href='"+request.getContextPath()+"/study/0425/t05_Login.jsp';"); 
			out.println("</script>");
		}
	}
	
}
