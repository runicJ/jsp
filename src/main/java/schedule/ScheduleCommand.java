package schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ScheduleCommand implements ScheduleInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오늘 날짜 처리(저장)
		Calendar calToday = Calendar.getInstance();  // 싱글통 객체 , 객체 생성x, getInstance로
		int toYear = calToday.get(Calendar.YEAR);
		int toMonth = calToday.get(Calendar.MONTH);
		int toDay = calToday.get(Calendar.DATE);
		
		// 화면에 보여줄 해당 '년/월'을 세팅
		int yy = request.getParameter("yy")==null ? toYear : Integer.parseInt(request.getParameter("yy"));  // 값이 안넘어오면 오늘기준
		int mm = request.getParameter("mm")==null ? toMonth : Integer.parseInt(request.getParameter("mm"));
		
		// 이전월 클릭시 1월(0)은 음수가 넘어오기에, 년도를 -1로 빼주고, 월은 12월(11)로 세팅해준다.
		// 다음월 클릭시 12월(11)은 13월이 넘어오기에, 년도를 +1로 빼주고, 월은 1월(0)로 세팅해준다.
		if(mm < 0) {
			yy--;  // 음수로 넘어오면 연도를 뺌
			mm = 11;  // 12월로
		}
		if(mm > 11) {
			yy++;
			mm = 0;
		}
		
		// 선택한 해당 '년/월'의 1일을 기준으로 날짜를 세팅해준다.(처음(첫(그 년월의 첫날)) 날짜 셋팅 : '년/월/1'로 셋팅한다.)  // 일정관리가 예약과 같음
		calToday.set(yy, mm, 1);  // 1일로 셋팅해서 꺼내야 요일 적용하기 쉽다.
		
		// 앞에서 셋팅된 해당 '년/월/1'의 요일에 해당하는 값을 숫자로 가져온다.(일:1, 월:2, 화:3, 수:4 ~) : 날짜를 표시할 시작테이블의 1일이 들어갈 첫 열을 찾기위한 것
		int startWeek = calToday.get(Calendar.DAY_OF_WEEK);  // 시작테이블에 들어갈 요일을 꺼낼것  // (현재날짜세팅할것의 요일(숫자로 가져옴))
		
		// 해당 '년/월'의 마지막 일자를 가져온다.
		int lastDay = calToday.getActualMaximum(Calendar.DAY_OF_MONTH);  // 해당월의 마지막 날짜 // Calendar 변수.get(가져오는 것)마지막일자
		
		// 화면에 보여줄 달력의 해당 내역(년/월/요일숫자/마지막일자)을 저장소에 담아서 넘겨준다.
		request.setAttribute("yy", yy);
		request.setAttribute("mm", mm);
		request.setAttribute("startWeek", startWeek);
		request.setAttribute("lastDay", lastDay);

		// 오늘 날짜를 저장소에 담아서 전송한다.
		request.setAttribute("toYear", toYear);
		request.setAttribute("toMonth", toMonth);
		request.setAttribute("toDay", toDay);
		
		// 현재달을 기준으로 전월/다음월을 구하여 저장소에 담아서 넘겨준다.
		
		// 앞에서 세팅한 '년/월'의 앞쪽 셀은 이전달 마지막일자를 적는다.
		int prevYear = yy;
		int prevMonth = mm - 1;
		int nextYear = yy;
		int nextMonth = mm + 1;
		
		if(prevMonth == -1) {
			prevYear--;
			prevMonth = 11;
		}
		if(nextMonth == 12) {
			nextYear++;
			nextMonth = 0;
		}
		
		// 현재월의 이전월에 해당하는 마지막 날짜를 구한다. 
		calToday.set(prevYear, prevMonth, 1);
		int prevLastDay = calToday.getActualMaximum(Calendar.DAY_OF_MONTH);  // 전년도, 전월, 일수에 해당하는 것을 가져옴
		
		// 다음월의 1일에 해당하는 요일을 숫자로 구한다.  // 다음은 무조건 1 다음월의 시작요일 위치만 확인하면 됨
		calToday.set(nextYear, nextMonth, 1);
		int nextStartWeek = calToday.get(Calendar.DAY_OF_WEEK);
		
		// 현재달의 '전월/다음월'의 변수를 저장해서 넘겨준다.
		request.setAttribute("prevYear", prevYear);
		request.setAttribute("prevMonth", prevMonth);
		request.setAttribute("prevLastDay", prevLastDay);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("nextStartWeek", nextStartWeek);
		
		// DB에 저장된 일정 정보들을 가져와서 저장소에 담아준다.		
		HttpSession session = request.getSession();  // 개개인의 일정을 가져옴(개인 것-로그인한 사람의 정보)
		String mid = (String) session.getAttribute("sMid");  // 강제형변환
		
		//String[] ymds = request.getParameter("ymd").split("-");  // 파라미터로 가져오되 나눠서 배열로 저장  // ymd를 그냥 꺼내면 연월일 시간까지 나옴
		//if(ymds[1].length() == 1) ymds[1] = "0" + ymds[1];  // 연도는 4글자 월 일이 5-1 한자리 나올 수 있음  // 숫자로 parseInt해서 10보다 작으면 0붙임 // 문자일때 길이가 Byte로 바꾸면 한자리는 1로 나오고 그럼 0이 없는 것
		//if(ymds[2].length() == 1) ymds[2] = "0" + ymds[2];  // db에 들어오는 형식과 맞춰서 db 데이터와 비교하려고 형식을 맞춰줌
		
//		String ymd = ymds[0] + "-" + ymds[1] + "-" + ymds[2];
		
		// 2024-5 ==> 2024-05
		String ym = "";
		if(mm+1 < 10) ym = yy + "-0" + (mm+1);
		else ym = yy + "-" + (mm+1);
		
		ScheduleDAO dao = new ScheduleDAO();
		
		ArrayList<ScheduleVO> vos = dao.getScheduleList(mid, ym, 0);  // 오늘날짜를 가져와야함, 년도 월 일 가져가야 함  // 0 넘겨서 // 0:partCnt, 1은 내역(list)
		
		request.setAttribute("vos", vos);
	}

}
