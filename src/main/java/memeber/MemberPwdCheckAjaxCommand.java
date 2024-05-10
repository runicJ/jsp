package memeber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberPwdCheckAjaxCommand implements MemberInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // ajax로 처리함
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");  // mid를 이용해서 pwd를 찾아와서 입력한 것과 비교해야 하니까 session에 계속 저장 되어 있는 mid를 가져옴(sMid로 저장했었음)
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberIdCheck(mid);
		
		// 저장된 비밀번호에서 salt키를 분리시켜서 다시 암호화 시킨 후 맞는지 비교처리한다.
		String salt = vo.getPwd().substring(0,8);
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(salt + pwd);
		
		String str = "";
		if(!vo.getPwd().substring(8).equals(pwd)) str = "0";
		else str = "1";
		
		response.getWriter().write(str);
	}

}
