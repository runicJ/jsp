package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;

@WebFilter("/*")  // annotation 방식 이것만 올리면 됨  
public class FilterTest implements Filter {  // implements Filter 해야 filter의 자격을 갖춘것(실행X)

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");  // 관점지향프로그램(Spring) AOP에 해당하는 부분
		response.setContentType("text/html; charset=utf-8");
		
//		System.out.println("1. 필터 수행 전 입니다.");  // 밑에 chain.doFilter(인증카드) 안 적으면 아무것도 안 나옴  // 얘가 필터라고 알려줘야 함(web.xml 필요)
		// annotation 방식으로 하려면 web.xml에 있는걸 주석처리 후에 
		chain.doFilter(request, response);  // 아무것도 안거르고 요청 들어오면 모두 다 통과해서 넘기겠다(다 응답).  // 얘 안쓰면 막혀서 안나옴
		
//		System.out.println("2. 필터 수행 후 입니다.");
	}
	// 여러개의 필터 만들 수 있고 필터가 여러개면 그 여러개의 필터를 모두 통과해야 함
}
