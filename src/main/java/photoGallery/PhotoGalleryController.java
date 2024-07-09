package photoGallery;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.ptg")
public class PhotoGalleryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PhotoGalleryInterface command = null;
		String viewPage = "/WEB-INF/photoGallery";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"), com.lastIndexOf("."));
		
		// 인증....처리.....
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
		
		if(level > 4) {
			request.setAttribute("message", "로그인후 사용하세요");
			request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/PhotoGallery")) {
			command = new PhotoGalleryCommand();
			command.execute(request, response);
			viewPage += "/photoGalleryList.jsp";
		}
		else if(com.equals("/PhotoGalleryPaging")) {
			command = new PhotoGalleryCommand();
			command.execute(request, response);
			viewPage += "/photoGalleryPaging.jsp";
		}
		else if(com.equals("/PhotoGalleryInput")) {
			viewPage += "/photoGalleryInput.jsp";
		}
		else if(com.equals("/PhotoGallerContent")) {
			command = new PhotoGallerContentCommand();
			command.execute(request, response);
			viewPage += "/photoGallerContent.jsp";
		}
		else if(com.equals("/PhotoGalleryReplyInput")) {
			command = new PhotoGalleryReplyInputCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/PhotoGalleryReplyDelete")) {
			command = new PhotoGalleryReplyDeleteCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/PhotoGalleryGoodCheck")) {
			command = new PhotoGalleryGoodCheckCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/PhotoGallerySingle")) {
			command = new PhotoGallerySingleCommand();
			command.execute(request, response);
			viewPage += "/photoGallerySingle.jsp";
		}
		else if(com.equals("/PhotoGallerySinglePaging")) {
			command = new PhotoGallerySingleCommand();
			command.execute(request, response);
			viewPage += "/photoGallerySinglePaging.jsp";
		}
//		else if(com.equals("/PhotoGallerySinglePaging")) {
//			command = new PhotoGallerySinglePagingCommand();
//			command.execute(request, response);
//			viewPage += "/photoGallerySinglePaging.jsp";
//		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);		
	}
}
