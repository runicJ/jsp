package study2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import study2.ajax.AjaxIdCheck0Command;
import study2.ajax.AjaxIdCheck1Command;
import study2.calendar.Calendar1Command;
import study2.calendar.Calendar2Command;
import study2.hoewon.HoewonDeleteCommand;
import study2.hoewon.HoewonInputCommand;
import study2.hoewon.HoewonMainCommand;
import study2.hoewon.HoewonSearchCommand;
import study2.hoewon.HoewonUpdateCommand;
import study2.modal.ModalTestCommand;
import study2.pdstest.FileDeleteCheckCommand;
import study2.pdstest.FileDeleteCommand;
import study2.pdstest.FileDownloadCommand;
import study2.pdstest.FileUpload1OkCommand;
import study2.pdstest.FileUpload2OkCommand;
import study2.pdstest.FileUpload3OkCommand;
import study2.pdstest.FileUpload4OkCommand;
import study2.pdstest.JavaFileDownloadCommand;
import study2.scrollPage.ScrollPageCommand;
import study2.transaction.TransactionBankBookCommand;
import study2.transaction.TransactionTest1Command;
import study2.transaction.TransactionTest2Command;

@SuppressWarnings("serial")
@WebServlet("*.st")
public class StudyController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudyInterface command = null;
		String viewPage = "/WEB-INF/study2";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/")+1, com.lastIndexOf("."));  // 집중화 끝
		
		// 인증....처리.....
		HttpSession session = request.getSession();  // 세션을 열음
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
		
		if(level > 4) {
			request.setAttribute("message", "로그인후 사용하세요");
			request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");
			viewPage = "/include/message.jsp";
		}
		// 다시 분리
		if(com.equals("ajaxTest1")) {
			viewPage += "/ajax/test1.jsp";  // 일반적으로 보내는 것(+=)
		}
		else if(com.equals("ajaxIdCheck0")) {  // mid 여기서 받아서 처리
			command = new AjaxIdCheck0Command();  // 해야할 일 여기서 안쓰고 service 만들어서 사용
			command.execute(request, response);  // execute 메소드 실행
			//viewPage += "/ajax/test1.jsp";  // view로 내용을 보냄
			viewPage = "/include/message.jsp";  // 메시지 url
		}
		else if(com.equals("ajaxIdCheck1")) {
			command = new AjaxIdCheck1Command();  // ctrl + shift + o import
			command.execute(request, response);
			//viewPage += "/ajax/test1.jsp";  // 비동기식이니까 메시지를 굳이 띄울 필요 없음  // 계속 전송이 돼서 오류가 난 것 => 멈춰줌(비동기식)  // 현재 화면에서 다시 부른 것이 됨(비동기식은 현재 화면에서만 작동)
			return;  // 강제로 멈춤
		}
		else if(com.equals("ajaxTest2")) {
			viewPage += "/ajax/test2.jsp";  // view에 바로 보여주는건 Command 안만들어도 됨
		}
		else if(com.equals("ajaxTest3")) {
			command = new HoewonMainCommand();
			command.execute(request, response);
			viewPage += "/ajax/hoewonMain.jsp";
		}
		else if(com.equals("hoewonInput")) {
			command = new HoewonInputCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("hoewonSearch")) {
			command = new HoewonSearchCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("hoewonUpdate")) {
			command = new HoewonUpdateCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("hoewonDelete")) {
			command = new HoewonDeleteCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("uuidForm")) {
			viewPage += "/uuid/uuidForm.jsp";
		}
		else if(com.equals("Modal1")) {
			viewPage += "/modal/modal1.jsp";
		}
		else if(com.equals("Modal2")) {
			command = new ModalTestCommand();
			command.execute(request, response);
			viewPage += "/modal/modal2.jsp";
		}
		else if(com.equals("FileUpload")) {
			viewPage += "/pdstest/fileUpload.jsp";
		}
		else if(com.equals("FileUpload1")) {
			viewPage += "/pdstest/fileUpload1.jsp";
		}
		else if(com.equals("FileUpload1Ok")) {
			command = new FileUpload1OkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("FileUpload2")) {
			viewPage += "/pdstest/fileUpload2.jsp";
		}
		else if(com.equals("FileUpload2Ok")) {
			command = new FileUpload2OkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("FileUpload3")) {
			viewPage += "/pdstest/fileUpload3.jsp";
		}
		else if(com.equals("FileUpload3Ok")) {
			command = new FileUpload3OkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("FileUpload4")) {
			viewPage += "/pdstest/fileUpload4.jsp";
		}
		else if(com.equals("FileUpload4Ok")) {
			command = new FileUpload4OkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("FileUpload5")) {
			viewPage += "/pdstest/fileUpload5.jsp";
		}
		else if(com.equals("FileUpload6")) {
			viewPage += "/pdstest/fileUpload6.jsp";
		}
		else if(com.equals("FileDownload")) {
			command = new FileDownloadCommand();
			command.execute(request, response);
			viewPage += "/pdstest/fileDownload.jsp";
		}
		else if(com.equals("JavaFileDownload")) {
			command = new JavaFileDownloadCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("FileDelete")) {
			command = new FileDeleteCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("FileDeleteCheck")) {
			command = new FileDeleteCheckCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("Calendar1")) {
			command = new Calendar1Command();
			command.execute(request, response);
			viewPage += "/calendar/calendar1.jsp";
		}
		else if(com.equals("Calendar2")) {
			command = new Calendar2Command();
			command.execute(request, response);
			viewPage += "/calendar/calendar2.jsp";
		}
		else if(com.equals("ScrollStudy")) {
			viewPage += "/scrollPage/scrollStudy.jsp";  // 보여주기만 하면 될 것 같아서
		}
		else if(com.equals("ScrollBasic")) {
			command = new ScrollPageCommand();
			command.execute(request, response);
			viewPage += "/scrollPage/scrollBasic.jsp";
		}
		else if(com.equals("ScrollPage")) {
			command = new ScrollPageCommand();  // Basic과 똑같은 command 객체
			command.execute(request, response);
			viewPage += "/scrollPage/scrollPage.jsp";
		}
		else if(com.equals("Transaction")) {
			viewPage += "/transaction/transaction.jsp"; // 처음에는 view만 있으면 됨
		}
		else if(com.equals("TransactionBankBook")) {
			command = new TransactionBankBookCommand();
			command.execute(request, response);
			viewPage += "/transaction/transactionBankBook.jsp";  // 조각나 jsp 프로그램
		}
		else if(com.equals("TransactionTest1")) {
			command = new TransactionTest1Command();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("TransactionTest2")) {
			command = new TransactionTest2Command();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
