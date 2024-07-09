package photoGallery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotoGallerySingleCommand implements PhotoGalleryInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/photoGallery");
		
		String[] files = new File(realPath).list();
		
		PhotoGalleryDAO dao = new PhotoGalleryDAO();
		
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		int startIndexNo = (pag - 1) * pageSize;
		
		if(pag == 1) {
			dao.setPhotoSingleDelete();
			for(String file : files) {
				System.out.println("file : " + file);
				dao.setPhotoSingleInput(file);
			}
			System.out.println("총 : " + files.length);
		}
		
		ArrayList<String[]> vos = dao.getPhotoGallerySingleList(startIndexNo, pageSize);
		
		request.setAttribute("vos", vos);
	}

}
