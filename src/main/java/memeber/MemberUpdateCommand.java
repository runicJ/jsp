package memeber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberUpdateCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내정보만 다시 실어서 보내줌(vo)
		
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");  // 로그인된 내 아이디를 가져와서 정보를 vo에 저장
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getMemberIdCheck(mid);
		
		// 전화번호 분리(-)  // 보내주기 전에 전화번호 분리
		String[] tel = vo.getTel().split("-");
		if(tel[1].equals(" ")) tel[1] = "";  // 공백이 있으면 공백을 무시하고 보내줘라
		if(tel[2].equals(" ")) tel[2] = "";		
		
		request.setAttribute("tel1", tel[0]);
		request.setAttribute("tel2", tel[1]);
		request.setAttribute("tel3", tel[2]);
		
		// 주소분리(/)
		String[] address = vo.getAddress().split("/");
		if(address[0].equals(" ")) address[0] = "";  // 주소에서는 어차피 api로 찾으니까 이 부분은 안넣어도 되나 정확히 하려고 넣음
		if(address[1].equals(" ")) address[1] = "";
		if(address[2].equals(" ")) address[2] = "";
		if(address[3].equals(" ")) address[3] = "";
		request.setAttribute("postcode", address[0]);
		request.setAttribute("roadAddress", address[1]);
		request.setAttribute("detailAddress", address[2]);
		request.setAttribute("extraAddress", address[3]);
		
		// 취미는 통째로 넘겨서 jstl에서 포함유무로 체크 처리한다.(포함되어 있는 항목이 중복되지 않는걸 해야함) => 분리해서 넘기면 하나하나 비교해야함
		//request.setAttribute("hobby", "vo.hobby");
				
		request.setAttribute("vo", vo);  // vo에 실었으니 수정하는 창으로 보내야함
	}

}
