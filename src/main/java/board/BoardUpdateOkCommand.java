package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardUpdateOkCommand implements BoardInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 1 : Integer.parseInt(request.getParameter("pageSize"));
		
		String title = request.getParameter("title")==null ? "" : request.getParameter("title");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String hostIp = request.getParameter("hostIp")==null ? "" : request.getParameter("hostIp");
		String openSw = request.getParameter("openSw")==null ? "" : request.getParameter("openSw");
		
		BoardVO vo = new BoardVO();
		
		vo.setIdx(idx);
		title = title.replace("<", "&lt;").replace(">", "&gt;");
		vo.setTitle(title);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		vo.setOpenSw(openSw);
		
		BoardDAO dao = new BoardDAO();
		
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		
		int res = dao.setBoardUpdate(vo);
		
		if(res != 0) request.setAttribute("message", "게시글이 수정되었습니다.");
		else request.setAttribute("message", "게시글 수정 실패~~");
		
		request.setAttribute("url", "BoardContent.bo?idx="+idx+"&pag="+pag+"&pageSize=" + pageSize);  // 성공을 하던 실패를 하던 BoardContent로 보냄
	}

}
