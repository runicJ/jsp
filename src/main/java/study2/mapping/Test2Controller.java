package study2.mapping;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet({"*.do"})  // 확장자 패턴(확장자 do를 쓰면 무조건 내게로 오기)  // 명칭 관계없이 확장자만
public class Test2Controller extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 *.do 서블릿 입니다.");
		
		int su1 = request.getParameter("su1")==null ? 0 : Integer.parseInt(request.getParameter("su1"));
		int su2 = request.getParameter("su2")==null ? 0 : Integer.parseInt(request.getParameter("su2"));
				
		request.setAttribute("su1", su1);
		request.setAttribute("su2", su2);
//		request.setAttribute("hap", (su1 + su2));
//		request.setAttribute("cha", (su1 - su2));
		
		Test1Service service = new Test1Service();  // 위의 처리를 해줄 service 객체 만들어줌
		int hap = service.hap(su1, su2);  // hap 메소드를 서비스에 만듬
		int cha = service.cha(su1, su2);
		request.setAttribute("hap", hap);
		request.setAttribute("cha", cha);
		
		String viewPage = "/WEB-INF/study2/mapping/test2.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
