package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

/* @WebFilter("/atom") atom만 통과할거야 */
//@WebFilter("/*") => 보안에 필터를 사용했다는 것만 기억해놓기
public class FilterCertification implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("1. 필터 수행 전..");
		
		String admin = request.getParameter("admin")==null ? "" : request.getParameter("admin");  // 쿼리스트링으로 넘기려면 request.getParameter, 서블릿에서 null값 처리 안하면 에러남
		String u = request.getParameter("u")==null ? "" : request.getParameter("u");
		System.out.println("admin : " + admin + " , user : " + u);
		
		PrintWriter out = response.getWriter();
		
		// 필터에서는 session객체의 사용불가, application 객체 사용 가능...
		//HttpSession session = request.getse
		ServletContext application = request.getServletContext();  // application server 동작할때 돌아감 필터에서도 값을 바꿀 수 있음(관리자가 중간에 환경설정) => Spring의 interciptor
		String aCertification = application.getAttribute("aCertification")==null ? "" : (String) application.getAttribute("aCertification");  // 읽어오는 것 처음엔 null
		System.out.println("현재 발급된 인증번호 : " + aCertification);
		
		// 주소창에 ?admin=admin 으로 들어왔을 경우에만 아래 조건을 통과시키도록 한다.
		
		if(!admin.equals("admin")) {  // 관리자가 아닐 경우에만 퇴출
			// 일반 유저이면서, 인증코드가 발급(32번줄)되지 않았다면(인증코드 발급 전), '발급 대기' 메시지를 띄우고 돌려보낸다.(서버는 켜져 있고)
			if(aCertification.equals("")) {
				out.println("<script>");  // 이럴때만 사용하고 서버단에서는 script 거는 것을 거의 사용x
				out.println("alert('아직 인증코드가 발급되지 않았습니다.\\n잠시후 다시 접속해 주세요.');");  // \\ 하나 더 붙여야 줄바꿈 작동(에러는 script에러처럼 뜸)
				out.println("history.back();");  // history.back(1)과 같음, history.back(5) 5번 앞으로 감 // 잘못 사용하면 무한루프에 빠질 수 있음
				out.println("</script>");
				return;  // 더이상 안돌게 끊어줌
			}
			
			// 일반유저가 인증코드 발급시 처리(인증코드 발급 후)
			String aUserCertification = application.getAttribute("aUserCertification")==null ? "" : (String) application.getAttribute("aUserCertification");  // 유저로 넣은 것을 읽어옴
			if(!aUserCertification.equals(aCertification)) {  // 기존 인증코드와 같지 않을 경우 처리
				if(!u.equals(aCertification)) {
					out.println("<script>");
					out.println("alert('인증코드를 확인하세요.(?c=인증코드)');");
					out.println("history.back();");
					out.println("</script>");
					return;
				}
				else {
					out.println("<script>");
					out.println("alert('인증되셨습니다. 서비스 이용이 가능합니다.');");  // 인증코드 마감 시간 설정
					out.println("</script>");
					application.setAttribute("aUserCertification", u);  // 인증키 등록
				}
			}
		}
//		else {뭐 관리자님 로그인 되었습니다. 해도됨}
		
		chain.doFilter(request, response);
		
		if(admin.equals("admin") && aCertification.equals("")) {
			out.println("<script>");
			out.println("alert('관리자님~ 인증코드를 발급해주세요');");
//			out.println("location.href='t2_Certification.jsp?admin=admin'");  // 필터안에서는 location.href 사용불가  // out.println("location.href='주소(t2_Certification.jsp?admin=admin)'"); 해도 못들어 감(sendRedirect, forward도 불가) // script는 클라이언트에 보여주는 것 // location 하면 다시 수문장 만남(보안 개념에서 생각해야) // 이 명령이 절대 안됨(되면 해커들)
			out.println("</script>");
		}
		
		System.out.println("2. 필터 수행 후..");
	}
}
