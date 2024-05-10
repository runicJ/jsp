package memeber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberPwdCheckOkCommand implements MemberInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ajax X
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");  // 입력한 비밀번호
		
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberIdCheck(mid);  // 정보를 DB에서 꺼내옴
		
		// 저장된 비밀번호에서 salt키를 분리시켜서 다시 암호화 시킨 후 맞는지 비교처리한다.
		String salt = vo.getPwd().substring(0,8);
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(salt + pwd);  // 분리시킨 비밀번호와 비교
		
		if(!vo.getPwd().substring(8).equals(pwd)) {
			request.setAttribute("message", "비밀번호를 확인하세요!");
			request.setAttribute("url", "MemberPwdCheck.mem");			
		}
		else {
			request.setAttribute("message", "NO");  // message.jsp에서 NO일 경우 url로 바로 보낸다는 것
			request.setAttribute("url", "MemberUpdate.mem");						
		}		
	}

}
