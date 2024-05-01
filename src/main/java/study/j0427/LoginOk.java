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
//		String last = request.getParameter("last")==null ? "" : request.getParameter("last");
		
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
			
//			Cookie cookieLast = new Cookie("cLast", last);
//			System.out.println("last : " + last);
//			cookieLast.setPath("/");
//			response.addCookie(cookieLast);
			
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

/*
 * <% // 세션의 최초 생성시간 long creationTime = session.getCreationTime();
 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
 * String creationTimeString = dateFormat.format(new Date(creationTime));
 * 
 * // 서버로 클라이언트가 마지막으로 요청한 시간 long accessedTime = session.getLastAccessedTime();
 * String accessedTimeString = dateFormat.format(new Date(accessedTime)); %>
 * <li>세션의 최초 생성시간 : <%=creationTimeString %></li> <li>클라이언트의 마지막 요청시간 :
 * <%=accessedTimeString %></li> </ul> <h2>세션 및 리퀘스트 영역에 저장</h2> <%
 * request.setAttribute("requestScope", "리퀘스트 영역입니다");
 * session.setAttribute("sessionScope", "세션 영역입니다"); %>
 */