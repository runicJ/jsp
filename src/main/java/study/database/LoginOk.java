package study.database;

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
@WebServlet("/database/LoginOk")
public class LoginOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		LoginDAO dao = new LoginDAO();  // 객체 생성 후 db 연동 여부 확인 가능(LoginDAO)
		
//		LoginVO vo = new LoginVO();
		LoginVO vo = dao.getLoginIdCheck(mid, pwd);  // 위에 보다 이렇게 쓰는 것이 좋다.  // vo로 가져오면 정보를 전부 가져올 수 있음
		//System.out.println("vo : " + vo);
		
		PrintWriter out = response.getWriter();
//		if(vo == null)  // 값이 없어도 null로 불러져옴 => null로 비교하면 안됨
		if(vo.getMid() != null) {
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
			session.setAttribute("sMid", mid);  // 세션에 많이 담으면 무거워지므로
			
			// 회원의 성명을 세션에 저장하기 위해 DB에서 가져온 name을 세션에 저장처리한다.
			session.setAttribute("sName", vo.getName());
			
			out.println("<script>");
			out.println("alert('"+mid+"님 로그인 되었습니다.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/LoginList';");
//			out.println("location.href='"+request.getContextPath()+"/study/database/JoinList';");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('로그인 실패~~');");
			out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp';");
			out.println("</script>");
		}
	}
}
