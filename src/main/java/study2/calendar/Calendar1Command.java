package study2.calendar;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class Calendar1Command implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}

}
