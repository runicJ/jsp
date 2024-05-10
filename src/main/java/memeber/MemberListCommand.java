package memeber;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListCommand implements MemberInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberVO> vos = dao.getMemberList();  // 아무것도 안 넣어서 보내고 리스트를 전부 가져옴(화면에 보여줄때 정보공개 거부한 사람 나눠서 보여줌)
		
		request.setAttribute("vos", vos);
	}

}
