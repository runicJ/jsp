package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardReplyEditCommand implements BoardInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardIdx = request.getParameter("boardIdx")==null ? 0 : Integer.parseInt(request.getParameter("boardIdx"));
    int pag = request.getParameter("pag")==null ? 0 : Integer.parseInt(request.getParameter("pag"));
    int pageSize = request.getParameter("pageSize")==null ? 0 : Integer.parseInt(request.getParameter("pageSize"));
    
    int modalIdx = request.getParameter("modalIdx")==null ? 0 : Integer.parseInt(request.getParameter("modalIdx"));
    String content = request.getParameter("modalContent")==null ? "" : request.getParameter("modalContent");
    
    BoardReplyVO vo = new BoardReplyVO();
    
    vo.setIdx(modalIdx);
    vo.setContent(content);
    
    BoardDAO dao = new BoardDAO();
    
    int res = dao.setBoardReplyEdit(vo);
    
    if(res != 0) request.setAttribute("message", "댓글이 수정되었습니다.");
    else request.setAttribute("message", "댓글 수정 실패");
    
    request.setAttribute("url", "BoardContent.bo?idx="+boardIdx+"&pag="+pag+"&pageSize="+pageSize);
	}

}
