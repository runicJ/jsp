package webMessage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Pagination;

public class WebMessageCommand2 implements WebMessageInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		// 웹 메시지를 보여주는 것이기도 하고, left right list를 보여줌(이미 내용이 다 떠있는 것 => 내용 보든 안보든 받는 것을 미리 처리해야함)
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));  // 얘는 content만 보내면 됨
		int mSw = request.getParameter("mSw")==null ? 1 : Integer.parseInt(request.getParameter("mSw"));  // 0은 메시지 작성으므로 처음 화면이 받은 메시지로 보이게 하겠다
		int mFlag = request.getParameter("mFlag")==null ? 0 : Integer.parseInt(request.getParameter("mFlag"));

		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		WebMessageDAO dao = new WebMessageDAO();
		WebMessageVO vo = null;
		
		if(mSw == 6) {  // 성격 다른 얘를 꺼내 놓음
			vo = dao.getWebMessageContent(idx, mFlag);
			request.setAttribute("vo", vo);
			request.setAttribute("pag", pag);
			request.setAttribute("pageSize", pageSize);
		}
		else {
			// 페이징 처리(나중에 추가)
		  			
			//ArrayList<WebMessageVO> vos = dao.getMessageList(mid, mSw);  // startIndex... 나중에 추가
			//request.setAttribute("vos", vos);
			
			String part = mid + "/" + mSw;
			
			Pagination.pageChange(request, pag, pageSize, "", "wm", part);
		}
		request.setAttribute("mSw", mSw);
		request.setAttribute("mFlag", mFlag);
	}

}
