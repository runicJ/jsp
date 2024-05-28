package webMessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WmRestoreCommand implements WebMessageInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
				
		WebMessageDAO dao = new WebMessageDAO();
		
		WebMessageVO vo = dao.getWebMessageIxdSearch(idx);
		
		if(vo.getSendId().equals(mid)) {
			dao.setWmRestore(idx, "s");
			//request.setAttribute("mSw", 3);
			request.setAttribute("url", "WebMessage.wm?mSw=3");
		}
		else {
			dao.setWmRestore(idx, "r");
			//request.setAttribute("mSw", 1);
			request.setAttribute("url", "WebMessage.wm?mSw=1");
		}
		request.setAttribute("message", "복원되었습니다.");
	}

}
