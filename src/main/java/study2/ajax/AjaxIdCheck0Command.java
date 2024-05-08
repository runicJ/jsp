package study2.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyDAO;
import study2.StudyInterface;

public class AjaxIdCheck0Command implements StudyInterface {

	@Override  // interface 작업지시서 아래 자동으로 나옴
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");  // 세우면 안되니까 바로 차에 싣음
		
		StudyDAO dao = new StudyDAO();
		
		String name = dao.getIdSearch(mid);
		
		if(!name.equals("")) {  // dao에서 값이 없으면 ""으로 넘어옴(!= null x)
			request.setAttribute("name",	name);  // 넘어가면 controller에서 view로 보냄
			request.setAttribute("message", "전송된 아이디 : "+mid+", 성명 : " + name);  // 메시지를 출력하는 곳에서 내용이 꺾임(멈췄다 => 위의 내용이 사라짐(request 날라감) => 다시 실어줘야함)
			request.setAttribute("url", "ajaxTest1.st?name=" + name);
		}
		else {
			request.setAttribute("message", "검색된 아이디가 없습니다.");
			request.setAttribute("url", "ajaxTest1.st");  // 넘길 수가 없으니까 호출만
		}
	}

}
