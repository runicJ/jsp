package photoGallery;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDAO;

public class PhotoGalleryGoodCheckCommand implements PhotoGalleryInterface {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		PhotoGalleryDAO dao = new PhotoGalleryDAO();
		
		// 좋아요수 증가처리(중복 불허)
		String sw = "0";
		HttpSession session = request.getSession();
		ArrayList<String> contentGood = (ArrayList<String>) session.getAttribute("sContentGood");
		if(contentGood == null) contentGood = new ArrayList<String>();
		String imsiContentGood = "photoGalleryGood" + idx;
		if(!contentGood.contains(imsiContentGood)) {
			dao.setPhotoGalleryGoodCheck(idx);
			contentGood.add(imsiContentGood);
			sw = "1";
		}
		session.setAttribute("sContentGood", contentGood);
		
		response.getWriter().write(sw);
	}

}
