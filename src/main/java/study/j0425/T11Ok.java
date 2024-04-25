package study.j0425;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0425/T11Ok")
public class T11Ok extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		
		// DB에서 mid와 같은 자료가 있는지 검색 후 돌아온다.
		if(!mid.equals("admin") || !pwd.equals("1234")) {
			// jsp로 보내서 다시 로그인하라는 메시지를 띄우도록 한다.
			response.sendRedirect(request.getContextPath() + "/study/0425/t11_Login.jsp?msgFlag=no");  // ?매개변수 get방식 쿼리스트링(문자로 값이 가는 것) // location.href와 거의 같음 // location.href 쓰려면 out 생성해서 js코드 써야함
			return;
		}
		
		// 정상 회원들 처리부분...
		System.out.println("정상로그인 처리됨.....");
		//response.sendRedirect(request.getContextPath() + "/study/0425/t11_Main.jsp?msgFlag=ok&mid="+mid);  // 잠시 딱 멈춰서 getContextPath/... 경로로 가라
		
		String viewPage = "/study/0425/t11_Main.jsp?msgFlag=ok&mid=" + mid;  // http://localhost:9090/javaclass/j0425/T11Ok 경로 이렇게 나옴 "" 아무것도 안쓰면 // request.getContextPath() 써도되는데 안쓰는게 좋음(계속 바뀜)
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);  // 직렬화 // 좌우든 뒤돌아 보지 않고 직진함 // 현재 작업 진행중(멈추는 것 아님) // 16번에 넣어놔서 request. 사용 가능 // 지금까지 response.sendRedirect 사용
		dispatcher.forward(request, response);  // 앞에서 넘어온 자료 그대로 가라 // forward 직렬화 명령어 // 35번줄이 종착역 // 새로고침하면 다시 실행됨(새로고침 조심해야함) DB, VO에 계속 저장됨 // http://localhost:9090/javaclass/j0425/T11Ok  // 파일명은 처음 그대로 멈춰 있음
	}
	
}
