package pds;

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
@WebServlet("*.pds")  // 확장자 패턴으로
public class PdsController extends HttpServlet {  // 4
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PdsInterface command = null;  // command 객체라서 변수명 이렇게 줌, 인터페이스이름 쓰고 마우스 올려서 interface create
		String viewPage = "/WEB-INF/pds";
		
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
		else if(level == 1) {
			request.setAttribute("message", "정회원 이상만 사용할 수 있습니다.");
			request.setAttribute("url", request.getContextPath()+"/MemberMain.mem");
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/PdsList")) {
			command = new PdsListCommand();
			command.excute(request, response);
			viewPage += "/pdsList.jsp";
		}
		else if(com.equals("/PdsInput")) {
			command = new PdsInputCommand();
			command.excute(request, response);
			viewPage += "/pdsInput.jsp";
		}
		else if(com.equals("/PdsInputOk")) {
			command = new PdsInputOkCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/PdsDownNumCheck")) {
			command = new PdsDownNumCheckCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/PdsContent")) {
			command = new PdsContentCommand();
			command.excute(request, response);
			viewPage += "/pdsContent.jsp";
		}
		else if(com.equals("/PdsDeleteCheck")) {
			command = new PdsDeleteCheckCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/PdsTotalDownload")) {
			command = new PdsTotalDownloadCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
