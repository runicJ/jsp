package photoGallery;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
	// location ="/경로명",	(String)
	fileSizeThreshold = 1024 * 1024,			// 업로드시에 메모리에 저장되는 임시 파일크기 설정(int)
	maxFileSize = 1024 * 1024 * 4,				// 업로드시 1개파일의 최대용량은 5MByte(long)
	maxRequestSize = 1024 * 1024 * 4 * 5	// 한번에 request 시에 전송할 파일의 최대크기(long)
)
@SuppressWarnings("serial")
@WebServlet("/PhotoGalleryInputOk")
public class PhotoGalleryInputOk extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/photoGallery/");
		
		// request 객체 내부 getParts() 메소드를 사용하여 배열로 받는다.
		Collection<Part> fileParts = request.getParts();
		
		String fSNames = "";
		int cnt = 0;
		for(Part filePart : fileParts) {
			if(!filePart.getName().equals("fName")) continue;
			if(filePart.getSize() == 0) continue;
			
			String fileName = filePart.getSubmittedFileName();
			InputStream fis = filePart.getInputStream();
			
			String uid = UUID.randomUUID().toString().substring(0, 8);
			fileName = fileName.substring(0,fileName.lastIndexOf(".")) + "_" + uid + fileName.substring(fileName.lastIndexOf("."));
			
			FileOutputStream fos = new FileOutputStream(realPath + fileName);
			
			byte[] buffer = new byte[2048];
			int size = 0;
			while((size=fis.read(buffer)) != -1) {
				fos.write(buffer, 0, size);
			}
			fos.close();
			fis.close();
			
			fSNames +=fileName + "/";
			cnt++;
		}
		fSNames = fSNames.substring(0, fSNames.length()-1);
		
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String part = request.getParameter("part")==null ? "" : request.getParameter("part");
		String title = request.getParameter("title")==null ? "" : request.getParameter("title");
		String hostIp = request.getParameter("hostIp")==null ? "" : request.getParameter("hostIp");
		
		PhotoGalleryVO vo = new PhotoGalleryVO();
		
		PhotoGalleryDAO dao = new PhotoGalleryDAO();
		
		vo.setIdx(dao.getPhotoGalleryMaxIdx());
		vo.setMid(mid);
		vo.setPart(part);
		vo.setTitle(title);
		vo.setPhotoCount(cnt);
		vo.setHostIp(hostIp);
		vo.setfSName(fSNames);
		
		int res = dao.setPhotoGalleryInput(vo);
		
		if(res != 0) {
			request.setAttribute("message", "파일이 업로드 되었습니다.");
			request.setAttribute("url", "PhotoGallery.ptg");
		}
		else {
			request.setAttribute("message", "파일 업로드 실패~");
			request.setAttribute("url", "PhotoGalleryInput.ptg");
		}
		
		String viewPage = "/include/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
