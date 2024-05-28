package webMessage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Pagination2;
import memeber.MemberDAO;
import memeber.MemberVO;

public class WebMessageCommand implements WebMessageInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int mSw = request.getParameter("mSw")==null ? 1 : Integer.parseInt(request.getParameter("mSw"));
		int mFlag = request.getParameter("mFlag")==null ? 0 : Integer.parseInt(request.getParameter("mFlag"));
		
		// 메세지 내용 상세보기에서 돌아가기 버튼처리..
		if(mSw == 1) {
			if(mFlag == 0 || mFlag == 11) mSw = 1;
			else if(mFlag == 12) mSw = 2;
			else if(mFlag == 13) mSw = 3;
			else if(mFlag == 15) mSw = 5;
		}
		
		WebMessageDAO dao = new WebMessageDAO();
		WebMessageVO vo = null;
		
		if(mSw == 6) {		// '메세지 내용보기' 처리하기
			vo = dao.getWebMessageContent(idx, mFlag);
			request.setAttribute("vo", vo);
		}
		else if(mSw == 0) {		// '메세지작성'버튼클릭
			MemberDAO mDao = new MemberDAO();
			ArrayList<MemberVO> mVos = mDao.getMemberList();
			request.setAttribute("mVos", mVos);
		}
		else {
			// 페이징처리
			int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		  int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		  
		  int totRecCnt = dao.getWmTotRecCnt(mid, mSw);
		  int startIndexNo = (pag - 1) * pageSize;
			
			ArrayList<WebMessageVO> vos = dao.getMessageList(mid, mSw, startIndexNo, pageSize);
			request.setAttribute("vos", vos);
			
			Pagination2.pageChange(request, pag, pageSize, totRecCnt, startIndexNo);
		}
		request.setAttribute("mSw", mSw);
		request.setAttribute("mFlag", mFlag);
	}

}
