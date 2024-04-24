package study.j0424;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/T10")
public class Test10 extends HttpServlet {  // 서블릿 작업준비  // 서블릿, java 모두 사용시 Class 파일로  // throws ServletException, IOException 필수로 넣고 HttpServlet이 가져감
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int su = Integer.parseInt(request.getParameter("su"));    // 값이 없어도 에러 발생x // ?su=100는 웹 서버로 넘어감 // was에서 su 값을 요청 값 받고 처리하고 ws로 넘기고 client에 값을 돌려줌
		
		int tot = 0;
		for(int i=1; i<=su; i++) {
			tot += i;
		}
		System.out.println("tot : " + tot);
		
		PrintWriter out = response.getWriter();  // out이라는 이름을 jsp에서 사용하는 out.println처럼 사용하겠다 // innerHTML
		out.println("이곳은 서블릿에서 보냅니다.");
		out.println("<p><input type='button' value='돌아가기' onclick='location.href=\"/javaclass/study/0424/test10OK.jsp?tot="+tot+"\"' /></p>");
	}
}
