package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardGoodCheck2Command implements BoardInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = new BoardDAO();
		
		// 좋아요 수 증가처리(중복불허)
		String sw = "0";
		HttpSession session = request.getSession();
		ArrayList<String> contentGood = (ArrayList<String>) session.getAttribute("sContentGood");
		if(contentGood == null) contentGood = new ArrayList<String>();  // contentReadNum 객체가 없으면 생성해주세요
		String imsiContentGood = "boardGood" + idx;
		if(!contentGood.contains(imsiContentGood)) {
			dao.setBoardGoodCheck(idx);
			contentGood.add(imsiContentGood);
			sw = "1";  // 통과하면 사용한 것
		}
		session.setAttribute("sContentGood", contentGood);
		
		response.getWriter().write(sw);  // 메시지 전달을 위해 sw 넣어줌
	}

}
