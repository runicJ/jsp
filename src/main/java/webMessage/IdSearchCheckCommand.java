package webMessage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memeber.MemberVO;

public class IdSearchCheckCommand implements WebMessageInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		WebMessageDAO dao = new WebMessageDAO();
		
		ArrayList<MemberVO> mVos = dao.getIdSearchCheck(mid);
		
		request.setAttribute("mVos", mVos);
		request.setAttribute("mSw", 0);
		request.setAttribute("mSwCheck", 0);
		
	}

}
