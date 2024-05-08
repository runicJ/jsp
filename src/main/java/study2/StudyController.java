package study2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.ajax.AjaxIdCheck0Command;
import study2.ajax.AjaxIdCheck1Command;

@SuppressWarnings("serial")
@WebServlet("*.st")
public class StudyController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudyInterface command = null;
		String viewPage = "/WEB-INF/study2";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/")+1, com.lastIndexOf("."));  // 집중화 끝
		
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
		/*
		 * else if(com.equals("")) {
		 * 
		 * viewPage = "/include/message.jsp"; // 메시지 보낼때(=) }
		 */
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
