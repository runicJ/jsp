package study2.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.do5")
public class Test5Controller extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Test4Interface command = null;
		String viewPage = "WEB-INF/study2/mapping/";
		
		String uri = request.getRequestURI();
		
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		
		if(com.equals("test5")) {
			viewPage += "test5.jsp";
		}
		else if(com.equals("list")) {
			command = new Test5ListOkCommand();
			viewPage += "list.jsp";
		}
	}
}
