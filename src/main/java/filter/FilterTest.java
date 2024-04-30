package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterTest implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");  // 관점지향프로그램(Spring) AOP에 해당하는 부분
		response.setContentType("text/html; charset=utf-8");
		
		System.out.println("1. 필터 수행 전 입니다.");  // 안 나옴  // 얘가 필터라고 알려줘야 함(web.xml 필요)
		
		chain.doFilter(request, response);  // 아무것도 안거르고 요청들어오면 모두 다 통과해서 넘기겠다.  // 얘 안쓰면 막혀서 안나옴
		
		System.out.println("2. 필터 수행 후 입니다.");
	}

}
