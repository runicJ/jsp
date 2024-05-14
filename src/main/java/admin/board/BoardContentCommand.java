package admin.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminInterface;
import board.BoardDAO;
import board.BoardVO;

public class BoardContentCommand implements AdminInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 1 : Integer.parseInt(request.getParameter("pageSize"));
		
		BoardDAO dao = new BoardDAO();
		
		// 게시글 조회수 1씩 증가시키기(중복방지) => 보통 ip, session(시간) 이용  // session에 장소(자료실, 게시글..), 글 번호 저장 => 배열에 저장(vo처럼) 타입이 각각 다르므로, 객체로 저장 => ArrayList => 글 들어가기 전까지 session 생성 안되어 있음. 끊기면 session 사라짐.
		HttpSession session = request.getSession();
		ArrayList<String> contentReadNum = (ArrayList<String>) session.getAttribute("sContentIdx");  // 타입을 맞춰줘야 해서 강제 타입 변환
		if(contentReadNum == null) contentReadNum = new ArrayList<String>();
		String imsiContentReadNum = "board" + idx;  // board 게시판, 글번호
		if(!contentReadNum.contains(imsiContentReadNum)) {  // 반복문(하나씩 비교) 할 필요 없음, 일렬(중복x)로 들어가 있고 포함되어 있는지 연산자 사용하면됨.
			dao.setBoardReadNumPlus(idx);  // 얘를 먼저 적용해야 조회수가 오르고 글 상세보기
			contentReadNum.add(imsiContentReadNum);  // 추가시켜야 함(add)
		}
		session.setAttribute("sContentIdx", contentReadNum);  // session에도 저장
		BoardVO vo = dao.getBoardContent(idx);		
		request.setAttribute("vo", vo);
		
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		
		// 이전글/다음글 처리
		BoardVO preVo = dao.getPreNextSearch(idx, "preVo");  // preVo 값을 준 것 얘가 있으면 이전글  // 하나로 dao에서 처리하기 위해
		BoardVO nextVo = dao.getPreNextSearch(idx, "nextVo");  // 같은 메소드 써도 된다는 것
		request.setAttribute("preVo", preVo);
		request.setAttribute("nextVo", nextVo);  // vo에 담지 않고 nextVo 변수에 바로 담아서 보냄
	}

}
