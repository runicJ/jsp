package memeber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSearchCommand implements MemberInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberIdCheck(mid);  // 한 건이면 vo로 한 건 가져오는 것
		
		vo.setAddress("(우) " + vo.getAddress().replace("/", " "));;
		
		request.setAttribute("vo", vo);  // 기차(request 저장소)에 실어주기
	}

}
