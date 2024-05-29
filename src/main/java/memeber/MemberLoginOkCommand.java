package memeber;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;
import guest.GuestDAO;

public class MemberLoginOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberIdCheck(mid);
		
		// 아래로 회원 인증 처리  (SHA-256 하기 전이라서 아래처럼 간단히 처리 // 회원가입시 비번 암호화 후에 처리)
		if(vo.getPwd() == null || vo.getUserDel().equals("OK")) {  // 탈퇴 신청한 회원 보여주면 안됨
			request.setAttribute("message", "입력하신 회원정보가 없습니다.\\n확인하고 다시 로그인 해주세요.");  // 아이디를 확인하세요.  // 메시지 전달
			request.setAttribute("url", "MemberLogin.mem");  // 어디로 보낼건지
			return;  // 끊어주려면
		}
		
		// 저장된 비밀번호에서 salt키를 분리시켜서 다시 암호화 시킨 후 맞는지 비교처리한다.
		// salt키 분리  // 먼저 salt키로 무작위 수를 만들고 회원가입시 입력한 비번과 합쳐서 sha256으로 암호처리, DB에 저장할때 암호처리된 비밀번호 앞에 만든 salt키를 붙여서 저장/ 로그인시 DB에 저장된 pw의 salt키(8자리) 분리하고 로그인할때 입력한 pw와 합쳐서 sha256 암호화 한다음 DB에 저장된 것과 비교
		String salt = vo.getPwd().substring(0,8);
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(salt + pwd);
		
		if(!vo.getPwd().substring(8).equals(pwd)) {  // DB pwd 8자리부터
			request.setAttribute("message", "비밀번호를 확인하세요");  // 회원정보가 없습니다.  // 메시지 전달
			request.setAttribute("url", request.getContextPath()+"/MemberLogin.mem");
			return;
		}
		
		// 로그인 체크 완료 후에 처리할 내용....(쿠키/세션/...)		
		// 회원이 맞으면 vo.getMid값이 null이 아니다.
		// 회원일때 처리할 부분
		/*
		 * 1.방문포인트지급:매번 10pt씩 지급, 단 1일 최대 50pt까지만 지급 // 날짜비교 6번부터는 지급x
		 * 2-1.최종접속일 처리, 방문카운트(일일방문카운트, 전체누적방문카운트)
		 * 2-2.준회원을 자동으로 정회원 등업처리....
		 * 3.처리 완료된 자료(vo)를 DB에 업데이트 해준다. 
		 */
		
		// 1번처리 : 방문포인트 처리를 위한 날짜 추출 비교하기 - 조건에 맞도록 방문 포인트와 카운트를 중지처리한다.  // Date simpleDateformat / calendar(singleton) / // 여긴 JAVA  // 날짜형식과 DB에 String 형식 맞춰야함
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sdf.format(today);  // String으로 문자변수 today 만듦
		
		if(!strToday.equals(vo.getLastDate().subSequence(0, 10))) {  // 오늘 처음 접속(마지막 접속일과 오늘접속일이 같으면) => 일일방문카운트 무조건 1
			// 오늘 처음 방문한 경우이다.(오늘 방문카운트는 1로, 기존 포인트에 +10)
			vo.setTodayCnt(1);
			vo.setPoint(vo.getPoint()+ 10);
		}
		else {
			// 오늘 다시 방문한 경우(오늘 방문 카운트는 오늘방문카운트 + 1, 포인트 증가는? 오늘 방문 횟수가 5회 전까지라면 기존 포인트에 +10을 한다.)
			vo.setTodayCnt(vo.getTodayCnt()+1);
			if(vo.getTodayCnt() <= 5) vo.setPoint(vo.getPoint()+ 10);
		}

		// 2번처리 : 자동 정회원 등업시키기
		// 조건 : 방명록에 5회 이상 글을 올렸을 시 '준회원'에서 '정회원'으로 자동 등업처리한다.(단, 방명록의 글은 1일 여러번 등록해도 1회로 처리한다.)
		GuestDAO gDao = new GuestDAO();
		int guestCnt = gDao.getMemberGuestCount(mid, vo.getName(), vo.getNickName());
		//System.out.println("guestCnt : " + guestCnt);
		if(vo.getLevel() == 1) {
			if(guestCnt >= 5) {
				vo.setLevel(2);
			}
			else vo.setLevel(1);
		}
		
		/*
		 * // 숙제 - 자동 정회원 등업시키기 // 조건 : 방명록에 5회 이상 글을 올렸을 시 '준회원'에서 '정회원'으로 자동 등업처리한다.
		 * (단, 방명록의 글은 하루에 여러번 등록해도 1회로 처리한다. GuestDAO gDao = new GuestDAO();
		 * ArrayList<GuestVO> gVos = gDao.getMemberGuestSearch(mid, vo.getName(),
		 * vo.getNickName());
		 * 
		 * Set<String> guestTime = new HashSet<>(); // 중복된 날짜를 제거하기 위해 HashSet 사용
		 * for(GuestVO gVo : gVos) { guestTime.add(gVo.getVisitDate().substring(0, 10));
		 * }
		 * 
		 * int guestCnt = guestTime.size();
		 * 
		 * if(guestCnt >= 5) { vo.setLevel(2); } dao.setLoginUpdate(vo); }
		 * 가져온 방명록 정보에서 방문 날짜(visitDate)를 추출하여 중복을 제거하기 위해 HashSet에 저장합니다. 
		 * 방문 날짜는 문자열 형식으로 "YYYY-MM-DD HH:MM:SS"이므로 substring() 메서드를 사용하여 날짜 부분만 추출합니다.
		 * HashSet의 크기를 통해 방명록에 등록된 고유한 날짜 수를 계산합니다. 이를 통해 회원이 방명록에 등록한 총 글 수를 알 수 있습니다
		 */
		
		// 3번처리 : 방문포인트와 카운트를 증가처리한 내용을 vo에 모두 담았다면 DB 자신의 레코드에 변경된 사항들을 갱신 처리해준다.
		dao.setLoginUpdate(vo);
		
		// 쿠키에 아이디를 저장/해제 처리한다.
		// 로그인시 아이디저장시킨다고 체크하면 쿠키에 아이디 저장하고, 그렇지 않으면 쿠키에서 아이디를 제거한다.  // 쿠키말고 클라이언트에 저장하는거 나중에
		String idSave = request.getParameter("idSave")==null ? "off" : "on";
		Cookie cookieMid = new Cookie("cMid", mid);
		cookieMid.setPath("/");  // 상위 폴더에 쿠키값을 저장하겠다(Root에)
		if(idSave.equals("on")) {
			cookieMid.setMaxAge(60*60*24*7);	// 쿠키의 만료시간은 1주일로 한다. // 저장x 기록을 해야함(client cookie 저장소에 저장)
		}
		else {
			cookieMid.setMaxAge(0);
		}
		response.addCookie(cookieMid);
		
		// 등급레벨별 등급명칭을 저장한다.
		String strLevel = "";
		if(vo.getLevel() == 0) strLevel="관리자";
		else if(vo.getLevel() == 1) strLevel = "준회원";
		else if(vo.getLevel() == 2) strLevel = "정회원";
		else if(vo.getLevel() == 3) strLevel = "우수회원";  // 비회원은 못들어오니까 저장필요x
		
		// 필요한 정보를 session에 저장처리한다.
		HttpSession session = request.getSession();
		session.setAttribute("sMid", mid);
		session.setAttribute("sNickName", vo.getNickName());  // vo에서 가져와서 sNickName에 저장
		session.setAttribute("sLevel", vo.getLevel());  // 숫자는 비교하기 위해서 대부분 문자로 저장  // 세션에 해당 등급 숫자가 무엇인지 저장
		session.setAttribute("strLevel", strLevel);
		
		request.setAttribute("message", mid+"님 로그인 되셨습니다.");
		request.setAttribute("url", request.getContextPath()+"/MemberMain.mem");
	}

}

