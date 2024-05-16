package study2.modal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memeber.MemberDAO;
import memeber.MemberVO;
import study2.StudyInterface;

public class ModalTestCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");  // 여기서는 처음에 값이 없으니까 null 값이 올 수 있음 "" 안하고 sMid 해도됨
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO mVo = dao.getMemberIdCheck(mid);
		
		request.setAttribute("mVo", mVo);
	}

}
