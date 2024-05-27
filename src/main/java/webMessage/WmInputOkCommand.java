package webMessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WmInputOkCommand implements WebMessageInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sendId = request.getParameter("sendId")==null ? "" : request.getParameter("sendId");
		String receiveId = request.getParameter("receiveId")==null ? "" : request.getParameter("receiveId");
		String title = request.getParameter("title")==null ? "" : request.getParameter("title");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		
		WebMessageVO vo = new WebMessageVO();
		vo.setSendId(sendId);
		vo.setReceiveId(receiveId);
		vo.setTitle(title);
		vo.setContent(content);

		WebMessageDAO dao = new WebMessageDAO();
		
		int res = dao.setWmInputOk(vo);
		
		if(res != 0) {
			request.setAttribute("message", "메시지가 전송되었습니다.");
			request.setAttribute("url", "WebMessage.wm?mSw=1");
		}
		else {
			request.setAttribute("message", "메시지가 전송실패~~");
			request.setAttribute("url", "WebMessage.wm?mSw=0");
		}
	}

}
