package study.j0430;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0430/LifeCycle1Ok")
public class LifeCycle1Ok extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 service 메소드 입니다.");
		/* filterTest에서 console 보면 
		1. 필터 수행 전 입니다.
		이곳은 service 메소드 입니다.
		2. 필터 수행 후 입니다. */
		
		response.sendRedirect(request.getContextPath()+"/study/0430_web_xml/lifeCycle/lifeCycle1.jsp");
	}
	
	@Override
	public void init() throws ServletException {  // 처음에 한번만 실행, 아무것도 상속x(초기화) request,response 이런거 사용x // 맨 앞에  // 메소드의 위치 순서는 관계없음
		System.out.println("이곳은 init 메소드 입니다.");
	}
	
	@Override
	public void destroy() {  // 사용한 것 반환, 시작과 끝 알려줌
		System.out.println("이곳은 destroy 메소드 입니다.");
	}
	
	/*
	 이곳은 init 메소드 입니다.
	 1. 필터 수행 전 입니다.
	 이곳은 service 메소드 입니다.
	 2. 필터 수행 후 입니다.*/
	
}
