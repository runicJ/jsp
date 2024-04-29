package study.j0427;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/j0427/T5_StorageTestOk")
public class T5_StorageTestOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String mid = request.getParameter("mid");
		System.out.println("T5_StorageTestOk : mid = " + mid);
		
		// 세션 객체 생성
		HttpSession session = request.getSession();  // 인터페이스
		session.setAttribute("sMid", mid);  // 자주 사용  // sendRedirect 사용해야하는지 아니면 forward 사용해야 할지 잘 생각하고 사용
		
		// 어플리케이션 생성
		ServletContext application = session.getServletContext();  // ServletContext라는 Container  // ServletContext 변수명 <= HttpServletRequest>HttpServlet // DI(dependency injection) 의존성 주입  // IOC
		application.setAttribute("aMid", mid);
		
		response.sendRedirect(request.getContextPath() + "/study/0427_storage/t5_StorageTest.jsp");
	}
}
// OOP 객체지향 / 객체지향 보완 AOP