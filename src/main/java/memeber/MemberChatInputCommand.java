package memeber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberChatInputCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  // 세션 jsp에서 안넘겼으니 여기서 생성
		String mid = (String) session.getAttribute("sMid");
		
		String chat = request.getParameter("chat")==null ? "" : request.getParameter("chat");
		chat = chat.replace("<", "&lt;").replace(">", "&gt;");  // 태그가 먹지 않도록
		
		MemberDAO dao = new MemberDAO();
		dao.setMemberChatInputOk(mid, chat);  // vo 안만들었으니까 여기서 그냥 넘김
	}

}
