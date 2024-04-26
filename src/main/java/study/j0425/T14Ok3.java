package study.j0425;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0425/T14Ok3")
public class T14Ok3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 T14Ok3 입니다.");
		
		// 값을 꺼냄
		String mid = (String) request.getAttribute("mid");  // request는 객체 이므로 String으로 강제 형변환
		String pwd = (String) request.getAttribute("pwd");
		String secureKey = (String) request.getAttribute("secureKey");
		String secureMasterKey = (String) request.getAttribute("secureMasterKey");
		String userPwd = pwd + secureKey;
		
		String viewPage = "";
		if(!mid.equals("admin") || !userPwd.equals("1234"+secureMasterKey)) {  // 비밀번호 보안키 추가해서 넣는 것으로 함  // 원래 보안키 따로 받아서 보내는 것
			request.setAttribute("loginFlag", "NO");
			viewPage = "/study/0425/t14_forward.jsp";
		}
		else {
			request.setAttribute("loginFlag", "OK");
//			viewPage = "/study/j0425/T14_Main.jsp";
			viewPage = "/study/0425/t14_Res.jsp";
		}
		RequestDispatcher dispatchar = request.getRequestDispatcher(viewPage);
		dispatchar.forward(request, response);
	}
}
