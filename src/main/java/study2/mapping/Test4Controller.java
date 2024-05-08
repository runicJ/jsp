package study2.mapping;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.do4")
public class Test4Controller extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Test4Interface command = null;
		String viewPage = "/WEB-INF/study2/mapping/";
		
		String uri = request.getRequestURI();
		
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		
		if(com.equals("test4")) {
			viewPage += "test4.jsp";
		}
		else if(com.equals("list")) {  // 내용을 DB에서 가져와서 뿌려줌
			command = new Test4ListCommand();  // 구현객체
			command.execute(request, response);
			// viewPage = "/WEB-INF/study2/mapping/list.jsp";
			viewPage += "list.jsp";
		}
		else if(com.equals("input")) {  // command 객체를 안만들 경우에 viewPage 부르면 됨(무조건 command 만드는게 아님)
			// viewPage = "/WEB-INF/study2/mapping/input.jsp";
			viewPage += "input.jsp";  // 단지 화면에 view만 보여줌
		}
		else if(com.equals("inputOk")) {
			command = new Test4InputOkCommand();  // DB에 저장되는걸 따로 빼서 생성
			command.execute(request, response);  // execute 메소드가 실행
			viewPage = "include/message.jsp";  // 종착지에서 메시지 뿌릴려고
		}
		else if(com.equals("update")) {
			command = new Test4UpdateCommand();
			command.execute(request, response);
			viewPage += "update.jsp";
		}
		else if(com.equals("updateOk")) {
			command = new Test4UpdateOkCommand();
			command.execute(request, response);
			viewPage = "include/message.jsp";
		}
		else if(com.equals("delete")) {
			command = new Test4DeleteCommand();
			command.execute(request, response);
			viewPage += "delete.jsp";
		}
		else if(com.equals("deleteOk")) {
			command = new Test4DeleteOkCommand();
			command.execute(request, response);
			viewPage = "include/message.jsp";
		}
		else if(com.equals("search")) {
			command = new Test4SearchCommand();
			command.execute(request, response);
			viewPage += "search.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);  // 계속 가야함(Redirect x)
		dispatcher.forward(request, response);
	}
}
