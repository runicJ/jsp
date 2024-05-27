package webMessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WmDeleteOneCommand implements WebMessageInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int mFlag = request.getParameter("mFlag")==null ? 0 : Integer.parseInt(request.getParameter("mFlag"));
		
		WebMessageDAO dao = new WebMessageDAO();
		
		int res = dao.setWmDeleteCheck(idx, mFlag);
		
		response.getWriter().write(res + "");
	}

}
