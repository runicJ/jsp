package admin.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminDAO;
import admin.AdminInterface;

public class ReviewReplyInputOkCommand implements AdminInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewIdx = request.getParameter("reviewIdx")==null ? 0 : Integer.parseInt(request.getParameter("reviewIdx"));
		String replyMid = request.getParameter("replyMid")==null ? "" : request.getParameter("replyMid");
		String replyNickName = request.getParameter("replyNickName")==null ? "" : request.getParameter("replyNickName");
		String replyContent = request.getParameter("replyContent")==null ? "" : request.getParameter("replyContent");
		
		AdminDAO dao = new AdminDAO();
		ReviewVO vo = new ReviewVO();
		
		vo.setIdx(reviewIdx);
		vo.setReplyMid(replyMid);
		vo.setReplyNickName(replyNickName);
		vo.setReplyContent(replyContent);
		
		int res = dao.setReviewReplyInputOk(vo);
		
		response.getWriter().write(res);
	}

}
