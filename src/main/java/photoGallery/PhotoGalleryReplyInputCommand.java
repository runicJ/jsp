package photoGallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardReplyVO;

public class PhotoGalleryReplyInputCommand implements PhotoGalleryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		int photoIdx = request.getParameter("photoIdx")==null ? 0 : Integer.parseInt(request.getParameter("photoIdx"));
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		
		PhotoGalleryVO vo = new PhotoGalleryVO();
		
		vo.setMid(mid);
		vo.setIdx(photoIdx);
		vo.setContent(content);
		
		PhotoGalleryDAO dao = new PhotoGalleryDAO();
		
		int res = dao.setReplyInput(vo);
		
		response.getWriter().write(res + "");
	}

}
