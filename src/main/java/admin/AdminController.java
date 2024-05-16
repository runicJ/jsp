package admin;

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

@SuppressWarnings("serial")  // 필터 통과하고 제일 먼저 들어옴
@WebServlet("*.ad")  // 확장자 패턴으로
public class AdminController extends HttpServlet {  // 4
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminInterface command = null;  // command 객체라서 변수명 이렇게 줌, 인터페이스이름 쓰고 마우스 올려서 interface create
		String viewPage = "/WEB-INF/admin";
		
		String com = request.getRequestURI();  // 식별자(uri), url(도메인까지 나옴) // 잘라내기 위해 com 변수 저장
		com = com.substring(com.lastIndexOf("/"), com.lastIndexOf("."));  // +1 안하고 24번줄 "/" 넣어둠, +1 하면 ""
		
		// 인증....처리......
		HttpSession session = request.getSession();  // 세션을 열음
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
		
		if(com.equals("/BoardComplaintInput")) {
			command = new BoardComplaintInputCommand();
			command.excute(request, response);
			return;
		}
		else if(level > 0) {
			request.setAttribute("message", "로그인 후 사용하세요");
			request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/AdminMain")) {
			viewPage += "/adminMain.jsp";  // frameset을 부름
		}
		else if(com.equals("/AdminLeft")) {
			viewPage += "/adminLeft.jsp";
		}
		else if(com.equals("/AdminContent")) {
			command = new AdminContentCommand();
			command.excute(request, response);
			viewPage += "/adminContent.jsp";
		}
		else if(com.equals("/GuestList")) {
			command = new GuestListCommand();
			command.excute(request, response);
			viewPage += "guest/guestList.jsp";
		}
		else if(com.equals("/MemberList")) {
			command = new MemberListCommand();
			command.excute(request, response);
			viewPage += "/member/memberList.jsp";
		}
		else if(com.equals("/MemberLevelChange")) {
			command = new MemberLevelChangeCommand();
			command.excute(request, response);
			return;  // ajax니까 return
		}
		else if(com.equals("/MemberDeleteOk")) {
			command = new MemberDeleteOkCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/MemberLevelSelectCheck")) {
			command = new MemberLevelSelectCheckCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/BoardList")) {
			command = new BoardListCommand();
			command.excute(request, response);
			viewPage += "/board/boardList.jsp";
		}
		else if(com.equals("/BoardContent")) {
			command = new BoardContentCommand();
			command.excute(request, response);
			viewPage += "/board/boardContent.jsp";
		}
		else if(com.equals("/ComplaintList")) {
			command = new ComplaintListCommand();
			command.excute(request, response);
			viewPage += "/complaint/complaintList.jsp";
		}
		else if(com.equals("/ComplaintCheck")) {
			command = new ComplaintCheckCommand();
			command.excute(request, response);
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
