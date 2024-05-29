package memeber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nickName = (String) session.getAttribute("sNickName");  // 로그인되서 온 거니까 null값 처리 안함
		
		session.invalidate();  // 저장했던 session 날림
		
		request.setAttribute("message", nickName+"님 로그아웃 되셨습니다.");  // 메시지 보냄
		request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");  // 로그인 창으로 보냄
	}

}
