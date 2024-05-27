package webMessage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.board.BoardContentCommand;
import admin.board.BoardListCommand;
import admin.complaint.BoardComplaintInputCommand;
import admin.complaint.ComplaintCheckCommand;
import admin.complaint.ComplaintListCommand;
import admin.guest.GuestListCommand;
import admin.member.MemberDeleteOkCommand;
import admin.member.MemberLevelChangeCommand;
import admin.member.MemberLevelSelectCheckCommand;
import admin.member.MemberListCommand;
import admin.review.ReviewDeleteCommand;
import admin.review.ReviewInputOkCommand;
import admin.review.ReviewReplyInputOkCommand;

@SuppressWarnings("serial")  // 필터 통과하고 제일 먼저 들어옴
@WebServlet("*.wm")  // 확장자 패턴으로
public class WebMessageController extends HttpServlet {  // 4
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebMessageInterface command = null;  // command 객체라서 변수명 이렇게 줌, 인터페이스이름 쓰고 마우스 올려서 interface create
		String viewPage = "/WEB-INF/webMessage";
		
		String com = request.getRequestURI();  // 식별자(uri), url(도메인까지 나옴) // 잘라내기 위해 com 변수 저장
		com = com.substring(com.lastIndexOf("/"), com.lastIndexOf("."));  // +1 안하고 24번줄 "/" 넣어둠, +1 하면 ""
		
		// 인증....처리......
		HttpSession session = request.getSession();  // 세션을 열음
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
		
		if(level > 4) {
			request.setAttribute("message", "로그인 후 사용하세요");
			request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/WebMessage")) {
			command = new WebMessageCommand();
			command.excute(request, response);
			viewPage += "/webMessage.jsp";
		}
		else if(com.equals("/WmInputOk")) {
			command = new WmInputOkCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}
//		else if(com.equals("/WebContent")) {  // 얘는 include로 부름 controller 안탐 controller 타는 곳에 내용을 같이 보냄
//			command = new WebContentCommand();
//			command.excute(request, response);
//			viewPage = "/webContent.jsp";
//		}
		else if(com.equals("/WmDeleteCheck")) {
			command = new WmDeleteCheckCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/WmDeleteOne")) {
			command = new WmDeleteOneCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/WmDeleteAll")) {
			command = new WmDeleteAllCommand();
			command.excute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
