package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.guest.GuestListCommand;
import admin.member.MemberLevelChangeCommand;
import admin.member.MemberListCommand;

@SuppressWarnings("serial")  // 필터 통과하고 제일 먼저 들어옴
@WebServlet("*.bo")  // 확장자 패턴으로
public class BoardController extends HttpServlet {  // 4
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardInterface command = null;  // command 객체라서 변수명 이렇게 줌, 인터페이스이름 쓰고 마우스 올려서 interface create
		String viewPage = "/WEB-INF/board";
		
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
		else if(com.equals("/BoardList")) {
			command = new BoardListCommand();
			command.excute(request, response);
			viewPage += "/boardList.jsp";
		}
		else if(com.equals("/BoardInput")) {
			viewPage += "/boardInput.jsp";  // command에 가서 가져올 것이 없는 경우(만약에 홈페이지 이메일 등 있는 경우 필요) = view에서 session으로 아이디를 가져오기 때문에
		}
		else if(com.equals("/BoardInputOk")) {
			command = new BoardInputOkCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/BoardContent")) {
			command = new BoardContentCommand();
			command.excute(request, response);
			viewPage += "/boardContent.jsp";
		}
		else if(com.equals("/BoardUpdate")) {
			command = new BoardUpdateCommand();
			command.excute(request, response);
			viewPage += "/boardUpdate.jsp";  // 수정하는 곳으로 보냄
		}
		else if(com.equals("/BoardUpdateOk")) {
			command = new BoardUpdateOkCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/BoardDelete")) {
			command = new BoardDeleteCommand();
			command.excute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/BoardGoodCheck")) {
			command = new BoardGoodCheckCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/BoardGoodCheck2")) {  // 세션처리(중복불허)
			command = new BoardGoodCheck2Command();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/BoardGoodCheckPlusMinus")) {
			command = new BoardGoodCheckPlusMinusCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/BoardSearchList")) {
			command = new BoardSearchListCommand();
			command.excute(request, response);
			viewPage += "/boardSearchList.jsp";  // ajax가 아니니까
		}
		else if(com.equals("/BoardReplyInput")) {
			command = new BoardReplyInputCommand();
			command.excute(request, response);
			return;
		}
		else if(com.equals("/BoardReplyDelete")) {
			command = new BoardReplyDeleteCommand();
			command.excute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
