package memeber;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webMessage.WebMessageVO;

public class MemberMainCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");  // session
		
		MemberDAO dao = new MemberDAO();
		MemberVO mVo = dao.getMemberIdCheck(mid);  // member

		// 로그인 회원이 활동한 '방명록/게시판/자료실'에 올린 글수 가져오기
		int guestCnt = dao.getMemberGuestSearch(mid, mVo.getName(), mVo.getNickName());
		int boardCnt = dao.getMemberBoardSearch(mid);
		int pdsCnt = dao.getMemberPdsSearch(mid);
		
		request.setAttribute("mVo", mVo);
		request.setAttribute("guestCnt", guestCnt);
		request.setAttribute("boardCnt", boardCnt);
		request.setAttribute("pdsCnt", pdsCnt);
		
		// 로그인 회원에게 온 신규 메세지 건수 발송자 아이디 보여주기
		ArrayList<WebMessageVO> wmVos = dao.getMemberWebMessage(mid); 
		
		request.setAttribute("wmVos", wmVos);
		request.setAttribute("wmCnt", wmVos.size());
		
		// 오늘 일정 출력하기
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(today);
		
		int scheduleCnt = dao.getMemberScheduleSearch(mid, ymd);
		request.setAttribute("ymd", ymd);
		request.setAttribute("scheduleCnt", scheduleCnt);
		
	}

}
