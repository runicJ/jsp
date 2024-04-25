package study.j0425;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0425/T12Ok")
public class T12Ok extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		// Front에서 넘어온 값들을 변수에 담아서 처리한다.
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		int age = (request.getParameter("age")== null || request.getParameter("age")== "") ? 0 : Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender"); // 무조건 값 하나는 넘어오니까 null값 처리 안함
//		String[] hobby = request.getParameterValues("hobby")==null ? "" : request.getParameterValues("hobby");  // 값이 여러개면 받는 쪽에서 배열로 받아야
		String[] hobbys = request.getParameterValues("hobby");
		String job = request.getParameter("job");  // 아무 것도 안하면 공백 처리되니까 null값 처리 안해도 됨
		String[] mountains = request.getParameterValues("mountain");
		String content = request.getParameter("content")== null ? "" : request.getParameter("content");
		content = content.replace("\n", "<br/>"); /* *** */
		String fileName = request.getParameter("fileName");
		
		String hobby = "";  // 밑에서 출력하려고 선언
			if(hobbys != null) {
			for(String h : hobbys) {
	//			System.out.print(h + "/");
				hobby += h + "/";
			}
			hobby = hobby.substring(0, hobby.length()-1);
		}
			
		String mountain = "";  // 밑에서 출력하려고 선언
		if(mountains != null) {
			for(String m : mountains) {
				mountain += m + "/";
			}
			mountain = mountain.substring(0, mountain.length()-1);
		}
		
		System.out.println("성명 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("성별 : " + gender);
		System.out.println("취미 : " + hobby);
		System.out.println("직업 : " + job);
		System.out.println("가본산 : " + mountain);
		System.out.println("자기소개 : " + content);
		System.out.println("파일명 : " + fileName);
		
//		response.sendRedirect(request.getContextPath() + "/study/0425/t12_Main.jsp?name="+name+"&age="+age+"&gender="+gender+"&job="+job);  // 쿼리스트링(get방식)  // java.lang.IllegalArgumentException: code point [54,861]에 위치한 유니코드 문자 [홍]은(는), 0에서 255까지의 허용 범위 바깥에 있으므로 인코딩될 수 없습니다. // 한글 등 문제 content,file, 'mountain, hobby의 /도 문제''그냥 한글 자체 문제'빼고함(get방식 문제점)
		
		T12VO vo = new T12VO();
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setHobby(hobby);
		vo.setJob(job);
		vo.setMountain(mountain);
		vo.setContent(content);
		vo.setFileName(fileName);
		
		//response.sendRedirect(request.getContextPath() + "/study/0425/t12_Main.jsp?vo="+vo);  // vo는 객체가 아니라 변수임 // 한글값 또 안 넘어옴
	
		request.setAttribute("vo", vo);  // MVC2(이 방식) <- MVC // 넘겨줄 변수, 값 보낼 것  // 가는 곳에 짐을 같이 보냄
		
		String viewPage = "/study/0425/t12_Main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	
}
