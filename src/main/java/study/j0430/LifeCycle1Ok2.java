package study.j0430;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0430/LifeCycle2Ok")
public class LifeCycle1Ok2 extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("이곳은 init 메소드 입니다.");
	}
	
	@Override  // doGet, doPost 사용하려면 서비스가 없어야함(어쨌든 서비스가 먼저 수행되면 안됨)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 service 메소드 입니다.");
		
		doPost(request, response);
	}  // 이렇게 하면 3개 전부 사용
	
	@Override  // doGet doPost 동순위, 뭘 보내느냐에 따라서 실행 순서 달라짐
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 doGet 메소드 입니다.");
		response.sendRedirect(request.getContextPath()+"/study/0430_web_xml/lifeCycle/lifeCycle2.jsp");  // 얘가 제일 마지막에 시행(원래 서비스에 있던 것)
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 doPost 메소드 입니다.");
		doGet(request, response);
	}
	
	@Override
	public void destroy() {  // 사용한 것 반환, 시작과 끝 알려줌
		System.out.println("이곳은 destroy 메소드 입니다.");
	}
	/*
	이곳은 init 메소드 입니다.
	1. 필터 수행 전 입니다.
	이곳은 service 메소드 입니다.
	이곳은 doPost 메소드 입니다.
	이곳은 doGet 메소드 입니다.
	2. 필터 수행 후 입니다.
	*/
	
	@PostConstruct
	public void initPostConstruct() {  // 사용자 메소드에 @ 어노테이션 붙여준 것
		System.out.println("이곳은 사용자메소드 : @PostConstruct 어노테이션 사용시 가장 먼저 수행한다...");
	}
	@PreDestroy
	public void destroyPreDestroy() {
		System.out.println("이곳은 사용자메소드 : @PreDestroy 어노테이션 사용시 가장 마지막에(갱신/자원반납시) 수행한다...");
	}
	
	/*
	이곳은 사용자메소드 : @PostConstruct 어노테이션 사용시 가장 먼저 수행한다.
	이곳은 init 메소드 입니다.
	1. 필터 수행 전 입니다.
	이곳은 service 메소드 입니다.
	이곳은 doPost 메소드 입니다.
	이곳은 doGet 메소드 입니다.
	2. 필터 수행 후 입니다.
	
	1. 필터 수행 전 입니다.
	2. 필터 수행 후 입니다.
	
	5월 02, 2024 2:50:06 오후 org.apache.catalina.core.StandardContext reload
	정보: 이름이 [/javaclass]인 컨텍스트를 다시 로드하는 작업이 시작되었습니다.
	이곳은 destroy 메소드 입니다.
	이곳은 사용자메소드 : @PreDestroy 어노테이션 사용시 가장 마지막에(갱신/자원반납시) 수행한다.
	*/
}
