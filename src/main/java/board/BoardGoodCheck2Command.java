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
		
		// 좋아요를 누른 게시물 idx를 세션에서 가져옴
//		ArrayList<Integer> likedAt = (ArrayList<Integer>) session.getAttribute("sContentIdx");
//		if (likedAt == null) likedAt = new ArrayList<Integer>();    // Java에서는 List 인터페이스를 직접 인스턴스화할 수 없으며, 대신 ArrayList 또는 다른 구현체를 사용해야 함. 인터페이스가 단순히 메소드의 선언을 포함하고, 실제로는 구현되지 않은 메소드를 가지기 때문. 따라서 인터페이스를 사용하여 객체를 생성할 수 없고, 대신 구체적인 구현체를 사용해야 함. 보통은 ArrayList, LinkedList 등의 구현체를 사용.
//		
//        boolean isLiked = likedAt.contains(idx);
//        session.setAttribute("isLiked", isLiked);
		
		request.setAttribute("sw", sw);
		response.getWriter().write(sw);  // 메시지 전달을 위해 sw 넣어줌  // AJAX 요청을 통해 서버로부터 데이터를 비동기적으로 수신할 때 사용
	}

}