/*
 * 1 *
HashSet은 Java에서 제공하는 Set 인터페이스를 구현한 클래스 중 하나입니다. Set은 중복을 허용하지 않고 순서를 보장하지 않는 자료구조입니다. 
HashSet은 이러한 Set의 특징을 그대로 따르면서 해시 테이블을 사용하여 요소를 저장하기 때문에 매우 빠른 검색 속도를 제공합니다.

예를 들어보겠습니다. 친구 목록을 관리하는 프로그램을 만든다고 가정해봅시다. 이 프로그램에서는 한 명의 친구가 여러 번 등록되더라도 중복으로 처리하지 않아야 합니다. 
이때 HashSet을 사용하면 효율적으로 중복을 제거할 수 있습니다.

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<String> friendSet = new HashSet<>();
        
        // 친구 목록에 중복된 이름을 추가해도 HashSet은 중복을 허용하지 않습니다.
        friendSet.add("John");
        friendSet.add("Alice");
        friendSet.add("John"); // 중복된 요소이므로 무시됩니다.
        friendSet.add("Bob");
        friendSet.add("Alice"); // 중복된 요소이므로 무시됩니다.
        
        // HashSet의 크기를 출력하면 중복이 제거된 친구의 수를 알 수 있습니다.
        System.out.println("친구 목록의 크기: " + friendSet.size()); // 출력: 친구 목록의 크기: 3
        
        // HashSet을 반복문으로 순회하면 요소의 순서는 보장되지 않습니다.
        for (String friend : friendSet) {
            System.out.println(friend);
        }
    }
}
이 코드에서는 HashSet을 사용하여 친구 목록을 관리합니다. HashSet은 중복된 요소를 허용하지 않으므로 "John", "Alice"와 같이 중복된 이름을 여러 번 추가해도 중복이 제거됩니다. 
이후 HashSet의 크기를 출력하면 중복이 제거된 친구의 수를 알 수 있습니다.

HashSet을 반복문으로 순회할 때는 요소의 순서가 보장되지 않습니다. 따라서 출력 결과는 요소가 추가된 순서와 다를 수 있습니다.

 * 2 *
HashSet은 자바의 컬렉션 프레임워크에서 Set 인터페이스를 구현한 클래스 중 하나입니다. Set은 순서가 없고 중복을 허용하지 않는 데이터의 집합을 나타냅니다. 
HashSet은 이러한 Set의 특성을 가지면서 내부적으로 해시 테이블을 사용하여 데이터를 저장합니다.

예를 들어, 게시판의 방명록에는 각 사용자가 방문한 날짜가 기록되어 있습니다. 회원이 여러 번 방문했더라도 하루에 여러 번 방문한 경우에는 하루에 중복되는 날짜는 한 번으로만 계산해야 합니다. 
이때 HashSet을 사용하면 중복된 날짜를 간편하게 제거할 수 있습니다.

import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> uniqueDates = new HashSet<>();
        
        // 방문 날짜 추가
        uniqueDates.add("2024-05-10");
        uniqueDates.add("2024-05-10"); // 중복된 값이므로 무시됨
        uniqueDates.add("2024-05-11");
        uniqueDates.add("2024-05-12");
        uniqueDates.add("2024-05-12"); // 중복된 값이므로 무시됨
        
        // 중복이 제거된 방문 날짜 출력
        System.out.println("중복이 제거된 방문 날짜:");
        for (String date : uniqueDates) {
            System.out.println(date);
        }
    }
}
이 예제에서는 HashSet을 사용하여 방문한 날짜를 저장하고, 중복된 값이 무시되는 것을 확인할 수 있습니다. 따라서 uniqueDates에는 중복이 제거된 방문 날짜만 남게 됩니다.
HashSet을 이용하면 중복된 값 제거와 같은 작업을 간단하게 처리할 수 있습니다.
*/