package memeber;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberPwdChangeCheckCommand implements MemberInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pwd = request.getParameter("pwdCheck")==null ? "" : request.getParameter("pwdCheck");
		
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");  // 현재 로그인한 사람의 아이디에 해당하는 유저 찾아서 변경
		
		MemberDAO dao = new MemberDAO();
		
		String salt = UUID.randomUUID().toString().substring(0,8);  // pwd 가져올 필요 없고 pwd 다시 DB 입력이니까 salt키도 다시 생성
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(salt + pwd);
		
		int res = dao.setMemberPwdChange(mid, (salt + pwd));
		
		if(res != 0) {
			request.setAttribute("message", "비밀번호가 변경되었습니다.\\n다시 로그인 해주세요.");
			request.setAttribute("url", "MemberLogout.mem");
		}
		else {
			request.setAttribute("message", "비밀번호가 변경 실패~~");
			request.setAttribute("url", "MemberPwdCheck.mem");			
		}
	}

}
