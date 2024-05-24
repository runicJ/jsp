package schedule;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ScheduleMenuCommand implements ScheduleInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  // 개개인의 일정을 가져옴(개인 것-로그인한 사람의 정보)
		String mid = (String) session.getAttribute("sMid");  // 강제형변환
		
		String[] ymds = request.getParameter("ymd").split("-");  // 파라미터로 가져오되 나눠서 배열로 저장  // ymd를 그냥 꺼내면 연월일 시간까지 나옴
		if(ymds[1].length() == 1) ymds[1] = "0" + ymds[1];  // 연도는 4글자 월 일이 5-1 한자리 나올 수 있음  // 숫자로 parseInt해서 10보다 작으면 0붙임 // 문자일때 길이가 Byte로 바꾸면 한자리는 1로 나오고 그럼 0이 없는 것
		if(ymds[2].length() == 1) ymds[2] = "0" + ymds[2];  // db에 들어오는 형식과 맞춰서 db 데이터와 비교하려고 형식을 맞춰줌
		
		String ymd = ymds[0] + "-" + ymds[1] + "-" + ymds[2];
		
		ScheduleDAO dao = new ScheduleDAO();
		
		ArrayList<ScheduleVO> vos = dao.getScheduleList(mid, ymd, 1);  // 오늘날짜를 가져와야함, 년도 월 일 가져가야 함
		
		request.setAttribute("vos", vos);
		request.setAttribute("ymd", ymd);  // 오늘날짜 기억해야 일정등록 가져가야함(날짜를 누르고 들어가잖아)
		request.setAttribute("scheduleCnt", vos.size());
	}

}
