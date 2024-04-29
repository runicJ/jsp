package study.j0427;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/j0427/LoginOk")
public class LoginOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		PrintWriter out = response.getWriter();  // out.print 사용 위해서
		if((mid.equals("admin") && pwd.equals("1234")) ||(mid.equals("atom") && pwd.equals("1234"))) {
			// 쿠키에 아이디를 저장/해제 처리한다.
			// 로그인시 아이디저장시킨다고 체크하면 쿠키에 아이디 저장하고, 그렇지 않으면 쿠키에서 아이디를 제거한다.
			String idSave = request.getParameter("idSave")==null ? "off" : "on";
			Cookie cookieMid = new Cookie("cMid", mid);
			cookieMid.setPath("/");  // 상위 폴더에 쿠키값을 저장하겠다(Root에)
			if(idSave.equals("on")) {
				cookieMid.setMaxAge(60*60*24*7);	// 쿠키의 만료시간은 1주일로 한다. // 저장x 기록을 해야함(client cookie 저장소에 저장)
			}
			else {
				cookieMid.setMaxAge(0);
			}
			response.addCookie(cookieMid);
			
			// 필요한 정보를 session에 저장처리한다.
			HttpSession session = request.getSession();
			session.setAttribute("sMid", mid);
			
			// 정상 로그인Ok이후에 모든 처리가 끝나면 Home(index.jsp)으로 보내준다.
			out.print("<script>");
			out.print("alert('"+mid+"님 로그인 되었습니다.');");
			out.print("location.href='"+request.getContextPath()+"/'");
			out.print("</script>");
		}
		else {
			// 회원 인증 실패시 처리... 다시 로그인창으로 보내준다.
			out.print("<script>");
			out.print("alert('로그인 실패~~');");
			out.print("location.href='"+request.getContextPath()+"/study/0428_Login/login.jsp';");
			out.print("</script>");
		}
	}
}
