package memeber;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")  // 필터 통과하고 제일 먼저 들어옴
@WebServlet("*.mem")  // 확장자 패턴으로
public class MemberController extends HttpServlet {  // 4
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberInterface command = null;  // command 객체라서 변수명 이렇게 줌, 인터페이스이름 쓰고 마우스 올려서 interface create
		String viewPage = "/WEB-INF/member";
		
		String com = request.getRequestURI();  // 식별자(uri), url(도메인까지 나옴) // 잘라내기 위해 com 변수 저장
		com = com.substring(com.lastIndexOf("/"), com.lastIndexOf("."));  // +1 안하고 24번줄 "/" 넣어둠, +1 하면 ""
		
		// 인증....처리......
		HttpSession session = request.getSession();  // 세션을 열음
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");  // int 담아서 밑에서 크고작고 비교 가능 // level 그릇에 비로그인 시 등급이 없으면 999 부여  // 로그인 안했는데 .mem으로 왔으니 쫓아 버려야
		
		
		if(com.equals("/MemberLogin")) {  // 로그인창
			viewPage += "/memberLogin.jsp";
		}
		else if(com.equals("/MemberLoginOk")) {  // Ok이면 아이디랑 비번 가져옴 인터페이스 command 객체 필요
			command = new MemberLoginOkCommand();
			command.excute(request, response);  // 실행객체
			viewPage = "/include/message.jsp";  // ~님 로그인되었습니다. 메시지 보냄
		}
		else if(com.equals("/MemberLogout")) {
			command = new MemberLogoutCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/MemberJoin")) {
			viewPage += "/memberJoin.jsp";
		}
		else if(com.equals("/MemberJoinOk")) {
			command = new MemberJoinOkCommand();  // ajax 처리 한 것이 아니기 때문에 msg띄워서 보내주면 됨
			command.excute(request, response);
			viewPage ="/include/message.jsp";  // 가입이 완료됐다는 메시지를 띄우고 view창으로 보냄
		}
		else if(com.equals("/MemberIdCheck")) {
			command = new MemberIdCheckCommand();
			command.excute(request, response);
			return;  // ajax니까 return으로 해줌!!!!!
		}
		else if(level > 4) {  // 순서를 잘 설정해야(login, join 뒤에) => Spring에서는 인터셉트에서 설정(지금은 controller에서)
			request.setAttribute("message", "로그인 후 사용하세요");
			request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");
			viewPage += "/include/message.jsp";
		}
		else if(com.equals("/MemberMain")) {  // 로그인하면 필요한걸 다 담아서 memberMain으로 보냄
			command = new MemberMainCommand();  // 경로명, 클래스명 대문자
			command.excute(request, response);
			viewPage += "/memberMain.jsp";  // 소문자
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
