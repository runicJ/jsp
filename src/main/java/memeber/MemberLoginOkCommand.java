package memeber;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLoginOkCommand implements MemberInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberIdCheck(mid);
		
		// 아래로 회원 인증 처리  (SHA-256 하기 전이라서 아래처럼 간단히 처리 // 회원가입시 비번 암호화 후에 처리)
		if(vo.getPwd() == null || !vo.getPwd().equals(pwd)) {
			request.setAttribute("message", "아이디를 확인하세요.");  // 회원정보가 없습니다.  // 메시지 전달
			request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");  // 어디로 보낼건지
			return;  // 끊어주려면
		}
		
		// 로그인 체크 완료 후에 처리할 내용....(쿠키/세션/...)		
		// 회원이 맞으면 vo.getMid값이 null이 아니다.
		// 회원일때 처리할 부분
		/*
		 * 1.방문포인트지급:매번 10pt씩 지급, 단 1일 최대 50pt까지만 지급 // 날짜비교 6번부터는 지급x
		 * 2.최종접속일 처리, 방문카운트(일일방문카운트, 전체누적방문카운트)
		 */
		// 쿠키에 아이디를 저장/해제 처리한다.
		// 로그인시 아이디저장시킨다고 체크하면 쿠키에 아이디 저장하고, 그렇지 않으면 쿠키에서 아이디를 제거한다.  // 쿠키말고 클라이언트에 저장하는거 나중에
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
		
		// 등급레벨별 등급명칭을 저장한다.
		String strLevel = "";
		if(vo.getLevel() == 0) strLevel="관리자";
		else if(vo.getLevel() == 1) strLevel = "준회원";
		else if(vo.getLevel() == 2) strLevel = "정회원";
		else if(vo.getLevel() == 3) strLevel = "우수회원";  // 비회원은 못들어오니까 저장필요x
		
		// 필요한 정보를 session에 저장처리한다.
		HttpSession session = request.getSession();
		session.setAttribute("sMid", mid);
		session.setAttribute("sNickName", vo.getNickName());  // vo에서 가져와서 sNickName에 저장
		session.setAttribute("sLevel", vo.getLevel());  // 숫자는 비교하기 위해서 대부분 문자로 저장  // 세션에 해당 등급 숫자가 무엇인지 저장
		session.setAttribute("strLevel", strLevel);
		
		request.setAttribute("message", mid+"님 로그인 되셨습니다.");
		request.setAttribute("url", request.getContextPath()+"/MemberMain.mem");
	}

}
