package board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminDAO;

public class BoardContentCommand implements BoardInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 1 : Integer.parseInt(request.getParameter("pageSize"));
		String flag = request.getParameter("flag")==null ? "" : request.getParameter("flag");
		String search = request.getParameter("search")==null ? "" : request.getParameter("search");
		String searchString = request.getParameter("searchString")==null ? "" : request.getParameter("searchString");
		
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
		
		ArrayList<String> contentGood = (ArrayList<String>) session.getAttribute("sContentGood");
    if(contentGood == null) contentGood = new ArrayList<String>();
    String imsiContentGood = "boardGood" + idx;
    
    String liked = "1";
    if(!contentGood.contains(imsiContentGood)) {
    	liked = "0";
    }
    request.setAttribute("liked", liked);
    
    ArrayList<String> contentLike = (ArrayList<String>) session.getAttribute("sContentLike");
    if(contentLike == null) contentLike = new ArrayList<String>();
    String imsiContentLike = "boardLike" + idx;
    
    String like = "1";
    if(!contentLike.contains(imsiContentLike)) {
        like = "0";
    }
    request.setAttribute("like", like);

		BoardVO vo = dao.getBoardContent(idx);		
		request.setAttribute("vo", vo);
		
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		
		// 이전글/다음글 처리
		BoardVO preVo = dao.getPreNextSearch(idx, "preVo");  // preVo 값을 준 것 얘가 있으면 이전글  // 하나로 dao에서 처리하기 위해
		BoardVO nextVo = dao.getPreNextSearch(idx, "nextVo");  // 같은 메소드 써도 된다는 것
		request.setAttribute("preVo", preVo);
		request.setAttribute("nextVo", nextVo);  // vo에 담지 않고 nextVo 변수에 바로 담아서 보냄
		
		// 신고글 유무 처리하기
		AdminDAO adminDAO = new AdminDAO();
		String call_112 = adminDAO.getCall_112("board", idx);
		
		request.setAttribute("call_112", call_112);
		request.setAttribute("flag", flag);
		request.setAttribute("search", search);
		request.setAttribute("searchString", searchString);
		
		// 댓글 처리
		ArrayList<BoardReplyVO> replyVos = dao.getBoardReply(idx);  // 해당 게시글의 idx를 가져가기
		request.setAttribute("replyVos", replyVos);
	}
}
