package photoGallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotoGalleryReplyDeleteCommand implements PhotoGalleryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		PhotoGalleryDAO dao = new PhotoGalleryDAO();
		
		int res = dao.setPhotoGalleryReplyDelete(idx);
		
		response.getWriter().write(res + "");
	}

}
